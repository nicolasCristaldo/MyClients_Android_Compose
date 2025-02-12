package com.nicolascristaldo.myclients.domain.usecases

import com.nicolascristaldo.myclients.data.repositories.OrderRepository
import javax.inject.Inject

/**
 * Use case to retrieve the total amount of pending orders from the database.
 */
class GetTotalPendingUseCase @Inject constructor(
    private val orderRepository: OrderRepository
){
    /**
     * Retrieves the total amount of pending orders from the database.
     * if there are no pending orders, it returns 0.
     * @return A [Double] representing the total amount of pending orders.
     */
    suspend operator fun invoke(): Double =
        orderRepository.getTotalPending() ?: 0.0
}