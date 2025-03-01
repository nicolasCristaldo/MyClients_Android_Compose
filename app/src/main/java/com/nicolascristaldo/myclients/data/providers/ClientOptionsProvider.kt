package com.nicolascristaldo.myclients.data.providers

import androidx.navigation.NavHostController
import com.nicolascristaldo.myclients.ui.navigation.AppDestinations
import com.nicolascristaldo.myclients.ui.screens.clients.details.ClientDetailsViewModel

/**
 * Data class that represents the UI state for a client option.
 * @property title The title of the client option.
 * @property onClick The action to perform when the client option is clicked.
 */
data class ClientOption(
    val title: String,
    val onClick: () -> Unit
)

/**
 * Returns a list of client options.
 * @param clientDetailsViewModel The view model for the client details screen.
 * @param navController The navigation controller.
 */
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