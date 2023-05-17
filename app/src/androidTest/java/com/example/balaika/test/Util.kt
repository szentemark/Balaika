package com.example.balaika.test

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.NavController
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Assert

fun NavController.assertCurrentRouteName(expectedRouteName: String) =
    Assert.assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)

fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithTagId(
    @StringRes id: Int
) = onNodeWithTag(activity.getString(id))

fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithContentDescriptionId(
    @StringRes id: Int
) = onNodeWithContentDescription(activity.getString(id))