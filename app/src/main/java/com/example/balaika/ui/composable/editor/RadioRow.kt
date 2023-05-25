package com.example.balaika.ui.composable.editor

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.balaika.ui.theme.DarkBrownCrayonCream
import com.example.balaika.ui.theme.DarkBrownCrayonDark

@Composable
fun RadioRow(
    options: Map<Int, Int>,
    selectedValue: Int,
    onSelect: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 12.dp)
    ) {
        options.forEach {
            RadioButton(
                selected = selectedValue == it.key,
                onClick = { onSelect(it.key) },
                colors = RadioButtonDefaults.colors(
                    unselectedColor = DarkBrownCrayonDark,
                    selectedColor = DarkBrownCrayonCream
                )
            )
            Text(
                text = stringResource(id = it.value),
                color = DarkBrownCrayonCream,
                modifier = Modifier.clickable { onSelect(it.key) }
            )
        }
    }
}