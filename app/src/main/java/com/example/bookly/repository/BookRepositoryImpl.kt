package com.example.bookly.repository

import com.example.bookly.BuildConfig
import com.example.bookly.api.model.Books
import com.example.bookly.api.model.Item
import com.example.bookly.api.service.BookService

internal class BookRepositoryImpl(
    private val bookService: BookService,
) : BookRepository {

    override suspend fun getBooksByQuery(query: String): Books =
        bookService.getBooksByQuery(
            query,
            20,
            BuildConfig.API_KEY
        )

    override suspend fun getBookById(id: String): Item =
        bookService.getBookById(id)
}