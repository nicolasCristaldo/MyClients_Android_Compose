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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.nicolascristaldo.myclients.R
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
            label = stringResource(R.string.description),
            validateInput = { isValidInput(it) },
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_normal))
        )

        AppTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            value = orderUiState.orderDetails.total,
            onValueChange = { onValueChange(orderUiState.orderDetails.copy(total = it)) },
            validateInput = { isValidPrice(it) },
            label = stringResource(R.string.total),
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_normal))
        )

        LabeledSwitch(
            title = stringResource(R.string.paid_status),
            description = stringResource(R.string.status_question),
            isPaid = orderUiState.orderDetails.isPaid,
            onValueChange = { onValueChange(orderUiState.orderDetails.copy(isPaid = it)) },
            modifier = Modifier
                .height(dimensionResource(R.dimen.labeled_switch_height))
                .width(dimensionResource(R.dimen.text_field_width))
                .padding(bottom = dimensionResource(R.dimen.padding_large))
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
            content = stringResource(R.string.delete_order_question),
            onConfirm = {
                deleteOrder()
                isDeleteDialogVisible = false
            },
            onDismiss = { isDeleteDialogVisible = false }
        )
    }
}



