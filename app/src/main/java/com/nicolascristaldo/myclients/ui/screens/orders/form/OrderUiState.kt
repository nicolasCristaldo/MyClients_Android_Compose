package com.nicolascristaldo.myclients.ui.screens.orders.form

import com.nicolascristaldo.myclients.domain.model.Order
import java.text.NumberFormat


data class OrderUiState(
    val orderDetails: OrderDetails = OrderDetails(),
    val isEntryValid: Boolean = false
)

data class OrderDetails(
    val id: Int = 0,
    val customerId: Int = 0,
    val total: String = "",
    val description: String = "",
)

fun OrderDetails.toOrder(): Order = Order(
    id = id,
    customerId = customerId,
    description = description,
    total = try {
        total.toDouble()
    } catch (e: Exception) {
        0.0
    }
)

fun Order.toOrderDetails(): OrderDetails = OrderDetails(
    id = id,
    customerId = customerId,
    total = total.toString(),
    description = description
)

fun Order.toUiState(isEntryValid: Boolean = false): OrderUiState = OrderUiState(
    orderDetails = this.toOrderDetails(),
    isEntryValid = isEntryValid
)

fun Order.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(total)
}

fun isValidPrice(price: String): Boolean {
    var isValid = true

    try {
        if (price != "") {
            price.toDouble()
        }
    } catch (e: Exception) {
        isValid = false
    }

    return isValid
}