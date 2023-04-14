package com.example.bookly.di

import com.example.bookly.ui.MainActivityViewModel
import com.example.bookly.ui.authentication.LoginViewModel
import com.example.bookly.ui.authentication.RegistrationViewModel
import com.example.bookly.ui.main.BookDetailsViewModel
import com.example.bookly.ui.main.BookListViewModel
import com.example.bookly.ui.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {

    viewModel {
        MainActivityViewModel(
            authenticationRepository = get()
        )
    }

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

    viewModel {
        BookListViewModel(
            authenticationRepository = get(),
            realtimeDatabaseRepository = get(),
            bookRepository = get(),
            sharedPreferencesManager = get()
        )
    }

    viewModel {
        BookDetailsViewModel(
            authenticationRepository = get(),
            bookRepository = get(),
            realtimeDatabaseRepository = get()
        )
    }

    viewModel {
        SettingsViewModel(
            sharedPreferencesManager = get(),
            authenticationRepository = get()
        )
    }
}