package com.nicolascristaldo.myclients.ui.screens.clients.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.myclients.data.repositories.CustomerRepository
import com.nicolascristaldo.myclients.data.repositories.OrderRepository
import com.nicolascristaldo.myclients.domain.model.Customer
import com.nicolascristaldo.myclients.domain.model.Order
import com.nicolascristaldo.myclients.domain.usecases.GetTotalEarnedUseCase
import com.nicolascristaldo.myclients.domain.usecases.GetTotalPaidByCustomerUseCase
import com.nicolascristaldo.myclients.domain.usecases.GetTotalPendingByCustomerUseCase
import com.nicolascristaldo.myclients.domain.usecases.GetTotalPendingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

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

    fun getTotalPaid(id: Int) = viewModelScope.launch {
        _totalPaid.value = getTotalPaidByCustomerUseCase(id)
    }

    fun getTotalPending(id: Int) = viewModelScope.launch {
        _totalPending.value = getTotalPendingByCustomerUseCase(id)
    }

    fun getClient(id: Int) = viewModelScope.launch {
        customerRepository.getCustomerById(id).collect {
            _client.value = it
        }
    }

    fun getOrdersByCustomerId(id: Int) = viewModelScope.launch {
        orderRepository.getOrdersByCustomerId(id).collect {
            _clientOrders.value = it
        }
    }
}