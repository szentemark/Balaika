package com.example.balaika.ui.viewmodel

import com.example.balaika.model.room.entity.Song
import com.example.balaika.ui.data.SongListItemData
import java.time.ZonedDateTime

data class UiState (
    val allSongs: List<SongListItemData>,
    val editedSong: Song,
    val newlyCreatedSong: Boolean,
    val currentlyPlayedSong: Song? = null,
    val currentPlayStart: ZonedDateTime? = null
)