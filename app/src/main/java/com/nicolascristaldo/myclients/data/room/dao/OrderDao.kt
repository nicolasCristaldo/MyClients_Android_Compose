package com.nicolascristaldo.myclients.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nicolascristaldo.myclients.data.room.entities.OrderEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for the Order entity.
 */
@Dao
interface OrderDao {
    /**
     * Inserts a new order into the database.
     * @param order The order to be inserted.
     */
    @Insert
    suspend fun insertOrder(order: OrderEntity)

    /**
     * Updates an existing order in the database.
     * @param order The order to be updated.
     */
    @Update
    suspend fun updateOrder(order: OrderEntity)

    /**
     * Deletes an order from the database.
     * @param order The order to be deleted.
     */
    @Delete
    suspend fun deleteOrder(order: OrderEntity)

    /**
     * Retrieves all orders from the database in descending order of date.
     * @return A [Flow] emitting a list of all orders.
     */
    @Query("SELECT * FROM orders ORDER BY date DESC")
    fun getAllOrders(): Flow<List<OrderEntity>>

    /**
     * Retrieves an order by its ID from the database.
     * @param id The ID of the order to retrieve.
     * @return A [Flow] emitting the order with the specified ID.
     */
    @Query("SELECT * FROM orders WHERE id = :id")
    fun getOrderById(id: Int): Flow<OrderEntity?>

    /**
     * Retrieves a list of orders associated with a specific customer ID from the database.
     * @param customerId The ID of the customer.
     * @return A [Flow] emitting a list of orders associated with the customer.
     */
    @Query("SELECT * FROM orders WHERE customer_id = :customerId")
    fun getOrdersByCustomerId(customerId: Int): Flow<List<OrderEntity>>

    /**
     * Retrieves the total amount of orders from the database.
     * @return A [Double] representing the total amount of orders.
     */
    @Query("SELECT SUM(total) FROM orders WHERE is_paid = 1")
    suspend fun getTotalEarned(): Double?

    /**
     * Retrieves the total amount of pending orders from the database.
     * @return A [Double] representing the total amount of pending orders.
     */
    @Query("SELECT SUM(total) FROM orders WHERE is_paid = 0")
    suspend fun getTotalPending(): Double?

    /**
     * Retrieves the total amount of paid orders for a specific customer from the database.
     * @param customerId The ID of the customer.
     * @return A [Double] representing the total amount of paid orders for the customer.
     */
    @Query("SELECT SUM(total) FROM orders WHERE customer_id = :customerId AND is_paid = 1")
    suspend fun getTotalPaidByCustomer(customerId: Int): Double?

    /**
     * Retrieves the total amount of pending orders for a specific customer from the database.
     * @param customerId The ID of the customer.
     * @return A [Double] representing the total amount of pending orders for the customer.
     */
    @Query("SELECT SUM(total) FROM orders WHERE customer_id = :customerId AND is_paid = 0")
    suspend fun getTotalPendingByCustomer(customerId: Int): Double?
}