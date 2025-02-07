package com.nicolascristaldo.myclients.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nicolascristaldo.myclients.domain.model.Order

/**
 * Represents an order entity in the database.
 * @property id The unique identifier for the order.
 * @property customerId The ID of the customer associated with the order.
 * @property date The date of the order.
 * @property total The total amount of the order.
 * @property isPaid A flag indicating whether the order is paid or not.
 */
@Entity(
    tableName = "orders",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = CustomerEntity::class,
            parentColumns = ["id"],
            childColumns = ["customer_id"],
            onDelete = androidx.room.ForeignKey.CASCADE
        )
    ]
)
data class OrderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "customer_id") val customerId: Int,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "total") val total: Double,
    @ColumnInfo(name = "is_paid") val isPaid: Boolean = false
)

/**
 * Converts an [Order] object (domain model) to an [OrderEntity] object (database entity).
 * @return An [OrderEntity] instance with mapped fields from [Order].
 */
fun Order.toDatabase() = OrderEntity(
    id = id,
    customerId = customerId,
    date = date,
    total = total,
    isPaid = isPaid
)