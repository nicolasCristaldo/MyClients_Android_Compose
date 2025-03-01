package com.nicolascristaldo.myclients.ui.screens.clients.form

import android.util.Patterns
import com.nicolascristaldo.myclients.domain.model.Customer

/**
 * Represents the state of the client details screen.
 * @property clientDetails The details of the client.
 * @property isEntryValid Indicates whether the entry is valid.
 */
data class ClientUiState(
    val clientDetails: ClientDetails = ClientDetails(),
    val isEntryValid: Boolean = false
)

/**
 * Represents the details of a client.
 * @property id The ID of the client.
 * @property name The name of the client.
 * @property email The email of the client.
 * @property phone The phone number of the client.
 * @property address The address of the client.
 * @property genre The genre of the client.
 */
data class ClientDetails(
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val address: String = "",
    val genre: String = "male"
)

/**
 * Extension function to convert a [ClientDetails] object to a [Customer] object.
 */
fun ClientDetails.toCustomer(): Customer = Customer(
    id = id,
    name = name,
    email = email,
    phone = phone,
    address = address,
    genre = genre
)

/**
 * Extension function to convert a [Customer] object to a [ClientDetails] object.
 */
fun Customer.toClientDetails(): ClientDetails = ClientDetails(
    id = id,
    name = name,
    email = email,
    phone = phone,
    address = address,
    genre = genre
)

/**
 * Extension function to convert a [Customer] object to a [ClientUiState] object.
 * @param isEntryValid Indicates whether the entry is valid.
 */
fun Customer.toUiState(isEntryValid: Boolean = false): ClientUiState = ClientUiState(
    clientDetails = this.toClientDetails(),
    isEntryValid = isEntryValid
)

/**
 * Function to validate the input fields of a client.
 * @param email The email of the client.
 * @return A [Boolean] value indicating whether the email is valid.
 */
fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

/**
 * Function to validate the phone number of a client.
 * @param phone The phone number of the client.
 * @return A [Boolean] value indicating whether the phone number is valid.
 */
fun isValidPhone(phone: String): Boolean {
    return Patterns.PHONE.matcher(phone).matches()
}

/**
 * Function to validate the input of a client.
 * @param input The input to be validated.
 * @return A [Boolean] value indicating whether the input is valid.
 */
fun isValidInput(input: String): Boolean {
    return input.isNotBlank() && input.length <= 30
}