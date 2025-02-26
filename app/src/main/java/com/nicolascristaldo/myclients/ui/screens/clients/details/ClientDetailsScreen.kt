package com.nicolascristaldo.myclients.ui.screens.clients.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.nicolascristaldo.myclients.R
import com.nicolascristaldo.myclients.ui.components.DeleteConfirmationDialog
import com.nicolascristaldo.myclients.ui.screens.clients.details.components.ClientHeader
import com.nicolascristaldo.myclients.ui.screens.clients.details.components.ClientStats
import com.nicolascristaldo.myclients.ui.screens.orders.list.OrdersScreen

@Composable
fun ClientDetailsScreen(
    viewModel: ClientDetailsViewModel,
    navController: NavHostController,
    onOrderClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val client by viewModel.client.collectAsState()
    val orders by viewModel.clientOrders.collectAsState()
    val totalPaid by viewModel.totalPaid.collectAsState()
    val totalPending by viewModel.totalPending.collectAsState()

    if (client != null) {
        Column(
            modifier = modifier
        ) {
            ClientHeader(
                client = client!!,
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_normal))
            )
            ClientStats(
                totalPaid = totalPaid,
                totalPending = totalPending,
                modifier = Modifier.fillMaxWidth()
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_normal))
            )
            OrdersScreen(
                orders = orders,
                onClick = onOrderClick
            )
        }
        if(viewModel.deleteAlertIsExpanded.value) {
            DeleteConfirmationDialog(
                content = stringResource(R.string.delete_client_question),
                onDismiss = { viewModel.setDeleteAlertExpanded(false) },
                onConfirm = {
                    viewModel.deleteClient()
                    navController.popBackStack()
                }
            )
        }
    }
}




