package com.example.bookly

import android.app.Application
import com.example.bookly.di.networkModule
import com.example.bookly.di.repositoryModule
import com.example.bookly.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BooklyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BooklyApplication)
            modules(
                repositoryModule,
                viewModelModule,
                networkModule,
            )
        }
    }
}