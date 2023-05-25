package com.example.balaika.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.balaika.model.Repository
import com.example.balaika.model.room.entity.Song
import com.example.balaika.ui.data.SongListItemData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BalaikaViewModel(private val repository: Repository): ViewModel() {

    private val _uiState = MutableStateFlow(UiState(allSongs = listOf(), editedSong = newSong()))
    val uiState: StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllSongs().collectLatest {
                val allSongs = it.map { song -> SongListItemData(
                    song = song,
                    title = song.title,
                    author = song.author,
                    lastPlayed = "Played: -",
                    averageLength = "Length: -"
                ) }
                _uiState.update { uiState -> uiState.copy(allSongs = allSongs) }
            }
        }
    }

    fun createSong(callback: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(editedSong = repository.insert(newSong())) }
            callback()
        }
    }

    fun startEditingSong(song: Song) = _uiState.update { it.copy(editedSong = song) }

    fun updateSong(updateFunction: (Song) -> Song) {
        _uiState.update { it.copy(editedSong = updateFunction(it.editedSong)) }
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(_uiState.value.editedSong)
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
        showInPlayroom = true
    )
}