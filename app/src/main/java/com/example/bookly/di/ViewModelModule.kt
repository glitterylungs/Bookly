package com.example.bookly.di

import com.example.bookly.ui.authentication.LoginViewModel
import com.example.bookly.ui.authentication.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel {
        RegistrationViewModel(
            authenticationRepository = get()
        )
    }

    viewModel {
        LoginViewModel(
            authenticationRepository = get()
        )
    }
}