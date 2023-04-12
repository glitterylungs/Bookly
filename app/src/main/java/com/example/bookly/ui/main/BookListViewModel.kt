package com.example.bookly.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookly.model.Book
import com.example.bookly.repository.AuthenticationRepository
import com.example.bookly.repository.BookRepository
import com.example.bookly.repository.RealtimeDatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class BookListViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val realtimeDatabaseRepository: RealtimeDatabaseRepository,
    private val bookRepository: BookRepository,
) : ViewModel() {

    var title: MutableState<String> = mutableStateOf("")
    var author: MutableState<String> = mutableStateOf("")
    var genre: MutableState<String> = mutableStateOf("")
    var year: MutableState<String> = mutableStateOf("")

    var books: MutableState<List<Book>> = mutableStateOf(emptyList())
        private set

    var isDialogVisible: MutableState<Boolean> = mutableStateOf(false)
        private set

    var isSuccessful: MutableState<Boolean> = mutableStateOf(false)
        private set

    var errorMessage: MutableState<String?> = mutableStateOf(null)
        private set

    fun changeIsDialogVisible(isVisible: Boolean) {
        isDialogVisible.value = isVisible
    }

    fun getBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            println(
                bookRepository.getBooksByQuery("flowers")
            )
        }
    }

    fun addBookToRead() {
        val book = Book(
            title = title.value,
            author = author.value,
            genre = genre.value,
            year = year.value.toInt()
        )

        viewModelScope.launch(Dispatchers.IO) {
            authenticationRepository.getUserUid()?.let {
                realtimeDatabaseRepository.addBookToRead(it, book)
                    .addOnSuccessListener {
                        isSuccessful.value = true
                    }
                    .addOnFailureListener { exception ->
                        errorMessage.value = exception.message
                        println(exception.message)
                    }
            } ?: run { errorMessage.value = "No such user" }

        }
    }

    fun getBooksToRead() {
        authenticationRepository.getUserUid()?.let {
            realtimeDatabaseRepository.getBooksToRead(
                userId = it,
                callback = {
                    books.value = it
                    println(books.value)
                },
                errorCallback = { errorMessage.value = it.message }
            )
        }
    }
}