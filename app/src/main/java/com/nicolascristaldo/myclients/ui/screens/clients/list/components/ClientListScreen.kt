package com.nicolascristaldo.myclients.ui.screens.clients.list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import com.nicolascristaldo.myclients.R
import com.nicolascristaldo.myclients.domain.model.Customer

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