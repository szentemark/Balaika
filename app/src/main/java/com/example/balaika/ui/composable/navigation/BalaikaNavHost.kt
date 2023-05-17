package com.example.balaika.ui.composable.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.balaika.ui.composable.songlist.SongList
import com.example.balaika.ui.enums.BalaikaScreen

@Composable
fun BalaikaNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BalaikaScreen.Playroom.name,
        modifier = modifier
    ) {
        composable(route = BalaikaScreen.Playroom.name) {
            SongList()
        }
        composable(route = BalaikaScreen.AllSongs.name) {
            Text(text = "Hello AllSongs!")
        }
        composable(route = BalaikaScreen.Settings.name) {
            Text(text = "Hello Settings!")
        }
    }
}