package com.nicolascristaldo.myclients.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.nicolascristaldo.myclients.data.room.entities.CustomerEntity

/**
 * DAO for the Customer entity.
 */
@Dao
interface CustomerDao {
    /**
     * Inserts a new customer into the database.
     * @param customer The customer to be inserted.
     */
    @Insert
    suspend fun insertCustomer(customer: CustomerEntity)

    /**
     * Updates an existing customer in the database.
     * @param customer The customer to be updated.
     */
    @Update
    suspend fun updateCustomer(customer: CustomerEntity)

    /**
     * Deletes a customer from the database.
     * @param customer The customer to be deleted.
     */
    @Delete
    suspend fun deleteCustomer(customer: CustomerEntity)
}