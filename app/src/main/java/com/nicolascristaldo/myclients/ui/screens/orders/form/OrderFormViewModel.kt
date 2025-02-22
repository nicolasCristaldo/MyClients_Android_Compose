package com.nicolascristaldo.myclients.ui.screens.orders.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.myclients.data.repositories.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderFormViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {
    var orderUiState by mutableStateOf(OrderUiState())
        private set

    fun retrieveOrder(id: Int) = viewModelScope.launch {
        orderRepository.getOrderById(id)
            .filterNotNull()
            .collectLatest { order ->
                orderUiState = order.toUiState(isEntryValid = true)
            }
    }

    private fun resetUiState() {
        orderUiState = OrderUiState()
    }

    private fun validateInput(uiState: OrderDetails = orderUiState.orderDetails): Boolean {
        return with(uiState) {
            total.isNotBlank() && description.isNotBlank()
        }
    }

    fun updateUiState(orderDetails: OrderDetails) {
        orderUiState = OrderUiState(
            orderDetails = orderDetails,
            isEntryValid = validateInput(orderDetails)
        )
    }

    fun saveOrder() = viewModelScope.launch {
        if (validateInput()) {
            orderRepository.insertOrder(orderUiState.orderDetails.toOrder())
        }
        resetUiState()
    }

    fun updateOrder() = viewModelScope.launch {
        if (validateInput(orderUiState.orderDetails)) {
            orderRepository.updateOrder(orderUiState.orderDetails.toOrder())
        }
        resetUiState()
    }

    fun deleteOrder() = viewModelScope.launch {
        orderRepository.deleteOrder(orderUiState.orderDetails.toOrder())
        resetUiState()
    }
}