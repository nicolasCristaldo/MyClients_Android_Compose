package com.nicolascristaldo.myclients.ui.screens.orders.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nicolascristaldo.myclients.ui.components.AppTextField
import com.nicolascristaldo.myclients.ui.components.DeleteConfirmationDialog
import com.nicolascristaldo.myclients.ui.screens.clients.form.isValidInput
import com.nicolascristaldo.myclients.ui.screens.orders.form.components.LabeledSwitch
import com.nicolascristaldo.myclients.ui.screens.orders.form.components.OrderFormButtons

@Composable
fun OrderFormScreen(
    orderUiState: OrderUiState,
    onValueChange: (OrderDetails) -> Unit,
    onSave: () -> Unit,
    deleteOrder: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var isDeleteDialogVisible by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        AppTextField(
            value = orderUiState.orderDetails.description,
            onValueChange = { onValueChange(orderUiState.orderDetails.copy(description = it)) },
            label = "Description",
            validateInput = { isValidInput(it) },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        AppTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            value = orderUiState.orderDetails.total,
            onValueChange = { onValueChange(orderUiState.orderDetails.copy(total = it)) },
            validateInput = { isValidPrice(it) },
            label = "Total",
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LabeledSwitch(
            title = "Paid status",
            description = "Is the order paid?",
            isPaid = orderUiState.orderDetails.isPaid,
            onValueChange = { onValueChange(orderUiState.orderDetails.copy(isPaid = it)) },
            modifier = Modifier
                .height(70.dp)
                .width(300.dp)
                .padding(bottom = 16.dp)
        )

        OrderFormButtons(
            id = orderUiState.orderDetails.id,
            enabled = orderUiState.isEntryValid,
            onSave = onSave,
            onDelete = { isDeleteDialogVisible = true },
            modifier = Modifier.fillMaxWidth()
        )
    }

    if (isDeleteDialogVisible) {
        DeleteConfirmationDialog(
            content = "Are you sure you want to delete this order?",
            onConfirm = {
                deleteOrder()
                isDeleteDialogVisible = false
            },
            onDismiss = { isDeleteDialogVisible = false }
        )
    }
}



