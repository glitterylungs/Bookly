package com.example.bookly.repository

import com.example.bookly.api.model.Item
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

internal interface RealtimeDatabaseRepository {

    fun addBook(userId: String, category: String, book: Item): Task<Void>

    fun deleteBookToRead(userId: String, bookId: String): Task<Void>

    fun checkIfBookToReadExists(
        userId: String,
        bookId: String,
        callback: (Boolean) -> Unit,
        errorCallback: (DatabaseError) -> Unit
    )

    fun getBooksToRead(
        userId: String,
        callback: (List<Item>) -> Unit,
        errorCallback: (DatabaseError) -> Unit
    )

    fun deleteBookAlreadyRead(userId: String, bookId: String): Task<Void>

    fun checkIfBookAlreadyReadExists(
        userId: String,
        bookId: String,
        callback: (Boolean) -> Unit,
        errorCallback: (DatabaseError) -> Unit
    )

    fun getBooksAlreadyRead(
        userId: String,
        callback: (List<Item>) -> Unit,
        errorCallback: (DatabaseError) -> Unit
    )
}

internal class RealtimeDatabaseRepositoryImpl(
    private val database: FirebaseDatabase,
) : RealtimeDatabaseRepository {

    override fun addBook(userId: String, category: String, book: Item) =
        database.reference.child("users").child(userId).child(category).child(book.id!!)
            .setValue(book)

    override fun deleteBookToRead(userId: String, bookId: String) =
        database.reference.child("users").child(userId).child("toRead").child(bookId).removeValue()

    override fun checkIfBookToReadExists(
        userId: String,
        bookId: String,
        callback: (Boolean) -> Unit,
        errorCallback: (DatabaseError) -> Unit
    ) {
        database.reference.child("users").child(userId).child("toRead").child(bookId)
            .addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        callback(snapshot.exists())
                    }

                    override fun onCancelled(error: DatabaseError) {
                        callback(false)
                    }
                }
            )
    }

    override fun getBooksToRead(
        userId: String,
        callback: (List<Item>) -> Unit,
        errorCallback: (DatabaseError) -> Unit
    ) {
        database.reference.child("users").child(userId).child("toRead")
            .addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val books: MutableList<Item> = mutableListOf()
                        snapshot.children.forEach {
                            it.getValue(Item::class.java)?.let { book ->
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

    override fun deleteBookAlreadyRead(userId: String, bookId: String) =
        database.reference.child("users").child(userId).child("alreadyRead").child(bookId)
            .removeValue()

    override fun checkIfBookAlreadyReadExists(
        userId: String,
        bookId: String,
        callback: (Boolean) -> Unit,
        errorCallback: (DatabaseError) -> Unit
    ) {
        database.reference.child("users").child(userId).child("alreadyRead").child(bookId)
            .addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        callback(snapshot.exists())
                    }

                    override fun onCancelled(error: DatabaseError) {
                        callback(false)
                    }
                }
            )
    }

    override fun getBooksAlreadyRead(
        userId: String,
        callback: (List<Item>) -> Unit,
        errorCallback: (DatabaseError) -> Unit
    ) {
        database.reference.child("users").child(userId).child("alreadyRead").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val books: MutableList<Item> = mutableListOf()
                    snapshot.children.forEach {
                        it.getValue(Item::class.java)?.let { book ->
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