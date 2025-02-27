package com.nicolascristaldo.myclients.ui.screens.clients.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.myclients.data.repositories.CustomerRepository
import com.nicolascristaldo.myclients.domain.model.Customer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * ViewModel for the Clients screen.
 * @param customerRepository The repository for managing customers.
 */
@HiltViewModel
class ClientsScreenViewModel @Inject constructor(
    customerRepository: CustomerRepository
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private val _clients: StateFlow<List<Customer>> = _searchQuery
        .debounce(300)
        .flatMapLatest { query ->
            if (query.isBlank()) {
                customerRepository.getAllCustomers()
            } else {
                customerRepository.getCustomerByName("%$query%")
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val clients: StateFlow<List<Customer>> get() = _clients

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
}