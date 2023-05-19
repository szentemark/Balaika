package com.example.balaika.ui.composable.songlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.balaika.R
import com.example.balaika.ui.data.SongListItemData

@Composable
fun SongListItem(
    songListItemData: SongListItemData
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .fillMaxWidth(),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp, pressedElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.gabymorenopostales),
                    contentDescription = null,
                    modifier = Modifier.size(96.dp)
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
                Column {
                    Text(text = songListItemData.title, style = MaterialTheme.typography.titleMedium)
                    Text(text = songListItemData.author, style = MaterialTheme.typography.bodyMedium)
                    Text(text = songListItemData.lastPlayed, style = MaterialTheme.typography.bodySmall)
                    Text(text = songListItemData.averageLength, style = MaterialTheme.typography.bodySmall)
                }
            }
            Image(painter = painterResource(id = R.drawable.guitar_string_decoration), contentDescription = null)
        }
    }
}