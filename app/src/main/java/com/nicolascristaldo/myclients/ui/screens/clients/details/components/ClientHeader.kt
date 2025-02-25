package com.nicolascristaldo.myclients.ui.screens.clients.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
                .padding(end = 16.dp, bottom = 16.dp)
                .size(100.dp)
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
            text = "email: " + client.email
        )
        if(client.phone.isNotBlank()) {
            Text(
                text = "phone: " + client.phone
            )
        }
        if(client.address.isNotBlank()) {
            Text(
                text = "address: " + client.address
            )
        }
    }
}