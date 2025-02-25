package com.nicolascristaldo.myclients.data.providers

import androidx.navigation.NavHostController
import com.nicolascristaldo.myclients.ui.navigation.AppDestinations
import com.nicolascristaldo.myclients.ui.screens.clients.details.ClientDetailsViewModel

data class ClientOption(
    val title: String,
    val onClick: () -> Unit
)

fun getClientOptions(
    clientDetailsViewModel: ClientDetailsViewModel,
    navController: NavHostController
): List<ClientOption> {
    return listOf(
        ClientOption("Edit") {
            navController.navigate(
                AppDestinations.ClientFormEdit.createRoute(
                    clientDetailsViewModel.client.value?.id ?: 0
                )
            )
        },
        ClientOption("Delete") { clientDetailsViewModel.setDeleteAlertExpanded(true) }
    )
}