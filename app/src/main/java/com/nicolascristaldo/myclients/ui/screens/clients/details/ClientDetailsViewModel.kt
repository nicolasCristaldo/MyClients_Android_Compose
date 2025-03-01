package com.nicolascristaldo.myclients.ui.screens.clients.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.myclients.data.repositories.CustomerRepository
import com.nicolascristaldo.myclients.data.repositories.OrderRepository
import com.nicolascristaldo.myclients.domain.model.Customer
import com.nicolascristaldo.myclients.domain.model.Order
import com.nicolascristaldo.myclients.domain.usecases.GetTotalPaidByCustomerUseCase
import com.nicolascristaldo.myclients.domain.usecases.GetTotalPendingByCustomerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the client details screen.
 * @property customerRepository The repository for managing customers.
 * @property orderRepository The repository for managing orders.
 * @property getTotalPaidByCustomerUseCase The use case for retrieving total paid orders for a customer.
 * @property getTotalPendingByCustomerUseCase The use case for retrieving total pending orders for a customer.
 */
@HiltViewModel
class ClientDetailsViewModel @Inject constructor(
    private val customerRepository: CustomerRepository,
    private val orderRepository: OrderRepository,
    private val getTotalPaidByCustomerUseCase: GetTotalPaidByCustomerUseCase,
    private val getTotalPendingByCustomerUseCase: GetTotalPendingByCustomerUseCase
): ViewModel()  {
    private var _client = MutableStateFlow<Customer?>(null)
    val client get() =  _client

    private var _clientOrders = MutableStateFlow<List<Order>>(emptyList())
    val clientOrders get() = _clientOrders

    private var _totalPaid = MutableStateFlow(0.0)
    val totalPaid get() = _totalPaid

    private var _totalPending = MutableStateFlow(0.0)
    val totalPending get() = _totalPending

    private var _deleteAlertIsExpanded = mutableStateOf(false)
    val deleteAlertIsExpanded get() = _deleteAlertIsExpanded

    /**
     * Sets the expanded state of the delete alert dialog.
     * @param value The new expanded state.
     */
    fun setDeleteAlertExpanded(value: Boolean) {
        _deleteAlertIsExpanded.value = value
    }

    /**
     * Retrieves the total paid orders for a customer.
     * @param id The ID of the customer.
     */
    fun getTotalPaid(id: Int) = viewModelScope.launch {
        _totalPaid.value = getTotalPaidByCustomerUseCase(id)
    }

    /**
     * Retrieves the total pending orders for a customer.
     * @param id The ID of the customer.
     */
    fun getTotalPending(id: Int) = viewModelScope.launch {
        _totalPending.value = getTotalPendingByCustomerUseCase(id)
    }

    /**
     * Retrieves the client with the specified ID.
     * @param id The ID of the client.
     */
    fun getClient(id: Int) = viewModelScope.launch {
        customerRepository.getCustomerById(id).collect {
            _client.value = it
        }
    }

    /**
     * Retrieves the orders associated with the client.
     * @param id The ID of the client.
     */
    fun getOrdersByCustomerId(id: Int) = viewModelScope.launch {
        orderRepository.getOrdersByCustomerId(id).collect {
            _clientOrders.value = it
        }
    }

    /**
     * Deletes the client.
     */
    fun deleteClient() = viewModelScope.launch {
        customerRepository.deleteCustomer(client.value!!)
    }
}