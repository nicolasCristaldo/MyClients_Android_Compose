package com.nicolascristaldo.myclients.ui.screens.clients.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.nicolascristaldo.myclients.ui.screens.clients.list.components.ClientListScreen

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
        modifier = Modifier.fillMaxSize()
    )
}


