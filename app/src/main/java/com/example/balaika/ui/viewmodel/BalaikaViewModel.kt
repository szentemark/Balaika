package com.example.balaika.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.balaika.mmSs
import com.example.balaika.model.Repository
import com.example.balaika.model.room.entity.Play
import com.example.balaika.model.room.entity.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.ZonedDateTime

class BalaikaViewModel(private val repository: Repository): ViewModel() {

    private val _uiState = MutableStateFlow(UiState(allSongs = listOf(), editedSong = newSong(), newlyCreatedSong = true))
    val uiState: StateFlow<UiState> = _uiState

    init {
        // Load all songs list from repository.
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllSongs().collectLatest {
                _uiState.update { uiState -> uiState.copy(allSongs = it) }
            }
        }
        // Start ticker for playing songs.
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                delay(100)
                _uiState.value.currentPlayStart?.let { currentPlayStart ->
                    val duration = Duration.between(currentPlayStart, ZonedDateTime.now())
                    _uiState.update { it.copy(currentPlayLength = duration.mmSs()) }
                }
            }
        }
    }

    fun createSong(callback: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(editedSong = repository.insert(newSong()), newlyCreatedSong = true) }
            callback()
        }
    }

    fun startEditingSong(song: Song) = _uiState.update { it.copy(editedSong = song, newlyCreatedSong = false) }

    fun updateSong(updateFunction: (Song) -> Song) {
        _uiState.update { it.copy(editedSong = updateFunction(it.editedSong)) }
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(_uiState.value.editedSong)
        }
    }

    fun startStopSong(song: Song) {
        when (_uiState.value.currentlyPlayedSong?.id) {
            song.id -> {
                // Update the repository.
                updateRepositoryAfterPlay(song)
                // Stop the currently playing song.
                _uiState.update { it.copy(currentlyPlayedSong = null, currentPlayStart = null, currentPlayLength = "") }
            }
            null -> {
                // Start playing the selected song.
                _uiState.update { it.copy(currentlyPlayedSong = song, currentPlayStart = ZonedDateTime.now()) }
            }
            else -> {
                // The user tapped a song while playing another one, we do nothing.
            }
        }
    }

    private fun updateRepositoryAfterPlay(song: Song) {
        val currentPlayFrom = _uiState.value.currentPlayStart ?: return
        viewModelScope.launch(Dispatchers.IO) {
            val currentPlayTill = ZonedDateTime.now()
            // Insert new play entry.
            repository.insert(Play(
                id = 0,
                songId = song.id,
                from = currentPlayFrom,
                till = currentPlayTill
            ))
            // Get average play length.
            val playsForSong = repository.getPlaysForSong(song.id)
            val averagePlayLength = if (playsForSong.isEmpty()) Duration.ZERO else {
                Duration.ofMillis(
                    playsForSong.map { Duration.between(it.from, it.till).toMillis() }.average().toLong()
                )
            }
            // Update song entry.
            repository.update(song.copy(
                lastPlayed = currentPlayFrom,
                averageLength = averagePlayLength
            ))
        }
    }

    private fun newSong() = Song(
        id = 0,
        title = "",
        author = "",
        imageFile = "",
        scrumming = 1,
        pick = true,
        leftHandHeavy = false,
        featureSong = false,
        showInPlayroom = true,
        lastPlayed = null,
        averageLength = null
    )
}