package com.nicolascristaldo.myclients.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nicolascristaldo.myclients.R

@Composable
fun DeleteConfirmationDialog(
    title: String = "attention",
    content: String = "",
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = title
            )
        },
        text = {
            Text(
                text = content
            )
        },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text(
                    text = stringResource(R.string.yes)
                )
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(
                    text = stringResource(R.string.no)
                )
            }
        },
        modifier = modifier
    )
}