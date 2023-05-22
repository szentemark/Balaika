package com.example.balaika.ui.composable.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.balaika.ui.enums.BalaikaScreen
import com.example.balaika.ui.enums.TabNavigationItem

@Composable
fun BalaikaBottomNavigationBar(
    currentScreen: BalaikaScreen,
    onTabPressed: ((TabNavigationItem) -> Unit),
    navigationItemContentList: List<TabNavigationItem>,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        for (navigationItem in navigationItemContentList) {
            val text = stringResource(id = navigationItem.screen.title)
            val image = ImageVector.vectorResource(id = navigationItem.icon)

            NavigationBarItem(
                selected = currentScreen == navigationItem.screen,
                onClick = { onTabPressed(navigationItem) },
                icon = {
                    Icon(
                        imageVector = image,
                        contentDescription = text
                    )
                },
                label = {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.labelSmall
                        // fontSize = 12.sp
                    )
                },
                modifier = modifier.testTag(text),
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    indicatorColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                )
            )
        }
    }
}