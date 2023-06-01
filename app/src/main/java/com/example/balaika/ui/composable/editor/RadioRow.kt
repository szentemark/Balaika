package com.example.balaika.ui.composable.editor

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun RadioRow(
    options: Map<Int, Int>,
    selectedValue: Int,
    onSelect: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        options.forEach {
            RadioButton(
                selected = selectedValue == it.key,
                onClick = { onSelect(it.key) },
                colors = RadioButtonDefaults.colors(
                    unselectedColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                    selectedColor = MaterialTheme.colorScheme.onBackground
                )
            )
            Text(
                text = stringResource(id = it.value),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.clickable { onSelect(it.key) }
            )
        }
    }
}