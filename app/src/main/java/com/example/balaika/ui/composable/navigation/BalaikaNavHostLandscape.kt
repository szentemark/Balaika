package com.example.balaika.ui.composable.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.balaika.model.room.entity.Song
import com.example.balaika.ui.composable.Setup
import com.example.balaika.ui.composable.editor.SongEditor
import com.example.balaika.ui.composable.songlist.SongList
import com.example.balaika.ui.enums.BalaikaScreen
import com.example.balaika.ui.viewmodel.BalaikaViewModel

@Composable
fun BalaikaNavHostLandscape(
    viewModel: BalaikaViewModel,
    navController: NavHostController,
    startEditing: (Song) -> Unit,
    modifier: Modifier
) {
    val uiState = viewModel.uiState.collectAsState().value

    NavHost(
        navController = navController,
        startDestination = BalaikaScreen.Playroom.name,
        modifier = modifier
    ) {
        composable(route = BalaikaScreen.Playroom.name) {
            SongList(
                songList = uiState.playroomSongs,
                highlightedSong = uiState.currentlyPlayedSong,
                currentPlayLength = uiState.currentPlayLength,
                onClickListItem = { viewModel.startStopSong(it) },
                onLongClickListItem = { viewModel.cancelPlay(it) }
            )
        }
        composable(route = BalaikaScreen.AllSongs.name) {
            if (uiState.editedSong == null) {
                SongList(
                    songList = uiState.allSongs,
                    highlightedSong = null,
                    currentPlayLength = "",
                    onClickListItem = { startEditing(it) },
                    onLongClickListItem = { viewModel.deleteSong(it) }
                )
            } else {
                Row {
                    SongList(
                        songList = uiState.allSongs,
                        highlightedSong = null,
                        currentPlayLength = "",
                        onClickListItem = { startEditing(it) },
                        onLongClickListItem = { viewModel.deleteSong(it) },
                        modifier = Modifier.weight(0.5f)
                    )
                    SongEditor(
                        song = uiState.editedSong,
                        newlyCreatedSong = uiState.newlyCreatedSong,
                        viewModel = viewModel,
                        modifier = Modifier.weight(0.5f)
                    )
                }
            }
        }
        composable(route = BalaikaScreen.Settings.name) {
            Setup(viewModel)
        }
    }
}