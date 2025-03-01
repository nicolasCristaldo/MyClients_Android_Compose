package com.nicolascristaldo.myclients.data.providers

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.nicolascristaldo.myclients.ui.navigation.AppDestinations

/**
 * Data class that represents a navigation item.
 * @property name The name of the navigation item.
 * @property selectedIcon The icon to display when the navigation item is selected.
 * @property unselectedIcon The icon to display when the navigation item is not selected.
 */
data class NavigationItem(
    val name: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

/**
 * Object that provides a list of navigation items.
 */
object NavigationItemsProvider {
    /**
     * List of available navigation items.
     */
    fun getNavigationItems(): List<NavigationItem> {
        return listOf(
            NavigationItem(
                name = AppDestinations.Home.route,
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            ),
            NavigationItem(
                name = AppDestinations.Clients.route,
                selectedIcon = Icons.Filled.Person,
                unselectedIcon = Icons.Outlined.Person
            ),
            NavigationItem(
                name = AppDestinations.Orders.route,
                selectedIcon = Icons.Filled.ShoppingCart,
                unselectedIcon = Icons.Outlined.ShoppingCart
            )
        )
    }
}
