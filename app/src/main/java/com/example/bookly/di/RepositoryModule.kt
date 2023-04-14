package com.example.bookly.di

import com.example.bookly.BuildConfig
import com.example.bookly.repository.AuthenticationRepository
import com.example.bookly.repository.AuthenticationRepositoryImpl
import com.example.bookly.repository.BookRepository
import com.example.bookly.repository.BookRepositoryImpl
import com.example.bookly.repository.RealtimeDatabaseRepository
import com.example.bookly.repository.RealtimeDatabaseRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.koin.dsl.module

internal val repositoryModule = module {

    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl(
            auth = FirebaseAuth.getInstance()
        )
    }

    single<RealtimeDatabaseRepository> {
        RealtimeDatabaseRepositoryImpl(
            database = FirebaseDatabase.getInstance(BuildConfig.FIREBASE_DATABASE)
        )
    }

    single<BookRepository> {
        BookRepositoryImpl(
            bookService = get(),
        )
    }
}