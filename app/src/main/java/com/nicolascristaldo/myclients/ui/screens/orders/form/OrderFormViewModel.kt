package com.nicolascristaldo.myclients.ui.screens.orders.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.myclients.data.repositories.OrderRepository
import com.nicolascristaldo.myclients.ui.screens.clients.form.isValidInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the order form screen.
 * @property orderRepository The repository for managing orders.
 */
@HiltViewModel
class OrderFormViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {
    var orderUiState by mutableStateOf(OrderUiState())
        private set

    /**
     * Retrieves the order with the specified ID.
     * @param id The ID of the order.
     */
    fun retrieveOrder(id: Int) = viewModelScope.launch {
        orderRepository.getOrderById(id)
            .filterNotNull()
            .collectLatest { order ->
                orderUiState = order.toUiState(isEntryValid = true)
            }
    }

    /**
     * Resets the UI state of the order form.
     */
    fun resetUiState() { orderUiState = OrderUiState() }

    /**
     * Validates the input of the order form.
     * @param uiState The UI state of the order form.
     * @return A [Boolean] value indicating whether the input is valid.
     */
    private fun validateInput(uiState: OrderDetails = orderUiState.orderDetails): Boolean {
        return with(uiState) {
            isValidPrice(total) && isValidInput(description)
        }
    }

    /**
     * Updates the UI state of the order form.
     * @param orderDetails The details of the order.
     */
    fun updateUiState(orderDetails: OrderDetails) {
        orderUiState = OrderUiState(
            orderDetails = orderDetails,
            isEntryValid = validateInput(orderDetails)
        )
    }

    /**
     * Saves the order to the database.
     */
    fun saveOrder() = viewModelScope.launch {
        if (validateInput()) {
            orderRepository.insertOrder(orderUiState.orderDetails.toOrder())
        }
    }

    /**
     * Updates the order in the database.
     */
    fun updateOrder() = viewModelScope.launch {
        if (validateInput(orderUiState.orderDetails)) {
            orderRepository.updateOrder(
                orderUiState.orderDetails.toOrder()
            )
        }
    }

    /**
     * Deletes the order from the database.
     */
    fun deleteOrder() = viewModelScope.launch {
        orderRepository.deleteOrder(orderUiState.orderDetails.toOrder())
    }
}