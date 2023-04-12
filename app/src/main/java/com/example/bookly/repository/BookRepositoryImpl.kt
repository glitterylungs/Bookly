package com.example.bookly.repository

import com.example.bookly.api.model.Books
import com.example.bookly.api.service.BookService

internal class BookRepositoryImpl(
    private val bookService: BookService,
) : BookRepository {

    override suspend fun getBooksByQuery(query: String): Result<Books> =
        bookService.getBooksByQuery(
            query,
            20,
            "AIzaSyBl6d0nkqnAHc1CQ5fBSJNUjLNPcFDas5o"
        )
}