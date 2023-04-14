package com.example.bookly.di

import com.example.bookly.manager.SharedPreferencesManager
import com.example.bookly.manager.SharedPreferencesManagerImpl
import org.koin.dsl.module

val managerModule = module {

    single<SharedPreferencesManager> {
        SharedPreferencesManagerImpl(
            context = get()
        )
    }
}