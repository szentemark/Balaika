package com.example.balaika.ui.composable.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.balaika.R
import com.example.balaika.model.room.entity.Song
import com.example.balaika.ui.composable.editor.SongEditor
import com.example.balaika.ui.composable.songlist.SongList
import com.example.balaika.ui.enums.BalaikaScreen
import com.example.balaika.ui.viewmodel.BalaikaViewModel

@Composable
fun BalaikaNavHost(
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
                songList = uiState.allSongs,
                onClickListItem = {  }
            )
        }
        composable(route = BalaikaScreen.AllSongs.name) {
            SongList(
                songList = uiState.allSongs,
                onClickListItem = { startEditing(it.song) }
            )
        }
        composable(route = BalaikaScreen.Settings.name) {
            Text(text = stringResource(id = R.string.dummy_settings_text))
        }
        composable(route = BalaikaScreen.SongEditor.name) {
            SongEditor(
                song = uiState.editedSong,
                newlyCreatedSong = uiState.newlyCreatedSong,
                viewModel = viewModel
            )
        }
    }
}