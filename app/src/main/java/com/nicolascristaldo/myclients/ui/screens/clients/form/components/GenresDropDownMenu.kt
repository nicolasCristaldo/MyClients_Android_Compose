package com.nicolascristaldo.myclients.ui.screens.clients.form.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nicolascristaldo.myclients.data.providers.GenreProvider
import com.nicolascristaldo.myclients.ui.screens.clients.form.ClientDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenresDropDownMenu(
    clientDetails: ClientDetails,
    onValueChange: (ClientDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    var dropdownIsExpanded by remember { mutableStateOf(false) }
    val genres = GenreProvider.genres

    ExposedDropdownMenuBox(
        expanded = dropdownIsExpanded,
        onExpandedChange = { dropdownIsExpanded = !dropdownIsExpanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = clientDetails.genre,
            onValueChange = { },
            label = { Text(text = "Genre") },
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdownIsExpanded) },
            modifier = Modifier
                .menuAnchor()
                .width(300.dp)
        )
        ExposedDropdownMenu(
            expanded = dropdownIsExpanded,
            onDismissRequest = { dropdownIsExpanded = false }
        ) {
            genres.forEachIndexed { index, genre ->
                if (index != 0) HorizontalDivider()
                DropdownMenuItem(
                    text = { Text(text = genre) },
                    onClick = {
                        onValueChange(clientDetails.copy(genre = genre))
                        dropdownIsExpanded = false
                    }
                )
            }
        }
    }
}