package com.nicolascristaldo.myclients.ui.screens.clients.list

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nicolascristaldo.myclients.data.providers.GenreProvider
import com.nicolascristaldo.myclients.domain.model.Customer

@Composable
fun ClientsScreen(
    viewModel: ClientsScreenViewModel = hiltViewModel(),
    onClientClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val clients by viewModel.clients.collectAsState()

    ClientListScreen(
        clients = clients,
        onClientClick = onClientClick,
        modifier = modifier
    )
}

@Composable
fun ClientListScreen(
    clients: List<Customer>,
    onClientClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(clients) { client ->
            ClientCard(
                client = client,
                onClick = onClientClick,
                modifier = Modifier.padding(8.dp)
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
            .clip(RoundedCornerShape(16.dp))
            .height(80.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = GenreProvider.getGenreByName(client.genre).imageRes),
                contentDescription = client.genre,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(72.dp)
            )

            Text(
                text = client.name,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
