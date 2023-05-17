package com.example.balaika.ui.composable.songlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.balaika.R
import com.example.balaika.ui.data.SongListItemData

@Composable
fun SongListItem(
    songListItemData: SongListItemData
) {
    Row {
        Image(painter = painterResource(id = R.drawable.gabymorenopostales), contentDescription = null)
        Column {
            Text(text = songListItemData.title)
            Text(text = songListItemData.author)
            Text(text = songListItemData.lastPlayed)
            Text(text = songListItemData.averageLength)
        }
    }
}