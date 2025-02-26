package com.nicolascristaldo.myclients.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.nicolascristaldo.myclients.R
import com.nicolascristaldo.myclients.domain.model.Order
import com.nicolascristaldo.myclients.ui.screens.orders.form.formatedPrice

@Composable
fun OrderCard(
    order: Order,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.order_info, order.id),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = order.description)
        Text(
            text = stringResource(R.string.status_info, if (order.isPaid) {
                stringResource(R.string.paid)
            } else {
                stringResource(R.string.not_paid)
            })
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Text(text = stringResource(R.string.total_info, order.formatedPrice()))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = order.date)
        }
        HorizontalDivider()
    }
}