package com.example.balaika

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.balaika.ui.theme.BalaikaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BalaikaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BalaikaApp()
                }
            }
        }
    }
}

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
            BalaikaAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        // UiState definition.
        // ...

        Column() {
            NavHost(
                navController = navController,
                startDestination = BalaikaScreen.Playroom.name,
                modifier = modifier
                    .padding(innerPadding)
                    .weight(1f)
            ) {
                composable(route = BalaikaScreen.Playroom.name) {
                    Text(
                        text = "Hello Playroom!",
                        modifier = modifier.padding(innerPadding)
                    )
                }
                composable(route = BalaikaScreen.AllSongs.name) {
                    Text(
                        text = "Hello AllSongs!",
                        modifier = modifier.padding(innerPadding)
                    )
                }
                composable(route = BalaikaScreen.Settings.name) {
                    Text(
                        text = "Hello Settings!",
                        modifier = modifier.padding(innerPadding)
                    )
                }
            }
            BalaikaBottomNavigationBar(
                currentTab = currentScreen,
                onTabPressed = { balaikaScreen -> navController.navigate(balaikaScreen.name) },
                navigationItemContentList = listOf(BalaikaScreen.Playroom, BalaikaScreen.AllSongs, BalaikaScreen.Settings)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BalaikaTheme {
        BalaikaApp()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BalaikaAppBar(
    currentScreen: BalaikaScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}


@Composable
private fun BalaikaBottomNavigationBar(
    currentTab: BalaikaScreen,
    onTabPressed: ((BalaikaScreen) -> Unit),
    navigationItemContentList: List<BalaikaScreen>,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentTab == navItem,
                onClick = { onTabPressed(navItem) },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = navItem.icon),
                        contentDescription = stringResource(id = navItem.title)
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = navItem.title),
                        fontSize = 12.sp
                    )
                }
            )
        }
    }
}

enum class BalaikaScreen(@StringRes val title: Int, @DrawableRes val icon: Int) {
    Playroom(title = R.string.page_playroom, icon = R.drawable.baseline_music_note_24),
    AllSongs(title = R.string.page_all_songs, icon = R.drawable.baseline_format_list_bulleted_24),
    Settings(title = R.string.page_settings, icon = R.drawable.baseline_settings_24)
}