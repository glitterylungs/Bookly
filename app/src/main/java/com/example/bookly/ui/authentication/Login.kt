package com.example.bookly.ui.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun Login() {
    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            Text(text = "Bookly")
        }
    }
}