package com.nicolascristaldo.myclients.ui.screens.clients.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.nicolascristaldo.myclients.R
import com.nicolascristaldo.myclients.domain.model.Customer
import com.nicolascristaldo.myclients.ui.screens.clients.list.components.ClientListHeader
import com.nicolascristaldo.myclients.ui.screens.clients.list.components.ClientListScreen

@Composable
fun ClientsScreen(
    clients: List<Customer>,
    onClientClick: (Int) -> Unit,
    onSearchValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        ClientListHeader(
            onValueChange = onSearchValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        HorizontalDivider(
            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_normal))
        )
        ClientListScreen(
            clients = clients,
            onClientClick = onClientClick,
            modifier = Modifier.fillMaxSize()
        )
    }
}


