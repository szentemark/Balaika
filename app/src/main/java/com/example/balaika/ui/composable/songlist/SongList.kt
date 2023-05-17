package com.example.balaika.ui.composable.songlist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.balaika.ui.data.dummySongListData

@Composable
fun SongList() {
    LazyColumn {
        items(dummySongListData) {
            SongListItem(songListItemData = it)
        }
    }
}