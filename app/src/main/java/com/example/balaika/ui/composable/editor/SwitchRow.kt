package com.example.balaika.ui.composable.editor

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.balaika.ui.theme.WoodyCrayonWhite

@Composable
fun SwitchRow(
    @StringRes label: Int,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(top = 12.dp)
    ) {
        Text(
            text = stringResource(id = label),
            color = WoodyCrayonWhite,
            style = MaterialTheme.typography.bodyMedium
        )
        Switch(
            checked = false,
            onCheckedChange = onCheckedChange
        )
    }
}