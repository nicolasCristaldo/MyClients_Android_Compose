package com.nicolascristaldo.myclients.ui.screens.orders.form.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LabeledSwitch(
    title: String,
    description: String = "",
    isPaid: Boolean,
    onValueChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title)
            Text(text = description)
        }
        Spacer(Modifier.weight(1f))
        VerticalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        Switch(
            checked = isPaid,
            onCheckedChange = { onValueChange(it) },
            thumbContent = {
                if (isPaid) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null
                    )
                }
            }
        )
    }
}