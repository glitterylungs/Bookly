package com.example.bookly.ui.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookly.ui.common.EmailTextField
import com.example.bookly.ui.common.PasswordTextField
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Registration(
    viewModel: RegistrationViewModel = koinViewModel(),
    navigateToLogin: () -> Unit
) {
    val email by remember { viewModel.email }
    val password by remember { viewModel.password }
    val confirmPassword by remember { viewModel.confirmPassword }
    val isRegistrationSuccessful by remember { viewModel.isRegistrationSuccessful }
    val errorMessage by remember { viewModel.errorMessage }
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = isRegistrationSuccessful) {
        if (isRegistrationSuccessful) {
            navigateToLogin()
        }
    }

    LaunchedEffect(key1 = errorMessage) {
        errorMessage?.let {
            snackBarHostState.showSnackbar(message = it)
        }
    }

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
        },
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
                text = "Sign Up",
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

            PasswordTextField(
                password = confirmPassword,
                onPasswordChange = { newValue ->
                    viewModel.confirmPassword.value = newValue
                },
                label = "Confirm Password"
            )

            Button(
                onClick = { viewModel.register() }
            ) {
                Text(text = "Register")
            }
        }
    }
}