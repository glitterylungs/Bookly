package com.example.bookly.repository

import com.example.bookly.api.model.Books

internal interface BookRepository {

    suspend fun getBooksByQuery(query: String): Result<Books>
}