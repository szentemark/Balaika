package com.example.balaika.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.balaika.R
import com.example.balaika.ui.composable.navigation.BalaikaBottomNavigationBar
import com.example.balaika.ui.composable.navigation.BalaikaNavHost
import com.example.balaika.ui.composable.navigation.BalaikaTopAppBar
import com.example.balaika.ui.enums.BalaikaScreen
import com.example.balaika.ui.enums.TabNavigationItem
import com.example.balaika.ui.theme.CherryBrown
import com.example.balaika.ui.theme.CherryCrayonWhite

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
                navigateUp = { navController.navigateUp() },
                modifier = Modifier
                    .testTag(stringResource(id = R.string.test_tag_top_app_bar))
            )
        },
        bottomBar = {
            BalaikaBottomNavigationBar(
                currentScreen = currentScreen,
                onTabPressed = {
                    if (it.name == TabNavigationItem.Playroom.name) {
                        navController.popBackStack(TabNavigationItem.Playroom.name, false)
                    } else {
                        navController.navigate(it.name)
                    }
                },
                navigationItemContentList = TabNavigationItem.values().toList()
            )
        },
        floatingActionButton = {
            if (currentScreen == BalaikaScreen.AllSongs) {
                FloatingActionButton(
                    onClick = { navController.navigate(BalaikaScreen.SongEditor.name) },
                    containerColor = CherryBrown,
                    contentColor = CherryCrayonWhite
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_add_24),
                        contentDescription = stringResource(id = R.string.add_song_button)
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        BalaikaNavHost(
            navController = navController,
            startEditing = { navController.navigate(BalaikaScreen.SongEditor.name) },
            modifier = modifier.padding(innerPadding)
        )
    }
}