package com.nicolascristaldo.myclients.ui.screens.clients.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.myclients.data.repositories.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the client form screen.
 * @property customerRepository The repository for managing customers.
 */
@HiltViewModel
class ClientFormViewModel @Inject constructor(
    private val customerRepository: CustomerRepository
) : ViewModel() {
    var clientUiState by mutableStateOf(ClientUiState())
        private set

    /**
     * Retrieves the client with the specified ID.
     * @param id The ID of the client.
     */
    fun retrieveClient(id: Int) = viewModelScope.launch {
        customerRepository.getCustomerById(id)
            .filterNotNull()
            .collectLatest { customer ->
                clientUiState = customer.toUiState(isEntryValid = true)
            }
    }

    /**
     * Resets the UI state of the client form.
     */
    fun resetUiState() { clientUiState = ClientUiState() }

    /**
     * Validates the input of the client form.
     * @param uiState The UI state of the client form.
     * @return A [Boolean] value indicating whether the input is valid.
     */
    private fun validateInput(uiState: ClientDetails = clientUiState.clientDetails): Boolean {
        return with(uiState) {
            isValidInput(name) && isValidEmail(email) && isValidPhone(phone) && isValidInput(address)
        }
    }

    /**
     * Updates the UI state of the client form.
     * @param clientDetails The details of the client.
     */
    fun updateUiState(clientDetails: ClientDetails) {
        clientUiState = ClientUiState(
            clientDetails = clientDetails,
            isEntryValid = validateInput(clientDetails)
        )
    }

    /**
     * Saves the client to the database.
     */
    fun saveClient() = viewModelScope.launch {
        if(validateInput()) {
            customerRepository.insertCustomer(clientUiState.clientDetails.toCustomer())
        }
    }

    /**
     * Updates the client in the database.
     */
    fun updateClient() = viewModelScope.launch {
        if(validateInput(clientUiState.clientDetails)) {
            customerRepository.updateCustomer(clientUiState.clientDetails.toCustomer())
        }
    }
}