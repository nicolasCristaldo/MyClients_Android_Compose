package com.nicolascristaldo.myclients.ui.screens.orders.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.nicolascristaldo.myclients.R
import com.nicolascristaldo.myclients.ui.components.StatCard

@Composable
fun OrderListHeader(
    totalPaid: Double,
    totalPending: Double,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        StatCard(
            title = stringResource(R.string.total_earned),
            value = totalPaid.toString(),
            color = Color.Green
        )
        StatCard(
            title = stringResource(R.string.total_pending),
            value = totalPending.toString(),
            color = Color.Red
        )
    }
}