package com.example.bookly.ui.authentication

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookly.repository.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class RegistrationViewModel(
    private val authenticationRepository: AuthenticationRepository,
) : ViewModel() {

    var email: MutableState<String> = mutableStateOf("")
    var password: MutableState<String> = mutableStateOf("")

    var errorMessage: MutableState<String?> = mutableStateOf(null)
        private set

    var isRegistrationSuccessful: MutableState<Boolean> = mutableStateOf(false)
        private set

    private fun register() {
        viewModelScope.launch(Dispatchers.IO) {
            authenticationRepository.signUp(email.value, password.value)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        isRegistrationSuccessful.value = true
                    } else {
                        errorMessage.value = task.exception.toString()
                    }
                }
        }
    }
}