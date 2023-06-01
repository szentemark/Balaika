package com.example.balaika.ui.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.balaika.R
import com.example.balaika.model.BalaikaDataStore
import com.example.balaika.model.Repository
import com.example.balaika.model.room.BalaikaDatabase
import com.example.balaika.ui.composable.navigation.BalaikaBottomNavigationBar
import com.example.balaika.ui.composable.navigation.BalaikaNavHostLandscape
import com.example.balaika.ui.composable.navigation.BalaikaNavHostPortrait
import com.example.balaika.ui.composable.navigation.BalaikaNavigationRail
import com.example.balaika.ui.composable.navigation.BalaikaTopAppBar
import com.example.balaika.ui.composable.navigation.WindowStructure
import com.example.balaika.ui.enums.BalaikaScreen
import com.example.balaika.ui.enums.TabNavigationItem
import com.example.balaika.ui.viewmodel.BalaikaViewModel
import com.example.balaika.ui.viewmodel.BalaikaViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BalaikaApp(
    navController: NavHostController,
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val windowStructure = when (windowSize) {
        WindowWidthSizeClass.Compact -> WindowStructure.PORTRAIT
        WindowWidthSizeClass.Medium -> WindowStructure.LANDSCAPE
        WindowWidthSizeClass.Expanded -> WindowStructure.LANDSCAPE
        else -> WindowStructure.PORTRAIT
    }

    /* when (windowStructure) {
        WindowStructure.PORTRAIT -> {
            BalaikaAppPortrait(navController = navController, modifier)
        }
        WindowStructure.LANDSCAPE -> {
            BalaikaAppLandscape(navController = navController, modifier)
        }
    } */

    val context = LocalContext.current.applicationContext
    val database = BalaikaDatabase.getDatabase(context)
    val dataStore = BalaikaDataStore(context)
    val repository = Repository(database, dataStore)
    val viewModel: BalaikaViewModel = viewModel {
        BalaikaViewModelFactory(repository).create(BalaikaViewModel::class.java)
    }

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
            if (windowStructure == WindowStructure.PORTRAIT) {
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
            }
        },
        floatingActionButton = {
            if (currentScreen == BalaikaScreen.AllSongs) {
                FloatingActionButton(
                    onClick = {
                        viewModel.createSong {
                            CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
                                navController.navigate(BalaikaScreen.SongEditor.name)
                            }
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
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
        when (windowStructure) {
            WindowStructure.PORTRAIT -> {
                BalaikaNavHostPortrait(
                    viewModel = viewModel,
                    navController = navController,
                    startEditing = {
                        viewModel.startEditingSong(it)
                        navController.navigate(BalaikaScreen.SongEditor.name)
                    },
                    modifier = modifier.padding(innerPadding)
                )
            }
            WindowStructure.LANDSCAPE -> {
                Row {
                    BalaikaNavigationRail(
                        currentTab = TabNavigationItem.Settings,
                        onTabPressed = {
                            if (it.name == TabNavigationItem.Playroom.name) {
                                navController.popBackStack(TabNavigationItem.Playroom.name, false)
                            } else {
                                navController.navigate(it.name)
                            }
                        },
                        navigationItemContentList = TabNavigationItem.values().toList()
                    )
                    BalaikaNavHostLandscape(
                        viewModel = viewModel,
                        navController = navController,
                        startEditing = {
                            viewModel.startEditingSong(it)
                            navController.navigate(BalaikaScreen.SongEditor.name)
                        },
                        modifier = modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}