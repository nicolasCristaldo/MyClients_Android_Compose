package com.nicolascristaldo.myclients.ui.screens.clients.form

import android.util.Patterns
import com.nicolascristaldo.myclients.domain.model.Customer

data class ClientUiState(
    val clientDetails: ClientDetails = ClientDetails(),
    val isEntryValid: Boolean = false
)

data class ClientDetails(
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val address: String = "",
    val genre: String = "male"
)

fun ClientDetails.toCustomer(): Customer = Customer(
    id = id,
    name = name,
    email = email,
    phone = phone,
    address = address,
    genre = genre
)

fun Customer.toClientDetails(): ClientDetails = ClientDetails(
    id = id,
    name = name,
    email = email,
    phone = phone,
    address = address,
    genre = genre
)

fun Customer.toUiState(isEntryValid: Boolean = false): ClientUiState = ClientUiState(
    clientDetails = this.toClientDetails(),
    isEntryValid = isEntryValid
)

fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isValidPhone(phone: String): Boolean {
    return Patterns.PHONE.matcher(phone).matches()
}

fun isValidInput(input: String): Boolean {
    return input.isNotBlank() && input.length <= 30
}