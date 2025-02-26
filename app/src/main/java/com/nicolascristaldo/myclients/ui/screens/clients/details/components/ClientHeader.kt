package com.nicolascristaldo.myclients.ui.screens.clients.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.nicolascristaldo.myclients.R
import com.nicolascristaldo.myclients.domain.model.Customer
import com.nicolascristaldo.myclients.ui.components.ClientImage

@Composable
fun ClientHeader(
    client: Customer,
    modifier: Modifier
) {
    Row(
        modifier = modifier
    ) {
        ClientImage(
            clientGenre = client.genre,
            modifier = Modifier
                .padding(
                    end = dimensionResource(R.dimen.padding_large),
                    bottom = dimensionResource(R.dimen.padding_large)
                )
                .size(dimensionResource(R.dimen.client_header_image_size))
        )
        ClientInformation(
            client = client
        )
    }
}

@Composable
fun ClientInformation(
    client: Customer,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = client.name)
        Text(
            text = stringResource(R.string.email_info, client.email)
        )
        if(client.phone.isNotBlank()) {
            Text(
                text = stringResource(R.string.phone_info, client.phone)
            )
        }
        if(client.address.isNotBlank()) {
            Text(
                text = stringResource(R.string.address_info, client.address)
            )
        }
    }
}