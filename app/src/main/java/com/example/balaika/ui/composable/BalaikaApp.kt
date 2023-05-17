package com.example.balaika.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.balaika.ui.composable.navigation.BalaikaBottomNavigationBar
import com.example.balaika.ui.composable.navigation.BalaikaNavHost
import com.example.balaika.ui.composable.navigation.BalaikaTopAppBar
import com.example.balaika.ui.enums.BalaikaScreen
import com.example.balaika.ui.enums.TabNavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BalaikaApp(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
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
        Column {
            BalaikaNavHost(
                navController = navController,
                modifier = modifier
                    .padding(innerPadding)
                    .weight(1f))
            BalaikaBottomNavigationBar(
                currentScreen = currentScreen,
                onTabPressed = { navController.navigate(it.name) },
                navigationItemContentList = TabNavigationItem.values().toList()
            )
        }
    }
}