package com.example.balaika.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.balaika.R
import com.example.balaika.ui.composable.BalaikaApp
import com.example.balaika.ui.enums.BalaikaScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            BalaikaApp(navController = navController)
        }
    }

    @Test
    fun balaikaNavHost_startScreen_atPlayRoomDestination() =
        navController.assertCurrentRouteName(BalaikaScreen.Playroom.name)

    @Test
    fun balaikaNavHost_startScreen_backNavigationNotShown() =
        composeTestRule.onNodeWithContentDescriptionId(R.string.back_button).assertDoesNotExist()

    @Test
    fun balaikaNavHost_tapAllSongsTab_backNavigationShown() {
        composeTestRule.onNodeWithTagId(R.string.page_all_songs).performClick()
        composeTestRule.onNodeWithContentDescriptionId(R.string.back_button).assertExists()
    }

    @Test
    fun balaikaNavHost_tapAllSongsTab_navigatesToAllSongsScreen() {
        composeTestRule.onNodeWithTagId(R.string.page_all_songs).performClick()
        navController.assertCurrentRouteName(BalaikaScreen.AllSongs.name)
    }

    @Test
    fun balaikaNavHost_tapSettingsTab_navigatesToSettingsScreen() {
        composeTestRule.onNodeWithTagId(R.string.page_settings).performClick()
        navController.assertCurrentRouteName(BalaikaScreen.Settings.name)
    }

    @Test
    fun balaikaNavHost_tapUpButton_navigatesBackToStartScreen() {
        composeTestRule.onNodeWithTagId(R.string.page_all_songs).performClick()
        composeTestRule.onNodeWithContentDescriptionId(R.string.back_button).performClick()
        navController.assertCurrentRouteName(BalaikaScreen.Playroom.name)
    }
}