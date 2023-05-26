package com.example.balaika.ui.composable.editor

import android.content.Context
import android.net.Uri
import android.os.Environment
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.balaika.R
import com.example.balaika.ui.theme.DarkBrownCrayonDark
import java.io.File

@Composable
fun ImageRow(imageFileName: String, onImageSaved: () -> Unit) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        pickImage(context, uri, onImageSaved)
    }

    val painter = if (imageFileName != "") {
        val imageFile = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), imageFileName)
        rememberAsyncImagePainter(imageFile)
    } else {
        painterResource(id = R.drawable.gabymorenopostales)
    }
    Card(
        shape = RoundedCornerShape(size = 20.dp),
        border = BorderStroke(
            width = 1.dp,
            color = DarkBrownCrayonDark
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

private fun pickImage(context: Context, uri: Uri?, onImageSaved: () -> Unit) {
    uri?.let {
        // Read content from uri and write content to local file.
        context.contentResolver.openInputStream(uri)?.let { inputStream ->
            val outputStream = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "test.jpg").outputStream()
            val buf = ByteArray(1024)
            var len: Int
            while (inputStream.read(buf).also { len = it } > 0) {
                outputStream.write(buf, 0, len)
            }
            outputStream.close()
            inputStream.close()
            // Save file location in Room.
            onImageSaved()
        }
    }
}