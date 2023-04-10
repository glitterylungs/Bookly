package com.example.bookly.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
) {
    TextField(
        value = email,
        onValueChange = onEmailChange,
        modifier = Modifier.padding(bottom = 15.dp),
        label = { Text(text = "E-mail") },
        placeholder = { Text(text = "john.doe@example.com") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
    )
}