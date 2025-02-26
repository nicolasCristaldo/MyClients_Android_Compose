package com.nicolascristaldo.myclients.ui.screens.clients.list.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import com.nicolascristaldo.myclients.R
import com.nicolascristaldo.myclients.domain.model.Customer
import com.nicolascristaldo.myclients.ui.components.ClientImage

@Composable
fun ClientListScreen(
    clients: List<Customer>,
    onClientClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(clients) { client ->
            ClientCard(
                client = client,
                onClick = onClientClick,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_normal))
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)))
                    .height(dimensionResource(R.dimen.client_card_height))
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun ClientCard(
    client: Customer,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onClick(client.id) },
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_normal))
                .fillMaxSize()
        ) {
            ClientImage(
                clientGenre = client.genre,
                modifier = Modifier
                    .padding(end = dimensionResource(R.dimen.padding_large))
                    .size(dimensionResource(R.dimen.client_card_image_size))
            )

            Text(
                text = client.name,
                modifier = Modifier.weight(1f)
            )
        }
    }
}