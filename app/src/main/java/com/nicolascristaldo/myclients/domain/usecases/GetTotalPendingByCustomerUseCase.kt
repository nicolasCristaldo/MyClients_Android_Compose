package com.nicolascristaldo.myclients.domain.usecases

import com.nicolascristaldo.myclients.data.repositories.OrderRepository
import javax.inject.Inject

/**
 * Use case for retrieving the total amount of pending orders for a specific customer from the database.
 */
class GetTotalPendingByCustomerUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    /**
     * Retrieves the total amount of pending orders for a specific customer from the database.
     * if the total is null, returns 0
     * @param customerId The ID of the customer.
     * @return A [Double] representing the total amount of pending orders for the customer.
     */
    suspend operator fun invoke(customerId: Int): Double =
        orderRepository.getTotalPendingByCustomer(customerId) ?: 0.0
}