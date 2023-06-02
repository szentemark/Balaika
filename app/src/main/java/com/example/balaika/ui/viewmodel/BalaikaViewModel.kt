package com.example.balaika.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.balaika.mmSs
import com.example.balaika.model.BalaikaDataStore
import com.example.balaika.model.Repository
import com.example.balaika.model.room.entity.Play
import com.example.balaika.model.room.entity.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.ZonedDateTime

class BalaikaViewModel(private val repository: Repository): ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    init {
        // Load all songs list from repository.
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllSongs().collectLatest {
                val playroomSongs = it
                    .asSequence()
                    .filter { song -> song.showInPlayroom }
                    .filter { song -> song.featureSong || !_uiState.value.setupFeatureOnly }
                    .filter { song -> !song.pick || !_uiState.value.setupHandPickOnly }
                    .filter { song -> song.scrumming < 3 || !_uiState.value.setupNoScrumming }
                    .sortedBy { song -> song.lastPlayed }
                    .toList()
                _uiState.update { uiState -> uiState.copy(allSongs = it, playroomSongs = playroomSongs) }
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
        viewModelScope.launch(Dispatchers.IO) {
            repository.setupValues[BalaikaDataStore.DataType.FEATURE_ONLY]?.collect { newValue ->
                val newUiState = _uiState.updateAndGet { it.copy(setupFeatureOnly = newValue) }
                updatePlayroomSongList(newUiState)
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.setupValues[BalaikaDataStore.DataType.HAND_PICK_ONLY]?.collect { newValue ->
                val newUiState = _uiState.updateAndGet { it.copy(setupHandPickOnly = newValue) }
                updatePlayroomSongList(newUiState)
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.setupValues[BalaikaDataStore.DataType.NO_SCRUMMING]?.collect { newValue ->
                val newUiState = _uiState.updateAndGet { it.copy(setupNoScrumming = newValue) }
                updatePlayroomSongList(newUiState)
            }
        }
    }

    private fun updatePlayroomSongList(uiState: UiState) {
        val playroomSongs = uiState.allSongs
            .asSequence()
            .filter { song -> song.showInPlayroom }
            .filter { song -> song.featureSong || !_uiState.value.setupFeatureOnly }
            .filter { song -> !song.pick || !_uiState.value.setupHandPickOnly }
            .filter { song -> song.scrumming < 3 || !_uiState.value.setupNoScrumming }
            .sortedBy { song -> song.lastPlayed }
            .toList()
        _uiState.update { it.copy(playroomSongs = playroomSongs) }
    }

    fun createSong() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(editedSong = repository.insert(newSong()), newlyCreatedSong = true) }
        }
    }

    fun startEditingSong(song: Song) = _uiState.update { it.copy(editedSong = song, newlyCreatedSong = false) }

    fun doneEditingSong() = _uiState.update { it.copy(editedSong = null) }

    fun isEditingSong() = _uiState.value.editedSong != null

    fun updateSong(updateFunction: (Song) -> Song) {
        _uiState.value.editedSong?.let { editedSong ->
            _uiState.update { it.copy(editedSong = updateFunction(editedSong)) }
            viewModelScope.launch(Dispatchers.IO) {
                repository.update(editedSong)
            }
        }
    }

    fun deleteSong(song: Song) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(song)
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

    fun cancelPlay(song: Song) {
        if (_uiState.value.currentlyPlayedSong?.id == song.id) {
            // Cancel the currently playing song without saving it.
            _uiState.update { it.copy(currentlyPlayedSong = null, currentPlayStart = null, currentPlayLength = "") }
        }
    }

    fun updateSetup(key: BalaikaDataStore.DataType, value: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateSetup(key, value)
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
                averageLength = averagePlayLength,
                playCount = playsForSong.size
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
        averageLength = null,
        playCount = 0
    )
}