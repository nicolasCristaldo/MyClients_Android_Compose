package com.nicolascristaldo.myclients.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nicolascristaldo.myclients.data.room.entities.CustomerEntity
import kotlinx.coroutines.flow.Flow

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

    /**
     * Retrieves all customers from the database.
     * @return A [Flow] emitting a list of all customers.
     */
    @Query("SELECT * FROM customers")
    fun getAllCustomers(): Flow<List<CustomerEntity>>

    /**
     * Retrieves a customer by its ID from the database.
     * @param id The ID of the customer to retrieve.
     * @return A [Flow] emitting the customer with the specified ID.
     */
    @Query("SELECT * FROM customers WHERE id = :id")
    fun getCustomerById(id: Int): Flow<CustomerEntity?>

    /**
     * Retrieves a list of customers with a specific name from the database.
     * @param name The name to filter the customers.
     * @return A [Flow] emitting a list of customers with the specified name.
     */
    @Query("SELECT * FROM customers WHERE name LIKE :name")
    fun getCustomerByName(name: String): Flow<List<CustomerEntity>>

    /**
     * Retrieves the last inserted customer from the database.
     * @return A [CustomerEntity] representing the last inserted customer.
     */
    @Query("SELECT * FROM customers ORDER BY id DESC LIMIT 1")
    fun getLastCustomer(): Flow<CustomerEntity?>
}