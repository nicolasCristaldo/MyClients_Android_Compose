package com.nicolascristaldo.myclients.ui.components

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nicolascristaldo.myclients.data.providers.getClientOptions
import com.nicolascristaldo.myclients.ui.screens.clients.details.ClientDetailsViewModel

@Composable
fun ClientOptionsDropDownMenu(
    clientDetailsViewModel: ClientDetailsViewModel = hiltViewModel(),
    navController: NavHostController,
    expanded: Boolean,
    expandedChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    val options = getClientOptions(
        clientDetailsViewModel = clientDetailsViewModel,
        navController = navController
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expandedChange() },
        modifier = modifier
    ) {
        options.forEachIndexed { index, option ->
            if (index != 0) HorizontalDivider()
            DropdownMenuItem(
                text = { Text(option.title) },
                onClick = {
                    option.onClick()
                    expandedChange()
                }
            )
        }
    }
}