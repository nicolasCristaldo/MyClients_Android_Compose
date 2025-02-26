package com.nicolascristaldo.myclients.ui.screens.orders.form.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nicolascristaldo.myclients.R

@Composable
fun OrderFormButtons(
    id: Int,
    enabled: Boolean,
    onSave: () -> Unit,
    onDelete: () -> Unit = {},
    modifier: Modifier = Modifier
) {


    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Button(
            onClick = { onSave() },
            enabled = enabled
        ) {
            Text(text = stringResource(R.string.save))
        }
        if(id != 0) {
            Spacer(modifier = Modifier.padding(horizontal = 16.dp))
            Button(
                onClick = { onDelete() },
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                )
            ) {
                Text(text = stringResource(R.string.delete))
            }
        }
    }
}

