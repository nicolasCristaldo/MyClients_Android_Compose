package com.nicolascristaldo.myclients.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nicolascristaldo.myclients.R
import com.nicolascristaldo.myclients.domain.model.Order
import com.nicolascristaldo.myclients.ui.screens.orders.list.components.OrderListScreen

@Composable
fun HomeScreen(
    navigateToAddClient: () -> Unit,
    navigateToOrderDetails: (Int) -> Unit,
    lastOrders: List<Order>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        HomeCard(
            imageRes = R.drawable.add_client_card_image,
            onClick = { navigateToAddClient() },
            title = stringResource(R.string.add_client),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(dimensionResource(R.dimen.padding_large))
        )
        Text(
            text = stringResource(R.string.last_orders),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        OrderListScreen(
            orders = lastOrders,
            onClick = { navigateToOrderDetails(it) }
        )
    }
}

@Composable
fun HomeCard(
    imageRes: Int,
    onClick: () -> Unit,
    title: String,
    modifier: Modifier
) {
    Card(
        onClick = { onClick() },
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = title,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_normal))
                    .align(Alignment.BottomStart)
            )
        }
    }
}