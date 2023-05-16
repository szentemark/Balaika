package com.example.balaika.ui.composable.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.sp
import com.example.balaika.ui.enums.BalaikaScreen
import com.example.balaika.ui.enums.BottomNavigationItem

@Composable
fun BalaikaBottomNavigationBar(
    currentScreen: BalaikaScreen,
    onTabPressed: ((BottomNavigationItem) -> Unit),
    navigationItemContentList: List<BottomNavigationItem>,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        for (navigationItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentScreen == navigationItem.screen,
                onClick = { onTabPressed(navigationItem) },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = navigationItem.icon),
                        contentDescription = stringResource(id = navigationItem.screen.title)
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = navigationItem.screen.title),
                        fontSize = 12.sp
                    )
                }
            )
        }
    }
}