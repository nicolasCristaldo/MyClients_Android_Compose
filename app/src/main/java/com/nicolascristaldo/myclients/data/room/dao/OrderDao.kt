package com.nicolascristaldo.myclients.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.nicolascristaldo.myclients.data.room.entities.OrderEntity

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
}