package com.nicolascristaldo.myclients.ui.screens.orders.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascristaldo.myclients.data.repositories.OrderRepository
import com.nicolascristaldo.myclients.domain.model.Order
import com.nicolascristaldo.myclients.domain.usecases.GetTotalEarnedUseCase
import com.nicolascristaldo.myclients.domain.usecases.GetTotalPendingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Orders screen.
 * @param orderRepository The repository for managing orders.
 * @param getTotalEarnedUseCase The use case for getting the total earned.
 * @param getTotalPendingUseCase The use case for getting the total pending.
 */
@HiltViewModel
class OrdersScreenViewModel @Inject constructor(
    orderRepository: OrderRepository,
    getTotalEarnedUseCase: GetTotalEarnedUseCase,
    getTotalPendingUseCase: GetTotalPendingUseCase
): ViewModel() {
    private val _orders: StateFlow<List<Order>> =
        orderRepository.getAllOrders()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    val orders: StateFlow<List<Order>> get() = _orders

    private val _lastOrders: StateFlow<List<Order>> =
        orderRepository.getLastOrders()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    val lastOrders: StateFlow<List<Order>> get() = _lastOrders

    val totalEarned = MutableStateFlow(0.0)
    val totalPending = MutableStateFlow(0.0)

    init {
        viewModelScope.launch {
            totalEarned.value = getTotalEarnedUseCase()
            totalPending.value = getTotalPendingUseCase()
        }
    }
}