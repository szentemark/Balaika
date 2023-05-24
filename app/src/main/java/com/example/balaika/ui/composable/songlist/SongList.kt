package com.example.balaika.ui.composable.songlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.balaika.R
import com.example.balaika.ui.data.SongListItemData
import com.example.balaika.ui.data.dummySongListData

@Composable
fun SongList(
    onClickListItem: (SongListItemData) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .testTag(stringResource(id = R.string.test_tag_song_list))
    ) {
        items(dummySongListData) {
            SongListItem(songListItemData = it, onClick = onClickListItem)
        }
    }
}