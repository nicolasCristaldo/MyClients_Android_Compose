package com.nicolascristaldo.myclients.ui.screens.orders.form

import com.nicolascristaldo.myclients.domain.model.Order
import java.text.NumberFormat

/**
 * Represents the state of the order details screen.
 * @property orderDetails The details of the order.
 * @property isEntryValid Indicates whether the entry is valid.
 */
data class OrderUiState(
    val orderDetails: OrderDetails = OrderDetails(),
    val isEntryValid: Boolean = false
)

/**
 * Represents the details of an order.
 * @property id The ID of the order.
 * @property customerId The ID of the customer associated with the order.
 * @property total The total amount of the order.
 * @property date The date of the order.
 * @property isPaid Indicates whether the order is paid.
 * @property description The description of the order.
 */
data class OrderDetails(
    val id: Int = 0,
    val customerId: Int = 0,
    val total: String = "",
    val date: String = "",
    val isPaid: Boolean = false,
    val description: String = "",
)

/**
 * Extension function to convert a [OrderDetails] object to a [Order] object.
 */
fun OrderDetails.toOrder(): Order = Order(
    id = id,
    customerId = customerId,
    description = description,
    isPaid = isPaid,
    total = try {
        total.toDouble()
    } catch (e: Exception) {
        0.0
    },
    date = date.ifBlank { Order.getCurrentDateTime() }
)

/**
 * Extension function to convert a [Order] object to a [OrderDetails] object.
 */
fun Order.toOrderDetails(): OrderDetails = OrderDetails(
    id = id,
    customerId = customerId,
    total = total.toString(),
    date = date,
    isPaid = isPaid,
    description = description
)

/**
 * Extension function to convert a [Order] object to a [OrderUiState] object.
 */
fun Order.toUiState(isEntryValid: Boolean = false): OrderUiState = OrderUiState(
    orderDetails = this.toOrderDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to format the price of an order.
 */
fun Order.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(total)
}

/**
 * Function to validate the price of an order.
 * @param price The price of the order.
 */
fun isValidPrice(price: String): Boolean {
    var isValid = true

    try {
        price.toDouble()
    } catch (e: Exception) {
        isValid = false
    }

    return isValid
}