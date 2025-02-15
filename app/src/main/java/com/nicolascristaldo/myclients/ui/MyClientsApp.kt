package com.nicolascristaldo.myclients.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nicolascristaldo.myclients.ui.navigation.AppDestinations
import com.nicolascristaldo.myclients.ui.navigation.MyClientsNavHost
import com.nicolascristaldo.myclients.ui.screens.clients.form.ClientFormViewModel
import com.nicolascristaldo.myclients.ui.screens.clients.list.ClientsScreenViewModel
import com.nicolascristaldo.myclients.ui.screens.orders.form.OrderFormViewModel
import com.nicolascristaldo.myclients.ui.screens.orders.list.OrdersScreenViewModel

@Composable
fun MyClientsApp(
    clientsScreenViewModel: ClientsScreenViewModel = hiltViewModel(),
    ordersScreenViewModel: OrdersScreenViewModel = hiltViewModel(),
    clientFormViewModel: ClientFormViewModel = hiltViewModel(),
    orderFormViewModel: OrderFormViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = when(backStackEntry?.destination?.route) {
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
                navigateBack = { navController.navigateUp() }
            )
        }
    ) { contentPadding ->
        Surface {
            MyClientsNavHost(
                clientsScreenViewModel = clientsScreenViewModel,
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
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(stringResource(currentScreen.title))
        },
        navigationIcon = {
            if(
                currentScreen.route != AppDestinations.Home.route &&
                currentScreen.route != AppDestinations.Clients.route &&
                currentScreen.route != AppDestinations.Orders.route
            ) {
                IconButton(
                    onClick = { navigateBack() }
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