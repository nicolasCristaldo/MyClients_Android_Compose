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

@HiltViewModel
class ClientFormViewModel @Inject constructor(
    private val customerRepository: CustomerRepository
) : ViewModel() {
    var clientUiState by mutableStateOf(ClientUiState())
        private set

    fun retrieveClient(id: Int) = viewModelScope.launch {
        customerRepository.getCustomerById(id)
            .filterNotNull()
            .collectLatest { customer ->
                clientUiState = customer.toUiState(isEntryValid = true)
            }
    }

    fun resetUiState() { clientUiState = ClientUiState() }

    private fun validateInput(uiState: ClientDetails = clientUiState.clientDetails): Boolean {
        return with(uiState) {
            isValidInput(name) && isValidEmail(email) && isValidPhone(phone) && isValidInput(address)
        }
    }

    fun updateUiState(clientDetails: ClientDetails) {
        clientUiState = ClientUiState(
            clientDetails = clientDetails,
            isEntryValid = validateInput(clientDetails)
        )
    }

    fun saveClient() = viewModelScope.launch {
        if(validateInput()) {
            customerRepository.insertCustomer(clientUiState.clientDetails.toCustomer())
        }
    }

    fun updateClient() = viewModelScope.launch {
        if(validateInput(clientUiState.clientDetails)) {
            customerRepository.updateCustomer(clientUiState.clientDetails.toCustomer())
        }
    }
}