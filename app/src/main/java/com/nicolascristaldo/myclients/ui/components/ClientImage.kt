package com.nicolascristaldo.myclients.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.nicolascristaldo.myclients.data.providers.GenreProvider

@Composable
fun ClientImage(
    clientGenre: String,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = GenreProvider.getGenreByName(clientGenre).imageRes),
        contentDescription = clientGenre,
        modifier = modifier
    )
}