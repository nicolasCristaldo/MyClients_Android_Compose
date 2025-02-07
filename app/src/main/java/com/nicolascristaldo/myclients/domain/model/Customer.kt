package com.nicolascristaldo.myclients.domain.model

import com.nicolascristaldo.myclients.data.room.entities.CustomerEntity

/**
 * Represents a customer.
 * @property id The unique identifier for the customer.
 * @property name The name of the customer.
 * @property email The email address of the customer.
 * @property phone The phone number of the customer.
 * @property address The address of the customer.
 * @property genre The gender of the customer.
 */
data class Customer(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val genre: String
)

/**
 * Converts a [CustomerEntity] object (database entity) to a [Customer] object (domain model).
 * @return A [Customer] instance with mapped fields from [CustomerEntity].
 */
fun CustomerEntity.toDomain() = Customer(
    id = id,
    name = name,
    email = email,
    phone = phone,
    address = address,
    genre = genre
)
