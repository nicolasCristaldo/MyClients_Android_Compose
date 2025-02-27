package com.nicolascristaldo.myclients.ui.screens.orders.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.nicolascristaldo.myclients.R
import com.nicolascristaldo.myclients.domain.model.Order
import com.nicolascristaldo.myclients.ui.components.OrderCard

@Composable
fun OrderListScreen(
    orders: List<Order>,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_normal))
    ) {
        items(orders) { order ->
            OrderCard(
                order = order,
                modifier = Modifier
                    .padding(bottom = dimensionResource(R.dimen.padding_normal))
                    .fillMaxWidth()
                    .clickable {
                        onClick(order.id)
                    }
            )
        }
    }
}