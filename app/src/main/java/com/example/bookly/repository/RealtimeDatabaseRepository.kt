package com.example.bookly.repository

import com.example.bookly.api.model.Item
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseError

internal interface RealtimeDatabaseRepository {

    fun addBookToRead(userId: String, book: Item): Task<Void>

    fun deleteBookToRead(userId: String, bookId: String): Task<Void>

    fun getBooksToRead(
        userId: String,
        callback: (List<Item>) -> Unit,
        errorCallback: (DatabaseError) -> Unit
    )

    fun getBooksToReadByQuery(
        userId: String,
        query: String,
        callback: (List<Item>) -> Unit,
        errorCallback: (DatabaseError) -> Unit
    )

    fun addBookAlreadyRead(userId: String, book: Item): Task<Void>

    fun deleteBookAlreadyRead(userId: String, bookId: String): Task<Void>

    fun getBooksAlreadyRead(
        userId: String,
        callback: (List<Item>) -> Unit,
        errorCallback: (DatabaseError) -> Unit
    )

    fun getBooksAlreadyReadByQuery(
        userId: String,
        query: String,
        callback: (List<Item>) -> Unit,
        errorCallback: (DatabaseError) -> Unit
    )
}