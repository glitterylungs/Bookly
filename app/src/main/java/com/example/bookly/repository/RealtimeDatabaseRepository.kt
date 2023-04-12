package com.example.bookly.repository

import com.example.bookly.model.Book
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseError

internal interface RealtimeDatabaseRepository {

    fun addBookToRead(userId: String, book: Book): Task<Void>

    fun getBooksToRead(
        userId: String,
        callback: (List<Book>) -> Unit,
        errorCallback: (DatabaseError) -> Unit
    )
}