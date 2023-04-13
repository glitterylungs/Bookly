package com.example.bookly.repository

import com.example.bookly.api.model.Books
import com.example.bookly.api.model.Item

internal interface BookRepository {

    suspend fun getBooksByQuery(query: String): Books

    suspend fun getBookById(id: String): Item
}