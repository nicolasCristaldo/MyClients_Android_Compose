package com.nicolascristaldo.myclients.ui.screens.clients.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nicolascristaldo.myclients.data.providers.GenreProvider
import com.nicolascristaldo.myclients.ui.screens.orders.list.OrdersScreen

@Composable
fun ClientDetailsScreen(
    viewModel: ClientDetailsViewModel,
    onEditClick: (Int) -> Unit,
    onAddOrderClick: () -> Unit,
    onOrderClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val client by viewModel.client.collectAsState()
    val orders by viewModel.clientOrders.collectAsState()
    val totalPaid by viewModel.totalPaid.collectAsState()
    val totalPending by viewModel.totalPending.collectAsState()

    if (client != null) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row() {
                Image(
                    painter = painterResource(id = GenreProvider.getGenreByName(client!!.genre).imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 16.dp, bottom = 16.dp)
                        .size(100.dp)
                )
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = client!!.name)
                    Text(
                        text = "email: " + client!!.email
                    )
                    if(client!!.phone.isNotBlank()) {
                        Text(
                            text = "phone: " + client!!.phone
                        )
                    }
                    if(client!!.address.isNotBlank()) {
                        Text(
                            text = "address: " + client!!.address
                        )
                    }
                }
            }
            ClientStats(
                totalPaid = totalPaid,
                totalPending = totalPending,
                modifier = Modifier.fillMaxWidth()
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            OrdersScreen(
                orders = orders,
                onClick = onOrderClick
            )
        }
    }
}

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

@Composable
fun StatCard(
    title: String,
    value: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp)
    ) {
        Text(
            text = value,
            color = color,
            fontSize = 28.sp,
        )

        Text(
            text = title,
            fontSize = 12.sp
        )
    }
}