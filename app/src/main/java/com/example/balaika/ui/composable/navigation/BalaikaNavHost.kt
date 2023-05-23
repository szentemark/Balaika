package com.example.balaika.ui.composable.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.balaika.R
import com.example.balaika.ui.composable.editor.SongEditor
import com.example.balaika.ui.composable.songlist.SongList
import com.example.balaika.ui.enums.BalaikaScreen

@Composable
fun BalaikaNavHost(
    navController: NavHostController,
    startEditing: () -> Unit,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BalaikaScreen.Playroom.name,
        modifier = modifier
    ) {
        composable(route = BalaikaScreen.Playroom.name) {
            SongList(onClickListItem = {  })
        }
        composable(route = BalaikaScreen.AllSongs.name) {
            SongList(onClickListItem = { startEditing() })
        }
        composable(route = BalaikaScreen.Settings.name) {
            Text(text = stringResource(id = R.string.dummy_settings_text))
        }
        composable(route = BalaikaScreen.SongEditor.name) {
            SongEditor()
        }
    }
}