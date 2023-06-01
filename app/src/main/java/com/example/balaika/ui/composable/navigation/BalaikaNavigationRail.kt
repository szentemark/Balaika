package com.example.balaika.ui.composable.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.balaika.ui.enums.TabNavigationItem

@Composable
fun BalaikaNavigationRail(
    currentTab: TabNavigationItem,
    onTabPressed: ((TabNavigationItem) -> Unit),
    navigationItemContentList: List<TabNavigationItem>,
    modifier: Modifier = Modifier
) {
    NavigationRail(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) {
        for (navigationItem in navigationItemContentList) {
            val text = stringResource(id = navigationItem.screen.title)
            val image = ImageVector.vectorResource(id = navigationItem.icon)

            NavigationRailItem(
                selected = currentTab == navigationItem,
                onClick = { onTabPressed(navigationItem) },
                icon = {
                    Icon(
                        imageVector = image,
                        contentDescription = text
                    )
                }
            )
        }
    }
}