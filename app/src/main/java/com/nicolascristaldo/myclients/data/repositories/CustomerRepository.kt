package com.nicolascristaldo.myclients.data.repositories

import com.nicolascristaldo.myclients.data.room.dao.CustomerDao
import com.nicolascristaldo.myclients.data.room.entities.toDatabase
import com.nicolascristaldo.myclients.domain.model.Customer
import com.nicolascristaldo.myclients.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CustomerRepository @Inject constructor(
    private val customerDao: CustomerDao
) {
    /**
     * Inserts a new customer into the database.
     * @param customer The customer to be inserted.
     */
    suspend fun insertCustomer(customer: Customer) =
        withContext(Dispatchers.IO) { customerDao.insertCustomer(customer.toDatabase()) }

    /**
     * Updates an existing customer in the database.
     * @param customer The customer to be updated.
     */
    suspend fun updateCustomer(customer: Customer) =
        withContext(Dispatchers.IO) { customerDao.updateCustomer(customer.toDatabase()) }

    /**
     * Deletes a customer from the database.
     * @param customer The customer to be deleted.
     */
    suspend fun deleteCustomer(customer: Customer) =
        withContext(Dispatchers.IO) { customerDao.deleteCustomer(customer.toDatabase()) }

    /**
     * Retrieves all customers from the database.
     * @return A [Flow] emitting a list of all customers.
     */
    fun getAllCustomers(): Flow<List<Customer>> =
        customerDao.getAllCustomers().map { customers -> customers.map { it.toDomain() } }

    /**
     * Retrieves a customer by its ID from the database.
     * @param id The ID of the customer to retrieve.
     * @return A [Flow] emitting the customer with the specified ID.
     */
    fun getCustomerById(id: Int): Flow<Customer?> =
        customerDao.getCustomerById(id).map { it?.toDomain() }

    /**
     * Retrieves a list of customers with a specific name from the database.
     * @param name The name to filter the customers.
     * @return A [Flow] emitting a list of customers with the specified name.
     */
    fun getCustomerByName(name: String): Flow<List<Customer>> =
        customerDao.getCustomerByName(name).map { customers -> customers.map { it.toDomain() } }

    /**
     * Retrieves the last inserted customer from the database.
     * @return A [Customer] representing the last inserted customer.
     */
    fun getLastCustomer(): Flow<Customer?> =
        customerDao.getLastCustomer().map { it?.toDomain() }
}