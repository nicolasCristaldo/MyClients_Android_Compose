package com.nicolascristaldo.myclients.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nicolascristaldo.myclients.ui.navigation.AppDestinations

@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Home screen")
        HomeCard(
            onClick = { navController.navigate(AppDestinations.ClientFormAdd.route) },
            title = "add client",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        HomeCard(
            onClick = { navController.navigate(AppDestinations.Stats.route) },
            title = "Stats",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
    }
}

@Composable
fun HomeCard(
    onClick: () -> Unit,
    title: String,
    modifier: Modifier
) {
    Card(
        onClick = { onClick() },
        modifier = modifier
    ) {
        Text(text = title)
    }
}