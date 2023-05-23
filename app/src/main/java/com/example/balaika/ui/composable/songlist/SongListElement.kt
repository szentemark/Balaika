package com.example.balaika.ui.composable.songlist

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.balaika.R
import com.example.balaika.ui.data.SongListItemData
import com.example.balaika.ui.theme.WoodyCrayonWhiteFade

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongListItem(
    songListItemData: SongListItemData,
    onClick: (SongListItemData) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp, pressedElevation = 4.dp),
        onClick = { onClick(songListItemData) }
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
                Image(
                    painter = painterResource(id = R.drawable.gabymorenopostales),
                    contentDescription = null,
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
                    Text(text = songListItemData.title, style = MaterialTheme.typography.titleMedium)
                    Text(text = songListItemData.author, style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.width(120.dp).height(17.dp).padding(vertical = 8.dp).background(WoodyCrayonWhiteFade))
                    Text(text = songListItemData.lastPlayed, style = MaterialTheme.typography.bodySmall)
                    Text(text = songListItemData.averageLength, style = MaterialTheme.typography.bodySmall)
                }
            }
            Image(painter = painterResource(id = R.drawable.guitar_string_decoration), contentDescription = null)
        }
    }
}