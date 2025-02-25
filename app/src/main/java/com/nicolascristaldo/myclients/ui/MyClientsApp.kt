package com.nicolascristaldo.myclients.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nicolascristaldo.myclients.ui.components.AppFab
import com.nicolascristaldo.myclients.ui.components.MyClientsBottomBar
import com.nicolascristaldo.myclients.ui.components.MyClientsTopAppBar
import com.nicolascristaldo.myclients.ui.navigation.AppDestinations
import com.nicolascristaldo.myclients.ui.navigation.MyClientsNavHost
import com.nicolascristaldo.myclients.ui.screens.clients.details.ClientDetailsViewModel

@Composable
fun MyClientsApp(
    clientDetailsViewModel: ClientDetailsViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = when (backStackEntry?.destination?.route) {
        AppDestinations.Clients.route -> AppDestinations.Clients
        AppDestinations.Orders.route -> AppDestinations.Orders
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
        bottomBar = { MyClientsBottomBar(navController = navController) },
        floatingActionButton = {
            if (
                currentScreen.route == AppDestinations.Clients.route ||
                currentScreen.route == AppDestinations.ClientDetails.route
            ) {
                AppFab(
                    onClick = {
                        if (currentScreen.route == AppDestinations.Clients.route){
                            navController.navigate(AppDestinations.ClientFormAdd.route)
                        }
                        else {
                            navController.navigate(
                                AppDestinations.OrderFormAdd.createRoute(
                                    clientDetailsViewModel.client.value?.id ?: 0
                                )
                            )
                        }
                    }
                )
            }
        }
    ) { contentPadding ->
        Surface {
            MyClientsNavHost(
                clientDetailsViewModel = clientDetailsViewModel,
                navController = navController,
                modifier = modifier.padding(contentPadding)
            )
        }
    }
}



