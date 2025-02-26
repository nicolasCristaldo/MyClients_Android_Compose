package com.nicolascristaldo.myclients.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.nicolascristaldo.myclients.R

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    validateInput: (String) -> Boolean,
    modifier: Modifier = Modifier
) {
    var isValidValue by remember { mutableStateOf(true) }

    OutlinedTextField(
        keyboardOptions = keyboardOptions,
        value = value,
        onValueChange = {
            onValueChange(it)
            isValidValue = validateInput(it)
        },
        label = { Text(text = label) },
        isError = !isValidValue,
        singleLine = true,
        modifier = modifier
            .width(dimensionResource(R.dimen.text_field_width))
    )
}