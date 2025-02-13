package com.nicolascristaldo.myclients.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nicolascristaldo.myclients.ui.screens.clients.details.ClientDetailsScreen
import com.nicolascristaldo.myclients.ui.screens.clients.form.ClientFormScreen
import com.nicolascristaldo.myclients.ui.screens.clients.list.ClientsScreen
import com.nicolascristaldo.myclients.ui.screens.clients.list.ClientsScreenViewModel
import com.nicolascristaldo.myclients.ui.screens.home.HomeScreen
import com.nicolascristaldo.myclients.ui.screens.orders.form.OrderFormScreen
import com.nicolascristaldo.myclients.ui.screens.orders.list.OrdersScreen
import com.nicolascristaldo.myclients.ui.screens.orders.list.OrdersScreenViewModel
import com.nicolascristaldo.myclients.ui.screens.stats.StatsScreen

@Composable
fun MyClientsNavHost(
    clientsScreenViewModel: ClientsScreenViewModel,
    ordersScreenViewModel: OrdersScreenViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable(route = "home") {
            HomeScreen()
        }

        composable(route = "clients") {
            ClientsScreen()
        }

        composable(route = "orders") {
            OrdersScreen()
        }

        composable(route = "stats") {
            StatsScreen()
        }

        composable(route = "client/details") {
            ClientDetailsScreen()
        }

        composable(route = "client/form") {
            ClientFormScreen()
        }

        composable(route = "order/form") {
            OrderFormScreen()
        }
    }
}