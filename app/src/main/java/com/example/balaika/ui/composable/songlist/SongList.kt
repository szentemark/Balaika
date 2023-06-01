package com.example.balaika.ui.composable.songlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.balaika.R
import com.example.balaika.model.room.entity.Song

@Composable
fun SongList(
    songList: List<Song>,
    highlightedSong: Song?,
    currentPlayLength: String,
    onClickListItem: (Song) -> Unit,
    onLongClickListItem: (Song) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 300.dp),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .testTag(stringResource(id = R.string.test_tag_song_list))
    ) {
        items(songList.size) {
            SongListItem(
                song = songList[it],
                isHighlighted = songList[it].id == highlightedSong?.id,
                currentPlayLength = if (songList[it].id == highlightedSong?.id) currentPlayLength else "",
                onClick = onClickListItem,
                onLongClick = onLongClickListItem
            )
        }
    }
}