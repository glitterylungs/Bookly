package com.example.bookly.ui.authentication

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookly.repository.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class LoginViewModel(
    private val authenticationRepository: AuthenticationRepository,
) : ViewModel() {

    var email: MutableState<String> = mutableStateOf("")
    var password: MutableState<String> = mutableStateOf("")

    var errorMessage: MutableState<String?> = mutableStateOf(null)
        private set

    var isLoginSuccessful: MutableState<Boolean> = mutableStateOf(false)
        private set

    fun login() {
        if (email.value.isNotEmpty() && password.value.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                authenticationRepository.signIn(email.value, password.value)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            isLoginSuccessful.value = true
                        } else {
                            errorMessage.value = task.exception?.message
                            println(task.exception.toString())
                        }
                    }
            }
        } else {
            errorMessage.value = "Please fill in all of the required fields"
        }
    }
}