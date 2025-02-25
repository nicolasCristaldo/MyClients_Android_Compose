package com.nicolascristaldo.myclients.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.nicolascristaldo.myclients.data.providers.NavigationItemsProvider

@Composable
fun MyClientsBottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navigationItems = NavigationItemsProvider.getNavigationItems()
    var currentScreen by remember { mutableIntStateOf(0) }

    NavigationBar(
        modifier = modifier
    ) {
        navigationItems.forEachIndexed { index, navigationItem ->
            val selected = index == currentScreen
            NavigationBarItem(
                selected = selected,
                onClick = {
                    currentScreen = index
                    navController.navigate(navigationItem.name)
                },
                icon = {
                    Icon(
                        imageVector = if (selected) navigationItem.selectedIcon
                        else navigationItem.unselectedIcon,
                        contentDescription = navigationItem.name
                    )
                },
                label = {
                    Text(text = navigationItem.name)
                }
            )
        }
    }
}