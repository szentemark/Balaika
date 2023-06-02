package com.example.balaika.ui.composable.songlist

import android.os.Environment
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.balaika.R
import com.example.balaika.mmSs
import com.example.balaika.model.room.entity.Song
import com.example.balaika.yyyyMmDd
import java.io.File

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SongListItem(
    song: Song,
    isHighlighted: Boolean,
    currentPlayLength: String,
    onClick: (Song) -> Unit,
    onLongClick: (Song) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = { onClick(song) },
                onLongClick = { onLongClick(song) }
            ),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.cardColors(
            containerColor = if (isHighlighted) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp, pressedElevation = 4.dp)
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
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier.height(128.dp)
                ) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(124.dp)
                            .clip(
                                CutCornerShape(
                                    topStart = 10.dp,
                                    topEnd = 0.dp,
                                    bottomStart = 0.dp,
                                    bottomEnd = 10.dp
                                )
                            )
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = song.title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSecondary)
                    Text(text = song.author, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSecondary)
                    Spacer(modifier = Modifier
                        .width(120.dp)
                        .height(17.dp)
                        .padding(vertical = 8.dp)
                        .background(MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.5f)))
                    val playCount = pluralStringResource(id = R.plurals.list_play_count, count = song.playCount, song.playCount)
                    Text(
                        text = playCount,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    val lastPlayed = stringResource(id = R.string.list_last_time, song.lastPlayed?.yyyyMmDd() ?: "-")
                    Text(
                        text = lastPlayed,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    val lengthString = stringResource(
                        id = R.string.list_song_length,
                        if (!isHighlighted) song.averageLength?.mmSs() ?: "-" else currentPlayLength
                    )
                    Text(text = lengthString, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSecondary)
                }
            }
            Image(painter = painterResource(id = R.drawable.guitar_string_decoration), contentDescription = null)
        }
    }
}