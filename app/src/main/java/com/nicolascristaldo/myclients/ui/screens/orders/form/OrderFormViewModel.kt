package com.nicolascristaldo.myclients.ui.screens.orders.form

import androidx.lifecycle.ViewModel
import com.nicolascristaldo.myclients.data.repositories.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderFormViewModel @Inject constructor(
    private val orderRepository: OrderRepository
): ViewModel() {

}