package com.nicolascristaldo.myclients.domain.usecases

import com.nicolascristaldo.myclients.data.repositories.OrderRepository
import javax.inject.Inject

/**
 * Use case to retrieve the total amount of paid orders from the database.
 */
class GetTotalEarnedUseCase @Inject constructor(
    private val orderRepository: OrderRepository
){
    /**
     * Retrieves the total amount of paid orders from the database.
     * if there are no paid orders, it returns 0.
     * @return A [Double] representing the total amount of orders.
     */
    suspend operator fun invoke(): Double =
        orderRepository.getTotalEarned() ?: 0.0
}