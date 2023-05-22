package com.example.bookly.di

import com.example.bookly.BuildConfig
import com.example.bookly.api.service.BookService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<GsonConverterFactory> {
        GsonConverterFactory.create()
    }

    single {
        retrofitBuilder(get(), BuildConfig.BOOKS_BASE_URL)
    }

    single<BookService> {
        createService(get())
    }
}

private fun retrofitBuilder(
    gsonConverterFactory: GsonConverterFactory,
    baseUrl: String
): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(gsonConverterFactory)
    .build()

private inline fun <reified T> createService(retrofit: Retrofit): T =
    retrofit.create(T::class.java)