package com.nicolascristaldo.myclients.ui.screens.orders.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OrderFormScreen(
    orderUiState: OrderUiState,
    onValueChange: (OrderDetails) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Order form screen")
        OutlinedTextField(
            value = orderUiState.orderDetails.description,
            onValueChange = {
                onValueChange(orderUiState.orderDetails.copy(description = it))
            },
            label = { Text(text = "Description") },
            maxLines = 5,
            singleLine = false
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        OutlinedTextField(
            value = orderUiState.orderDetails.total,
            onValueChange = {
                onValueChange(orderUiState.orderDetails.copy(total = it))
            },
            label = { Text(text = "Total") },
            singleLine = true
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        Switch(
            checked = orderUiState.orderDetails.isPaid,
            onCheckedChange = {
                onValueChange(orderUiState.orderDetails.copy(isPaid = it))
            }
        )

        Spacer(modifier = Modifier.padding(vertical = 16.dp))

        Button(
            onClick = { onClick() },
            enabled = orderUiState.isEntryValid
        ) {
            Text(text = "Save")
        }
        Text(
            text = "id: ${orderUiState.orderDetails.id}"
        )
    }
}