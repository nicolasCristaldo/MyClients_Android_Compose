package com.nicolascristaldo.myclients.ui.screens.orders.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.nicolascristaldo.myclients.R
import com.nicolascristaldo.myclients.domain.model.Order
import com.nicolascristaldo.myclients.ui.screens.orders.list.components.OrderListHeader
import com.nicolascristaldo.myclients.ui.screens.orders.list.components.OrderListScreen

@Composable
fun OrdersScreen(
    totalEarned: Double,
    totalPending: Double,
    orders: List<Order>,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        OrderListHeader(
            totalPaid = totalEarned,
            totalPending = totalPending,
            modifier = Modifier.fillMaxWidth()
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_normal))
        )

        OrderListScreen(
            orders = orders,
            onClick = onClick
        )
    }
}






