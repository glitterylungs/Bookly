package com.example.bookly.ui.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookly.ui.common.EmailTextField
import com.example.bookly.ui.common.PasswordTextField
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
    var errorMessage by remember { viewModel.errorMessage }
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = isLoginSuccessful) {
        if (isLoginSuccessful) {
            navigateToMainScreen()
        }
    }

    LaunchedEffect(key1 = errorMessage) {
        errorMessage?.let {
            snackBarHostState.showSnackbar(message = it)
            errorMessage = null
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sign In",
                modifier = Modifier.padding(bottom = 40.dp),
                style = MaterialTheme.typography.headlineMedium
            )

            EmailTextField(
                email = email,
                onEmailChange = { newValue ->
                    viewModel.email.value = newValue
                })

            PasswordTextField(
                password = password,
                onPasswordChange = { newValue ->
                    viewModel.password.value = newValue
                },
                label = "Password"
            )

            Button(
                onClick = { viewModel.login() },
                modifier = Modifier.padding(vertical = 10.dp),
            ) {
                Text(text = "Login")
            }

            TextButton(onClick = navigateToRegistration) {
                Text(text = "Don't have an account? Register here.", textAlign = TextAlign.Center)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginPreview() {
    BooklyTheme {
        Surface {
            Login(navigateToRegistration = {}) {}
        }
    }
}