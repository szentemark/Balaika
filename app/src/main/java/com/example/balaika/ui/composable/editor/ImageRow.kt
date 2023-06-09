package com.example.balaika.ui.composable.editor

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.balaika.COVER_IMAGES_DIRECTORY
import com.example.balaika.R
import com.example.balaika.calculateImageFilePath
import com.example.balaika.model.room.entity.Song
import java.io.File
import java.time.ZonedDateTime

@Composable
fun ImageRow(song: Song, onImageSaved: (Long, String) -> Unit) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        pickImage(context, uri, song, onImageSaved)
    }

    val painter = if (song.imageFile != "") {
        val imageFile = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), song.imageFile)
        rememberAsyncImagePainter(imageFile)
    } else {
        painterResource(id = R.drawable.image_placeholder)
    }
    Card(
        shape = RoundedCornerShape(size = 20.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
        ),
        modifier = Modifier
            .size(240.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    launcher.launch(
                        PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
        )
    }
}

private fun pickImage(context: Context, uri: Uri?, song: Song, onImageSaved: (Long, String) -> Unit) {
    uri?.let {
        // Read content from uri and write content to local file.
        context.contentResolver.openInputStream(uri)?.let { inputStream ->
            // TODO Relevant error snackbar.
            val extension = context.getFileExtension(uri) ?: return
            // If the cover images directory doesn't exist, we create it.
            val coverImagesDirectory = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), COVER_IMAGES_DIRECTORY)
            if (!coverImagesDirectory.exists()) {
                coverImagesDirectory.mkdir()
            }
            // Save the cover image file.
            val timestamp = ZonedDateTime.now().toInstant().toEpochMilli()
            val outputStream = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), song.calculateImageFilePath(timestamp, extension)).outputStream()
            val buf = ByteArray(1024)
            var len: Int
            while (inputStream.read(buf).also { len = it } > 0) {
                outputStream.write(buf, 0, len)
            }
            outputStream.close()
            inputStream.close()
            // Save file location in Room.
            onImageSaved(timestamp, extension)
        }
    }
}

private fun Context.getFileExtension(uri: Uri) =
    getFileName(uri)?.let {
        if (it.contains(".")) {
            it.substring(it.lastIndexOf("."))
        } else ""
    }

private fun Context.getFileName(uri: Uri): String? = when(uri.scheme) {
    ContentResolver.SCHEME_CONTENT -> getContentFileName(uri)
    else -> uri.path?.let(::File)?.name
}

private fun Context.getContentFileName(uri: Uri): String? = runCatching {
    contentResolver.query(uri, null, null, null, null)?.use { cursor ->
        cursor.moveToFirst()
        return@use cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME).let(cursor::getString)
    }
}.getOrNull()