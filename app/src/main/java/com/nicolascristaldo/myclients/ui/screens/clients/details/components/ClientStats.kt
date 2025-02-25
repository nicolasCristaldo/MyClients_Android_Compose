package com.nicolascristaldo.myclients.ui.screens.clients.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.nicolascristaldo.myclients.ui.components.StatCard

@Composable
fun ClientStats(
    totalPaid: Double,
    totalPending: Double,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        StatCard(
            title = "Total paid",
            value = totalPaid.toString(),
            color = Color.Green
        )
        StatCard(
            title = "Total pending",
            value = totalPending.toString(),
            color = Color.Red
        )
    }
}