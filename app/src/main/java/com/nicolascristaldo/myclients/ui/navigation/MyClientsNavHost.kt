package com.nicolascristaldo.myclients.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nicolascristaldo.myclients.ui.screens.clients.details.ClientDetailsScreen
import com.nicolascristaldo.myclients.ui.screens.clients.form.ClientFormScreen
import com.nicolascristaldo.myclients.ui.screens.clients.form.ClientFormViewModel
import com.nicolascristaldo.myclients.ui.screens.clients.list.ClientsScreen
import com.nicolascristaldo.myclients.ui.screens.clients.list.ClientsScreenViewModel
import com.nicolascristaldo.myclients.ui.screens.home.HomeScreen
import com.nicolascristaldo.myclients.ui.screens.orders.form.OrderFormScreen
import com.nicolascristaldo.myclients.ui.screens.orders.form.OrderFormViewModel
import com.nicolascristaldo.myclients.ui.screens.orders.list.OrdersScreen
import com.nicolascristaldo.myclients.ui.screens.orders.list.OrdersScreenViewModel
import com.nicolascristaldo.myclients.ui.screens.stats.StatsScreen

@Composable
fun MyClientsNavHost(
    clientsScreenViewModel: ClientsScreenViewModel,
    ordersScreenViewModel: OrdersScreenViewModel,
    clientFormViewModel: ClientFormViewModel,
    orderFormViewModel: OrderFormViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.Home.route,
        modifier = modifier
    ) {
        composable(route = AppDestinations.Home.route) {
            HomeScreen(
                navController = navController
            )
        }

        composable(route = AppDestinations.Clients.route) {
            ClientsScreen()
        }

        composable(route = AppDestinations.Orders.route) {
            OrdersScreen()
        }

        composable(route = AppDestinations.Stats.route) {
            StatsScreen()
        }

        composable(
            route = AppDestinations.ClientDetails.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            LaunchedEffect(id) {
                //getDetails(id)
            }

            ClientDetailsScreen()
        }

        composable(
            route = AppDestinations.ClientFormAdd.route
        ) {
            ClientFormScreen(
                clientUiState = clientFormViewModel.clientUiState,
                onClick = { clientFormViewModel.saveClient() },
                onValueChange = { clientFormViewModel.updateUiState(it) },
                buttonTitle = "Save"
            )
        }

        composable(
            route = AppDestinations.ClientFormEdit.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            LaunchedEffect(id) {
                if (id != 0) clientFormViewModel.retrieveClient(id)
            }

            ClientFormScreen(
                clientUiState = clientFormViewModel.clientUiState,
                onClick = { clientFormViewModel.updateClient() },
                onValueChange = { clientFormViewModel.updateUiState(it) },
                buttonTitle = "Edit"
            )
        }

        composable(
            route = AppDestinations.OrderFormAdd.route
        ) {
            OrderFormScreen()
        }

        composable(
            route = AppDestinations.OrderFormEdit.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            LaunchedEffect(id) {
                //if(id != 0) retrieveOrder(id)
            }

            OrderFormScreen()
        }
    }
}