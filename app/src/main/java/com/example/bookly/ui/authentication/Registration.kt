package com.example.bookly.ui.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Registration(
    viewModel: RegistrationViewModel = koinViewModel(),
    navigateToLogin: () -> Unit
) {
    val email by remember { viewModel.email }
    val password by remember { viewModel.password }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Registration") },
                navigationIcon = {
                    IconButton(onClick = navigateToLogin) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Return")
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            TextField(
                value = email,
                onValueChange = { newValue ->
                    viewModel.email.value = newValue
                }
            )

            TextField(
                value = password,
                onValueChange = { newValue ->
                    viewModel.password.value = newValue
                }
            )

            Button(onClick = navigateToLogin) {
                Text(text = "Register")
            }
        }
    }
}