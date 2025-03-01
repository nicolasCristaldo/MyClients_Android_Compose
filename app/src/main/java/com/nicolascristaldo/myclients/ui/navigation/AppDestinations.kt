package com.nicolascristaldo.myclients.ui.navigation

import com.nicolascristaldo.myclients.R

/**
 * Represents the different destinations in the app.
 * @property route The route for the destination.
 * @property title The title for the destination.
 */
sealed class AppDestinations(
    val route: String,
    val title: Int
) {
    data object Home : AppDestinations(
        route = "home",
        title = R.string.app_name
    )

    data object Clients : AppDestinations(
        route = "clients",
        title = R.string.clients_screen
    )

    data object Orders : AppDestinations(
        route = "orders",
        title = R.string.orders_screen
    )

    data object ClientDetails : AppDestinations(
        route = "client/details/{id}",
        title = R.string.client_details_screen
    ) {
        fun createRoute(id: Int) = "client/details/$id"
    }

    data object ClientFormAdd: AppDestinations(
        route = "client/form",
        title = R.string.client_form_add_screen
    )

    data object ClientFormEdit : AppDestinations(
        route = "client/form/{id}",
        title = R.string.client_form_edit_screen
    ) {
        fun createRoute(id: Int) = "client/form/$id"
    }

    data object OrderFormAdd: AppDestinations(
        route = "order/form/add/{customerId}",
        title = R.string.order_form_add_screen
    ) {
        fun createRoute(customerId: Int) = "order/form/add/$customerId"
    }

    data object OrderFormEdit : AppDestinations(
        route = "order/form/edit/{id}",
        title = R.string.order_form_edit_screen
    ) {
        fun createRoute(id: Int) = "order/form/edit/$id"
    }
}