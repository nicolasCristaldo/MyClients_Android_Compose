package com.nicolascristaldo.myclients.ui.screens.orders.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nicolascristaldo.myclients.domain.model.Order

@Composable
fun OrdersScreen(
    orders: List<Order>,
    modifier: Modifier = Modifier
) {
    OrderListScreen(
        orders = orders,
        modifier = modifier
    )
}

@Composable
fun OrderListScreen(
    orders: List<Order>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(orders) { order ->
            OrderCard(order = order)
        }
    }
}

@Composable
fun OrderCard(
    order: Order,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
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
            Text(text = "Total: ${order.total}")
            Spacer(modifier = Modifier.weight(1f))
            Text(text = order.date)
        }
        HorizontalDivider(modifier = Modifier.padding(bottom = 8.dp))
    }
}
