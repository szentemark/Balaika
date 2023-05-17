package com.example.balaika.ui.composable.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.sp
import com.example.balaika.ui.enums.BalaikaScreen
import com.example.balaika.ui.enums.TabNavigationItem

@Composable
fun BalaikaBottomNavigationBar(
    currentScreen: BalaikaScreen,
    onTabPressed: ((TabNavigationItem) -> Unit),
    navigationItemContentList: List<TabNavigationItem>,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
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
                        fontSize = 12.sp
                    )
                },
                modifier = modifier.testTag(text)
            )
        }
    }
}