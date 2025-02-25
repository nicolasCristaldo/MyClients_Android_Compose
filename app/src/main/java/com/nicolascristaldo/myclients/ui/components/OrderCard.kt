package com.nicolascristaldo.myclients.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nicolascristaldo.myclients.domain.model.Order
import com.nicolascristaldo.myclients.ui.screens.orders.form.formatedPrice

@Composable
fun OrderCard(
    order: Order,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(text = "Order #${order.id}")
        Text(text = order.description)
        Text(
            text = "status: " + if (order.isPaid) {
                "paid"
            } else {
                "not paid"
            }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Text(text = "Total: ${order.formatedPrice()}")
            Spacer(modifier = Modifier.weight(1f))
            Text(text = order.date)
        }
        HorizontalDivider()
    }
}