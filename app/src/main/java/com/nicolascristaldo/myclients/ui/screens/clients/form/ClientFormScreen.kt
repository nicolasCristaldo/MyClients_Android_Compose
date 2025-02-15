package com.nicolascristaldo.myclients.ui.screens.clients.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ClientFormScreen(
    clientUiState: ClientUiState,
    onClick: () -> Unit,
    onValueChange: (ClientDetails) -> Unit,
    buttonTitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "Client form screen")
        OutlinedTextField(
            value = clientUiState.clientDetails.name,
            onValueChange = {
                onValueChange(clientUiState.clientDetails.copy(name = it))
            },
            label = { Text(text = "Name") },
            singleLine = true
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        OutlinedTextField(
            value = clientUiState.clientDetails.email,
            onValueChange = {
                onValueChange(clientUiState.clientDetails.copy(email = it))
            },
            label = { Text(text = "Email") },
            singleLine = true
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        OutlinedTextField(
            value = clientUiState.clientDetails.phone,
            onValueChange = {
                onValueChange(clientUiState.clientDetails.copy(phone = it))
            },
            label = { Text(text = "Phone") },
            singleLine = true
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        OutlinedTextField(
            value = clientUiState.clientDetails.address,
            onValueChange = {
                onValueChange(clientUiState.clientDetails.copy(address = it))
            },
            label = { Text(text = "Address") },
            singleLine = true
        )

        Spacer(modifier = Modifier.padding(vertical = 16.dp))

        Button(
            onClick = { onClick() },
            enabled = clientUiState.isEntryValid
        ) {
            Text(text = buttonTitle)
        }
    }
}