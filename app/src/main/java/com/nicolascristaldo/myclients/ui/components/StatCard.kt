package com.nicolascristaldo.myclients.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.nicolascristaldo.myclients.R
import java.text.NumberFormat

@Composable
fun StatCard(
    title: String,
    value: Double,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(dimensionResource(R.dimen.padding_normal))
    ) {
        Text(
            text = NumberFormat.getCurrencyInstance().format(value),
            color = color,
            fontSize = 32.sp,
        )

        Text(
            text = title,
            fontSize = 12.sp
        )
    }
}