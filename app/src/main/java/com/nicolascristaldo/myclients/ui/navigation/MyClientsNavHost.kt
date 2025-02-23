package com.nicolascristaldo.myclients.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nicolascristaldo.myclients.ui.screens.clients.details.ClientDetailsScreen
import com.nicolascristaldo.myclients.ui.screens.clients.details.ClientDetailsViewModel
import com.nicolascristaldo.myclients.ui.screens.clients.form.ClientFormScreen
import com.nicolascristaldo.myclients.ui.screens.clients.form.ClientFormViewModel
import com.nicolascristaldo.myclients.ui.screens.clients.list.ClientsScreen
import com.nicolascristaldo.myclients.ui.screens.home.HomeScreen
import com.nicolascristaldo.myclients.ui.screens.orders.form.OrderFormScreen
import com.nicolascristaldo.myclients.ui.screens.orders.form.OrderFormViewModel
import com.nicolascristaldo.myclients.ui.screens.orders.list.OrdersScreen
import com.nicolascristaldo.myclients.ui.screens.orders.list.OrdersScreenViewModel
import com.nicolascristaldo.myclients.ui.screens.stats.StatsScreen

@Composable
fun MyClientsNavHost(
    clientDetailsViewModel: ClientDetailsViewModel,
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
            ClientsScreen(
                onClientClick = {
                    navController.navigate(AppDestinations.ClientDetails.createRoute(id = it))
                }
            )
        }

        composable(route = AppDestinations.Orders.route) {
            val orders by ordersScreenViewModel.orders.collectAsState()
            OrdersScreen(
                orders = orders,
                onClick = {
                    navController.navigate(AppDestinations.OrderFormEdit.createRoute(id = it))
                }
            )
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
                clientDetailsViewModel.getClient(id)
                clientDetailsViewModel.getOrdersByCustomerId(id)
                clientDetailsViewModel.getTotalPaid(id)
                clientDetailsViewModel.getTotalPending(id)
            }

            ClientDetailsScreen(
                onEditClick = {
                    navController.navigate(AppDestinations.ClientFormEdit.createRoute(id = it))
                },
                viewModel = clientDetailsViewModel,
                onAddOrderClick = {
                    navController.navigate(AppDestinations.OrderFormAdd.createRoute(customerId = id))
                },
                onOrderClick = {
                    navController.navigate(AppDestinations.OrderFormEdit.createRoute(id = it))
                }
            )
        }

        composable(
            route = AppDestinations.ClientFormAdd.route
        ) {
            LaunchedEffect(Unit) {
                clientFormViewModel.resetUiState()
            }

            ClientFormScreen(
                clientUiState = clientFormViewModel.clientUiState,
                onClick = {
                    clientFormViewModel.saveClient()
                    navController.popBackStack()
                },
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
                onClick = {
                    clientFormViewModel.updateClient()
                    navController.popBackStack()
                },
                onValueChange = { clientFormViewModel.updateUiState(it) },
                buttonTitle = "Edit"
            )
        }

        composable(
            route = AppDestinations.OrderFormAdd.route,
            arguments = listOf(navArgument("customerId") { type = NavType.IntType })
        ) { backStackEntry ->
            val customerId = backStackEntry.arguments?.getInt("customerId") ?: 0
            LaunchedEffect(customerId) {
                orderFormViewModel.resetUiState()
                if (customerId != 0) {
                    orderFormViewModel.updateUiState(
                        orderFormViewModel.orderUiState.orderDetails.copy(customerId = customerId)
                    )
                }
            }

            OrderFormScreen(
                orderUiState = orderFormViewModel.orderUiState,
                onValueChange = { orderFormViewModel.updateUiState(it) },
                onClick = {
                    orderFormViewModel.saveOrder()
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = AppDestinations.OrderFormEdit.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            LaunchedEffect(id) {
                if(id != 0) orderFormViewModel.retrieveOrder(id)
            }

            OrderFormScreen(
                orderUiState = orderFormViewModel.orderUiState,
                onValueChange = { orderFormViewModel.updateUiState(it) },
                onClick = {
                    orderFormViewModel.updateOrder()
                    navController.popBackStack()
                },
                onDelete = {
                    orderFormViewModel.deleteOrder()
                    navController.popBackStack()
                }
            )
        }
    }
}