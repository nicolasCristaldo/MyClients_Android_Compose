package com.nicolascristaldo.myclients.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nicolascristaldo.myclients.R
import com.nicolascristaldo.myclients.domain.model.Order
import com.nicolascristaldo.myclients.ui.screens.home.components.HomeCard
import com.nicolascristaldo.myclients.ui.screens.orders.list.components.OrderListScreen

@Composable
fun HomeScreen(
    navigateToAddClient: () -> Unit,
    navigateToOrderDetails: (Int) -> Unit,
    lastOrders: List<Order>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        HomeCard(
            onClick = { navigateToAddClient() },
            title = stringResource(R.string.add_client),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(dimensionResource(R.dimen.padding_large))
        )

        HorizontalDivider()

        Text(
            text = stringResource(R.string.last_orders),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        OrderListScreen(
            orders = lastOrders,
            onClick = { navigateToOrderDetails(it) }
        )
    }
}