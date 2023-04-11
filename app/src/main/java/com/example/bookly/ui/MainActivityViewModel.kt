package com.example.bookly.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bookly.repository.AuthenticationRepository

internal class MainActivityViewModel(
    authenticationRepository: AuthenticationRepository,
) : ViewModel() {

    var isLoggedIn: MutableState<Boolean> =
        mutableStateOf(authenticationRepository.checkIfSignedIn())
}