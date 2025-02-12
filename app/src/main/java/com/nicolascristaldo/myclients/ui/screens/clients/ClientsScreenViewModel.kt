package com.nicolascristaldo.myclients.ui.screens.clients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.myclients.data.repositories.CustomerRepository
import com.nicolascristaldo.myclients.domain.model.Customer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * ViewModel for the Clients screen.
 * @param customerRepository The repository for managing customers.
 */
@HiltViewModel
class ClientsScreenViewModel @Inject constructor(
    customerRepository: CustomerRepository
): ViewModel() {
    private val _clients: StateFlow<List<Customer>> =
        customerRepository.getAllCustomers()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    val clients: StateFlow<List<Customer>> get() = _clients
}