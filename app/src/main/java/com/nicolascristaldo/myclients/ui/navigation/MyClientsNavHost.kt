package com.nicolascristaldo.myclients.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nicolascristaldo.myclients.R
import com.nicolascristaldo.myclients.ui.screens.clients.details.ClientDetailsScreen
import com.nicolascristaldo.myclients.ui.screens.clients.details.ClientDetailsViewModel
import com.nicolascristaldo.myclients.ui.screens.clients.form.ClientFormScreen
import com.nicolascristaldo.myclients.ui.screens.clients.form.ClientFormViewModel
import com.nicolascristaldo.myclients.ui.screens.clients.list.ClientsScreen
import com.nicolascristaldo.myclients.ui.screens.clients.list.ClientsScreenViewModel
import com.nicolascristaldo.myclients.ui.screens.home.HomeScreen
import com.nicolascristaldo.myclients.ui.screens.orders.form.OrderFormScreen
import com.nicolascristaldo.myclients.ui.screens.orders.form.OrderFormViewModel
import com.nicolascristaldo.myclients.ui.screens.orders.list.OrdersScreen
import com.nicolascristaldo.myclients.ui.screens.orders.list.OrdersScreenViewModel

@Composable
fun MyClientsNavHost(
    clientDetailsViewModel: ClientDetailsViewModel,
    clientsScreenViewModel: ClientsScreenViewModel = hiltViewModel(),
    ordersScreenViewModel: OrdersScreenViewModel = hiltViewModel(),
    clientFormViewModel: ClientFormViewModel = hiltViewModel(),
    orderFormViewModel: OrderFormViewModel = hiltViewModel(),
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
            val clients by clientsScreenViewModel.clients.collectAsState()

            ClientsScreen(
                clients = clients,
                onClientClick = {
                    navController.navigate(AppDestinations.ClientDetails.createRoute(id = it))
                },
                onSearchValueChange = {
                    clientsScreenViewModel.setSearchQuery(it)
                },
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_normal))
            )
        }

        composable(route = AppDestinations.Orders.route) {
            val orders by ordersScreenViewModel.orders.collectAsState()
            val totalEarned by ordersScreenViewModel.totalEarned.collectAsState()
            val totalPending by ordersScreenViewModel.totalPending.collectAsState()

            OrdersScreen(
                orders = orders,
                totalEarned = totalEarned,
                totalPending = totalPending,
                onClick = {
                    navController.navigate(AppDestinations.OrderFormEdit.createRoute(id = it))
                }
            )
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
                viewModel = clientDetailsViewModel,
                navController = navController,
                onOrderClick = {
                    navController.navigate(AppDestinations.OrderFormEdit.createRoute(id = it))
                },
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_normal))
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
                onButtonClick = {
                    clientFormViewModel.saveClient()
                    navController.popBackStack()
                },
                onValueChange = { clientFormViewModel.updateUiState(it) },
                buttonTitle = stringResource(R.string.save)
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
                onButtonClick = {
                    clientFormViewModel.updateClient()
                    navController.popBackStack()
                },
                onValueChange = { clientFormViewModel.updateUiState(it) },
                buttonTitle = stringResource(R.string.edit)
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
                onSave = {
                    orderFormViewModel.saveOrder()
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(
            route = AppDestinations.OrderFormEdit.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            LaunchedEffect(id) {
                if (id != 0) orderFormViewModel.retrieveOrder(id)
            }

            OrderFormScreen(
                orderUiState = orderFormViewModel.orderUiState,
                onValueChange = { orderFormViewModel.updateUiState(it) },
                onSave = {
                    orderFormViewModel.updateOrder()
                    navController.popBackStack()
                },
                deleteOrder = {
                    orderFormViewModel.deleteOrder()
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}