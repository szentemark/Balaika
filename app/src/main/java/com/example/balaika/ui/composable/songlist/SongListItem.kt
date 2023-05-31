package com.example.balaika.ui.composable.songlist

import android.os.Environment
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.balaika.R
import com.example.balaika.mmSs
import com.example.balaika.model.room.entity.Song
import com.example.balaika.ui.theme.WoodyBrownHighlighted
import com.example.balaika.ui.theme.WoodyCrayonWhiteFade
import com.example.balaika.yyyyMmDd
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongListItem(
    song: Song,
    isHighlighted: Boolean,
    currentPlayLength: String,
    onClick: (Song) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.cardColors(
            containerColor = if (isHighlighted) WoodyBrownHighlighted else MaterialTheme.colorScheme.secondary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp, pressedElevation = 4.dp),
        onClick = { onClick(song) }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxSize()
            ) {
                val painter = if (song.imageFile != "") {
                    val context = LocalContext.current
                    val imageFile = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), song.imageFile)
                    rememberAsyncImagePainter(imageFile)
                } else {
                    painterResource(id = R.drawable.image_placeholder)
                }
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(104.dp)
                        .clip(
                            CutCornerShape(
                                topStart = 10.dp,
                                topEnd = 0.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 10.dp
                            )
                        )
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = song.title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSecondary)
                    Text(text = song.author, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSecondary)
                    Spacer(modifier = Modifier.width(120.dp).height(17.dp).padding(vertical = 8.dp).background(WoodyCrayonWhiteFade))
                    val lastPlayedString = "Played: ${song.lastPlayed?.yyyyMmDd() ?: "-"}"
                    Text(text = lastPlayedString, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSecondary)
                    val lengthString = if (!isHighlighted) "Length: ${song.averageLength?.mmSs() ?: "-"}" else "Length: $currentPlayLength"
                    Text(text = lengthString, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSecondary)
                }
            }
            Image(painter = painterResource(id = R.drawable.guitar_string_decoration), contentDescription = null)
        }
    }
}