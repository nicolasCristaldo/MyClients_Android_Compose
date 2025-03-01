package com.nicolascristaldo.myclients.data.repositories

import com.nicolascristaldo.myclients.data.room.dao.OrderDao
import com.nicolascristaldo.myclients.data.room.entities.toDatabase
import com.nicolascristaldo.myclients.domain.model.Order
import com.nicolascristaldo.myclients.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrderRepository @Inject constructor(
    private val orderDao: OrderDao
) {
    /**
     * Inserts a new order into the database.
     * @param order The order to be inserted.
     */
    suspend fun insertOrder(order: Order) =
        withContext(Dispatchers.IO) { orderDao.insertOrder(order.toDatabase()) }

    /**
     * Updates an existing order in the database.
     * @param order The order to be updated.
     */
    suspend fun updateOrder(order: Order) =
        withContext(Dispatchers.IO) { orderDao.updateOrder(order.toDatabase()) }

    /**
     * Deletes an order from the database.
     * @param order The order to be deleted.
     */
    suspend fun deleteOrder(order: Order) =
        withContext(Dispatchers.IO) { orderDao.deleteOrder(order.toDatabase()) }

    /**
     * Retrieves all orders from the database in descending order of date.
     * @return A [Flow] emitting a list of all orders.
     */
    fun getAllOrders(): Flow<List<Order>> =
        orderDao.getAllOrders().map { orders -> orders.map { it.toDomain() } }

    /**
     * Retrieves the last 10 orders from the database in descending order of date.
     * @return A [Flow] emitting a list of the last 10 orders.
     */
    fun getLastOrders(): Flow<List<Order>> =
        orderDao.getLastOrders().map { orders -> orders.map { it.toDomain() } }

    /**
     * Retrieves an order by its ID from the database.
     * @param id The ID of the order to retrieve.
     * @return A [Flow] emitting the order with the specified ID.
     */
    fun getOrderById(id: Int): Flow<Order?> =
        orderDao.getOrderById(id).map { it?.toDomain() }

    /**
     * Retrieves a list of orders associated with a specific customer ID from the database.
     * @param customerId The ID of the customer.
     * @return A [Flow] emitting a list of orders associated with the customer.
     */
    fun getOrdersByCustomerId(customerId: Int): Flow<List<Order>> =
        orderDao.getOrdersByCustomerId(customerId).map { orders -> orders.map { it.toDomain() } }

    /**
     * Retrieves the total amount of paid orders from the database.
     * @return A [Double] representing the total amount of orders.
     */
    suspend fun getTotalEarned(): Double? =
        withContext(Dispatchers.IO) { orderDao.getTotalEarned() }

    /**
     * Retrieves the total amount of pending orders from the database.
     * @return A [Double] representing the total amount of pending orders.
     */
    suspend fun getTotalPending(): Double? =
        withContext(Dispatchers.IO) { orderDao.getTotalPending() }

    /**
     * Retrieves the total amount of paid orders for a specific customer from the database.
     * @param customerId The ID of the customer.
     * @return A [Double] representing the total amount of paid orders for the customer.
     */
    suspend fun getTotalPaidByCustomer(customerId: Int): Double? =
        withContext(Dispatchers.IO) { orderDao.getTotalPaidByCustomer(customerId) }

    /**
     * Retrieves the total amount of pending orders for a specific customer from the database.
     * @param customerId The ID of the customer.
     * @return A [Double] representing the total amount of pending orders for the customer.
     */
    suspend fun getTotalPendingByCustomer(customerId: Int): Double? =
        withContext(Dispatchers.IO) { orderDao.getTotalPendingByCustomer(customerId) }

}