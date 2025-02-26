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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.nicolascristaldo.myclients.R
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
            label = stringResource(R.string.name),
            validateInput = { isValidInput(it) },
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_normal))
        )

        GenresDropDownMenu(
            clientDetails = clientUiState.clientDetails,
            onValueChange = onValueChange,
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_normal))
        )

        AppTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            value = clientUiState.clientDetails.email,
            onValueChange = { onValueChange(clientUiState.clientDetails.copy(email = it)) },
            validateInput = { isValidEmail(it) },
            label = stringResource(R.string.email),
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_normal))
        )

        AppTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            value = clientUiState.clientDetails.phone,
            onValueChange = { onValueChange(clientUiState.clientDetails.copy(phone = it)) },
            validateInput = { isValidPhone(it) },
            label = stringResource(R.string.phone),
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_normal))
        )

        AppTextField(
            value = clientUiState.clientDetails.address,
            onValueChange = { onValueChange(clientUiState.clientDetails.copy(address = it)) },
            label = stringResource(R.string.address),
            validateInput = { isValidInput(it) },
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_large))
        )

        Button(
            onClick = { onButtonClick() },
            enabled = clientUiState.isEntryValid
        ) {
            Text(text = buttonTitle)
        }
    }
}



