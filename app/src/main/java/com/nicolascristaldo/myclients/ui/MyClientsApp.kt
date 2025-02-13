package com.nicolascristaldo.myclients.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nicolascristaldo.myclients.ui.navigation.MyClientsNavHost
import com.nicolascristaldo.myclients.ui.screens.clients.list.ClientsScreenViewModel
import com.nicolascristaldo.myclients.ui.screens.orders.list.OrdersScreenViewModel

@Composable
fun MyClientsApp(
    clientsScreenViewModel: ClientsScreenViewModel = hiltViewModel(),
    ordersScreenViewModel: OrdersScreenViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    Scaffold() { contentPadding ->
        Surface {
            MyClientsNavHost(
                clientsScreenViewModel = clientsScreenViewModel,
                ordersScreenViewModel = ordersScreenViewModel,
                navController = navController,
                modifier = modifier.padding(contentPadding)
            )
        }
    }
}