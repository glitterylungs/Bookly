package com.example.bookly.repository

import com.example.bookly.model.Book
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

internal class RealtimeDatabaseRepositoryImpl(
    private val database: FirebaseDatabase,
) : RealtimeDatabaseRepository {

    override fun addBookToRead(userId: String, book: Book) =
        database.reference.child("users").child(userId).child("toRead").push().setValue(book)

    override fun getBooksToRead(
        userId: String,
        callback: (List<Book>) -> Unit,
        errorCallback: (DatabaseError) -> Unit
    ) {
        database.reference.child("users").child(userId).child("toRead").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val books: MutableList<Book> = mutableListOf()
                    snapshot.children.forEach {
                        it.getValue(Book::class.java)?.let { book ->
                            books.add(book)
                        }
                    }
                    callback(books)
                }

                override fun onCancelled(error: DatabaseError) {
                    errorCallback(error)
                }
            }
        )
    }
}