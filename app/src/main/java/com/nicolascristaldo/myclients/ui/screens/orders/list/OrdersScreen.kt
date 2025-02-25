package com.nicolascristaldo.myclients.ui.screens.orders.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nicolascristaldo.myclients.domain.model.Order
import com.nicolascristaldo.myclients.ui.components.OrderCard

@Composable
fun OrdersScreen(
    orders: List<Order>,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    OrderListScreen(
        orders = orders,
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
fun OrderListScreen(
    orders: List<Order>,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(orders) { order ->
            OrderCard(
                order = order,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
                    .clickable {
                        onClick(order.id)
                    }
            )
        }
    }
}


