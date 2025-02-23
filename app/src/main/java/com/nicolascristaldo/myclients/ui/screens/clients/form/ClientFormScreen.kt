package com.nicolascristaldo.myclients.ui.screens.clients.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nicolascristaldo.myclients.data.providers.GenreProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientFormScreen(
    clientUiState: ClientUiState,
    onClick: () -> Unit,
    onValueChange: (ClientDetails) -> Unit,
    buttonTitle: String,
    modifier: Modifier = Modifier
) {
    var dropdownIsExpanded by remember { mutableStateOf(false) }
    val genres = GenreProvider.genres
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

        ExposedDropdownMenuBox(
            expanded = dropdownIsExpanded,
            onExpandedChange = { dropdownIsExpanded = !dropdownIsExpanded },
        ) {
            OutlinedTextField(
                value = clientUiState.clientDetails.genre,
                onValueChange = { },
                label = { Text(text = "Genre") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdownIsExpanded) },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = dropdownIsExpanded,
                onDismissRequest = { dropdownIsExpanded = false }
            ) {
                genres.forEachIndexed { index, genre ->
                    if (index != 0) HorizontalDivider()
                    DropdownMenuItem(
                        text = { Text(text = genre) },
                        onClick = {
                            onValueChange(clientUiState.clientDetails.copy(genre = genre))
                            dropdownIsExpanded = false
                        }
                    )
                }
            }
        }

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