package com.example.bookly.ui.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bookly.manager.SharedPreferencesManager
import com.example.bookly.repository.AuthenticationRepository

internal class SettingsViewModel(
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val authenticationRepository: AuthenticationRepository,
) : ViewModel() {

    val isLargeViewEnabled = mutableStateOf(sharedPreferencesManager.getBoolean("accessibility"))

    fun largeViewSwitched(isEnabled: Boolean) {
        sharedPreferencesManager.setBoolean("accessibility", isEnabled)
        isLargeViewEnabled.value = isEnabled
        println(isEnabled)
    }

    fun signOut() {
        authenticationRepository.signOut()
    }
}