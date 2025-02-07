package com.nicolascristaldo.myclients.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a customer entity in the database.
 * @property id The unique identifier for the customer.
 * @property name The name of the customer.
 * @property email The email address of the customer.
 * @property phone The phone number of the customer.
 * @property address The address of the customer.
 * @property genre The gender of the customer.
 */
@Entity(tableName = "customers")
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "genre") val genre: String
)
