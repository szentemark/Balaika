package com.example.balaika.ui.viewmodel

import com.example.balaika.model.room.entity.Song
import java.time.ZonedDateTime

data class UiState (
    val allSongs: List<Song> = listOf(),
    val playroomSongs: List<Song> = listOf(),
    val editedSong: Song? = null,
    val newlyCreatedSong: Boolean = false,
    val currentlyPlayedSong: Song? = null,
    val currentPlayStart: ZonedDateTime? = null,
    val currentPlayLength: String = "",
    val setupFeatureOnly: Boolean = false,
    val setupHandPickOnly: Boolean = false,
    val setupNoScrumming: Boolean = false
)