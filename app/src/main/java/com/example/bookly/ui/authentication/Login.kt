package com.example.bookly.ui.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookly.ui.theme.BooklyTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Login(
    viewModel: LoginViewModel = koinViewModel(),
    navigateToRegistration: () -> Unit,
    navigateToMainScreen: () -> Unit,
) {
    val email by remember { viewModel.email }
    val password by remember { viewModel.password }
    val isLoginSuccessful by remember { viewModel.isLoginSuccessful }

    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            Text(text = "Bookly")
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

            Button(onClick = { if (isLoginSuccessful) navigateToMainScreen() }) {
                Text(text = "Login")
            }

            TextButton(onClick = navigateToRegistration) {
                Text(text = "Don't have an account? Register here.")
            }
        }
    }
}

@Preview
@Composable
private fun LoginPreview() {
    BooklyTheme {
        Surface {
            Login(navigateToRegistration = {}) {}
        }
    }
}