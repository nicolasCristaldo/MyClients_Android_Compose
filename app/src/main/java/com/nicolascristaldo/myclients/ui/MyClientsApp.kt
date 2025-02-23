package com.nicolascristaldo.myclients.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nicolascristaldo.myclients.data.providers.NavigationItemsProvider
import com.nicolascristaldo.myclients.ui.navigation.AppDestinations
import com.nicolascristaldo.myclients.ui.navigation.MyClientsNavHost
import com.nicolascristaldo.myclients.ui.screens.clients.details.ClientDetailsViewModel
import com.nicolascristaldo.myclients.ui.screens.clients.form.ClientFormViewModel
import com.nicolascristaldo.myclients.ui.screens.orders.form.OrderFormViewModel
import com.nicolascristaldo.myclients.ui.screens.orders.list.OrdersScreenViewModel

@Composable
fun MyClientsApp(
    clientDetailsViewModel: ClientDetailsViewModel = hiltViewModel(),
    ordersScreenViewModel: OrdersScreenViewModel = hiltViewModel(),
    clientFormViewModel: ClientFormViewModel = hiltViewModel(),
    orderFormViewModel: OrderFormViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = when (backStackEntry?.destination?.route) {
        AppDestinations.Clients.route -> AppDestinations.Clients
        AppDestinations.Orders.route -> AppDestinations.Orders
        AppDestinations.Stats.route -> AppDestinations.Stats
        AppDestinations.ClientDetails.route -> AppDestinations.ClientDetails
        AppDestinations.ClientFormAdd.route -> AppDestinations.ClientFormAdd
        AppDestinations.ClientFormEdit.route -> AppDestinations.ClientFormEdit
        AppDestinations.OrderFormAdd.route -> AppDestinations.OrderFormAdd
        AppDestinations.OrderFormEdit.route -> AppDestinations.OrderFormEdit
        else -> AppDestinations.Home
    }

    Scaffold(
        topBar = {
            MyClientsTopAppBar(
                currentScreen = currentScreen,
                navController = navController
            )
        },
        bottomBar = {
            MyClientsBottomBar(
                navController = navController
            )
        },
        floatingActionButton = {
            if (
                currentScreen.route == AppDestinations.Clients.route ||
                currentScreen.route == AppDestinations.ClientDetails.route
            ) {
                FloatingActionButton(
                    onClick = {
                        if (currentScreen.route == AppDestinations.Clients.route){
                            navController.navigate(AppDestinations.ClientFormAdd.route)
                        }
                        else {
                            navController.navigate(AppDestinations.OrderFormAdd.createRoute(
                                clientDetailsViewModel.client.value?.id ?: 0
                            ))
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "add"
                    )
                }
            }
        }
    ) { contentPadding ->
        Surface {
            MyClientsNavHost(
                clientDetailsViewModel = clientDetailsViewModel,
                ordersScreenViewModel = ordersScreenViewModel,
                clientFormViewModel = clientFormViewModel,
                orderFormViewModel = orderFormViewModel,
                navController = navController,
                modifier = modifier.padding(contentPadding)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyClientsTopAppBar(
    currentScreen: AppDestinations,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    CenterAlignedTopAppBar(
        title = {
            Text(stringResource(currentScreen.title))
        },
        actions = {
            if (currentScreen.route == AppDestinations.ClientDetails.route) {
                IconButton(
                    onClick = { expanded = !expanded }
                ) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "options"
                    )
                    ClientOptionsDropDownMenu(
                        expanded = expanded,
                        expandedChange = { expanded = false },
                        navController = navController
                    )
                }
            }
        },
        navigationIcon = {
            if (
                currentScreen.route != AppDestinations.Home.route &&
                currentScreen.route != AppDestinations.Clients.route &&
                currentScreen.route != AppDestinations.Orders.route
            ) {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "go back"
                    )
                }
            }
        },
        modifier = modifier
    )
}

@Composable
fun MyClientsBottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navigationItems = remember { NavigationItemsProvider.getNavigationItems() }
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
                        imageVector = if (selected) navigationItem.selectedIcon else navigationItem.unselectedIcon,
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

@Composable
fun ClientOptionsDropDownMenu(
    clientDetailsViewModel: ClientDetailsViewModel = hiltViewModel(),
    navController: NavHostController,
    expanded: Boolean,
    expandedChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expandedChange() },
        modifier = modifier
    ) {
        DropdownMenuItem(
            text = { Text("Edit") },
            onClick = {
                expandedChange()
                navController.navigate(AppDestinations.ClientFormEdit.createRoute(
                        clientDetailsViewModel.client.value?.id ?: 0)
                )
            }
        )
        HorizontalDivider()
        DropdownMenuItem(
            text = { Text("Delete") },
            onClick = {
                expandedChange()
                clientDetailsViewModel.deleteClient()
                navController.popBackStack()
            }
        )
    }
}