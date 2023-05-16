package com.example.balaika.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.balaika.ui.composable.navigation.BalaikaBottomNavigationBar
import com.example.balaika.ui.composable.navigation.BalaikaNavHost
import com.example.balaika.ui.composable.navigation.BalaikaTopAppBar
import com.example.balaika.ui.enums.BalaikaScreen
import com.example.balaika.ui.enums.BottomNavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BalaikaApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = BalaikaScreen.valueOf(
        backStackEntry?.destination?.route ?: BalaikaScreen.Playroom.name
    )

    Scaffold(
        topBar = {
            BalaikaTopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        // UiState definition.
        // ...

        Column {
            BalaikaNavHost(
                navController = navController,
                modifier = modifier
                    .padding(innerPadding)
                    .weight(1f))
            BalaikaBottomNavigationBar(
                currentScreen = currentScreen,
                onTabPressed = { navigationItem: BottomNavigationItem -> navController.navigate(navigationItem.name) },
                navigationItemContentList = listOf(BottomNavigationItem.Playroom, BottomNavigationItem.AllSongs, BottomNavigationItem.Settings)
            )
        }
    }
}