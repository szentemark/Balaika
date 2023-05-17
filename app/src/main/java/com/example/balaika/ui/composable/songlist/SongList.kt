package com.example.balaika.ui.composable.songlist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.example.balaika.R
import com.example.balaika.ui.data.dummySongListData

@Composable
fun SongList(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .testTag(stringResource(id = R.string.test_tag_song_list))
    ) {
        items(dummySongListData) {
            SongListItem(songListItemData = it)
        }
    }
}