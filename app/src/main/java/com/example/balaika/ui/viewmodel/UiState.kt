package com.example.balaika.ui.viewmodel

import com.example.balaika.model.room.entity.Song
import com.example.balaika.ui.data.SongListItemData

data class UiState (
    val allSongs: List<SongListItemData>,
    val editedSong: Song
)