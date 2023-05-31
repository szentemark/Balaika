package com.example.balaika.ui.viewmodel

import com.example.balaika.model.room.entity.Song
import java.time.ZonedDateTime

data class UiState (
    val allSongs: List<Song> = listOf(),
    val playroomSongs: List<Song> = listOf(),
    val editedSong: Song,
    val newlyCreatedSong: Boolean,
    val currentlyPlayedSong: Song? = null,
    val currentPlayStart: ZonedDateTime? = null,
    val currentPlayLength: String = ""
)