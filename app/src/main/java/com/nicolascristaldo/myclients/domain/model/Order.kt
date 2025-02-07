package com.nicolascristaldo.myclients.domain.model

import com.nicolascristaldo.myclients.data.room.entities.OrderEntity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Represents an order in the domain layer.
 * @property id The unique identifier for the order.
 * @property customerId The ID of the customer associated with the order.
 * @property date The date of the order.
 * @property total The total amount of the order.
 * @property isPaid A flag indicating whether the order is paid or not.
 */
data class Order(
    val id: Int,
    val customerId: Int,
    val date: String = getCurrentDateTime(),
    val total: Double,
    val isPaid: Boolean = false
) {
    companion object {
        /**
         * Gets the current date and time in the format "yyyy/mm/dd hh:mm".
         * @return A [String] representing the current date and time.
         */
        fun getCurrentDateTime(): String {
            val formatter = DateTimeFormatter.ofPattern("yyyy/mm/dd hh:mm")
            return LocalDateTime.now().format(formatter)
        }
    }
}

/**
 * Converts an [OrderEntity] object (database entity) to an [Order] object (domain model).
 * @return An [Order] instance with mapped fields from [OrderEntity].
 */
fun OrderEntity.toDomain() = Order(
    id = id,
    customerId = customerId,
    date = date,
    total = total,
    isPaid = isPaid
)