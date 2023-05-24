package com.example.balaika.ui.composable.editor

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.balaika.ui.theme.DarkBrownCrayonCream
import com.example.balaika.ui.theme.DarkBrownCrayonDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextRow(
    @StringRes label: Int,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        label = {
            Text(text = stringResource(id = label))
        },
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = DarkBrownCrayonCream,
            unfocusedBorderColor = DarkBrownCrayonDark,
            focusedLabelColor = DarkBrownCrayonCream,
            unfocusedLabelColor = DarkBrownCrayonCream,
            textColor = DarkBrownCrayonCream
        ),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth(0.94f)
            .padding(top = 12.dp)
    )
}