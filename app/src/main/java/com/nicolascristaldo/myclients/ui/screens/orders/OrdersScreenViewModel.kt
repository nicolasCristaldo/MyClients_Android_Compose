package com.nicolascristaldo.myclients.ui.screens.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.myclients.data.repositories.OrderRepository
import com.nicolascristaldo.myclients.domain.model.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * ViewModel for the Orders screen.
 * @param orderRepository The repository for managing orders.
 */
@HiltViewModel
class OrdersScreenViewModel @Inject constructor(
    orderRepository: OrderRepository
): ViewModel() {
    private val _orders: StateFlow<List<Order>> =
        orderRepository.getAllOrders()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    val orders: StateFlow<List<Order>> get() = _orders
}