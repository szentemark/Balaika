package com.example.balaika.ui.composable.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.balaika.ui.enums.BalaikaScreen
import com.example.balaika.ui.enums.TabNavigationItem

@Composable
fun BalaikaNavigationRail(
    currentScreen: BalaikaScreen,
    onTabPressed: ((TabNavigationItem) -> Unit),
    navigationItemContentList: List<TabNavigationItem>,
    modifier: Modifier = Modifier
) {
    NavigationRail(
        modifier = modifier.padding(top = 12.dp),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        for (navigationItem in navigationItemContentList) {
            val text = stringResource(id = navigationItem.screen.title)
            val image = ImageVector.vectorResource(id = navigationItem.icon)

            NavigationRailItem(
                selected = currentScreen == navigationItem.screen,
                onClick = { onTabPressed(navigationItem) },
                icon = {
                    Icon(
                        imageVector = image,
                        contentDescription = text
                    )
                },
                colors = NavigationRailItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    indicatorColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                )
            )
        }
    }
}