package com.example.bookly.di

import com.example.bookly.repository.AuthenticationRepository
import com.example.bookly.repository.AuthenticationRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

internal val repositoryModule = module {

    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl(
            auth = FirebaseAuth.getInstance()
        )
    }
}