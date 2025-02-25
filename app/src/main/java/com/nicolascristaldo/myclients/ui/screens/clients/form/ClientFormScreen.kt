package com.nicolascristaldo.myclients.ui.screens.clients.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nicolascristaldo.myclients.ui.components.AppTextField
import com.nicolascristaldo.myclients.ui.screens.clients.form.components.GenresDropDownMenu

@Composable
fun ClientFormScreen(
    clientUiState: ClientUiState,
    onButtonClick: () -> Unit,
    onValueChange: (ClientDetails) -> Unit,
    buttonTitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        AppTextField(
            value = clientUiState.clientDetails.name,
            onValueChange = { onValueChange(clientUiState.clientDetails.copy(name = it)) },
            label = "Name",
            validateInput = { isValidInput(it) },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        GenresDropDownMenu(
            clientDetails = clientUiState.clientDetails,
            onValueChange = onValueChange,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        AppTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            value = clientUiState.clientDetails.email,
            onValueChange = { onValueChange(clientUiState.clientDetails.copy(email = it)) },
            validateInput = { isValidEmail(it) },
            label = "Email",
            modifier = Modifier.padding(bottom = 8.dp)
        )

        AppTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            value = clientUiState.clientDetails.phone,
            onValueChange = { onValueChange(clientUiState.clientDetails.copy(phone = it)) },
            validateInput = { isValidPhone(it) },
            label = "Phone",
            modifier = Modifier.padding(bottom = 8.dp)
        )

        AppTextField(
            value = clientUiState.clientDetails.address,
            onValueChange = { onValueChange(clientUiState.clientDetails.copy(address = it)) },
            label = "Address",
            validateInput = { isValidInput(it) },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = { onButtonClick() },
            enabled = clientUiState.isEntryValid
        ) {
            Text(text = buttonTitle)
        }
    }
}



