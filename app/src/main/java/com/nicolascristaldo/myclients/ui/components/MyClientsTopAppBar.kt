package com.nicolascristaldo.myclients.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.nicolascristaldo.myclients.R
import com.nicolascristaldo.myclients.ui.navigation.AppDestinations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyClientsTopAppBar(
    currentScreen: AppDestinations,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    CenterAlignedTopAppBar(
        title = {
            Text(stringResource(currentScreen.title))
        },
        actions = {
            if (currentScreen.route == AppDestinations.ClientDetails.route) {
                IconButton(
                    onClick = { expanded = !expanded }
                ) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = stringResource(R.string.options)
                    )
                    ClientOptionsDropDownMenu(
                        expanded = expanded,
                        expandedChange = { expanded = false },
                        navController = navController
                    )
                }
            }
        },
        navigationIcon = {
            if (
                currentScreen.route != AppDestinations.Home.route &&
                currentScreen.route != AppDestinations.Clients.route &&
                currentScreen.route != AppDestinations.Orders.route
            ) {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.go_back)
                    )
                }
            }
        },
        modifier = modifier
    )
}