package com.example.bookly.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookly.api.model.Item
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

    var allBooks: MutableState<List<Item>> = mutableStateOf(emptyList())
        private set

    var searchText: MutableState<String> = mutableStateOf("")

    var booksToRead: MutableState<List<Item>> = mutableStateOf(emptyList())
        private set

    var booksToReadSearchText: MutableState<String> = mutableStateOf("")

    var booksAlreadyRead: MutableState<List<Item>> = mutableStateOf(emptyList())
        private set

    var booksAlreadyReadSearchText: MutableState<String> = mutableStateOf("")

    var errorMessage: MutableState<String?> = mutableStateOf(null)
        private set


    fun onSearchTextChange(text: String) {
        searchText.value = text
        getBooks(text)
    }

    fun onBooksToReadSearchTextChange(text: String) {
        booksToReadSearchText.value = text
        getBooksToReadByQuery(text)
    }

    fun onBooksAlreadyReadSearchTextChange(text: String) {
        booksAlreadyReadSearchText.value = text
        getBooksAlreadyReadByQuery(text)
    }

    fun getBooks(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (query != "") {
                allBooks.value =
                    bookRepository.getBooksByQuery(query).items?.filterNotNull() ?: emptyList()
            } else {
                allBooks.value =
                    bookRepository.getBooksByQuery("harry potter").items?.filterNotNull()
                        ?: emptyList()
            }
        }
    }

    fun getBooksToRead() {
        viewModelScope.launch(Dispatchers.IO) {
            authenticationRepository.getUserUid()?.let {
                realtimeDatabaseRepository.getBooksToRead(
                    userId = it,
                    callback = {
                        booksToRead.value = it
                        println(booksToRead.value)
                    },
                    errorCallback = { errorMessage.value = it.message }
                )
            }
        }
    }

    fun getBooksToReadByQuery(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            authenticationRepository.getUserUid()?.let {
                realtimeDatabaseRepository.getBooksToReadByQuery(
                    userId = it,
                    query = query,
                    callback = {
                        booksToRead.value = it
                        println(booksToRead.value)
                    },
                    errorCallback = { errorMessage.value = it.message }
                )
            }
        }
    }

    fun getBooksAlreadyRead() {
        viewModelScope.launch(Dispatchers.IO) {
            authenticationRepository.getUserUid()?.let {
                realtimeDatabaseRepository.getBooksAlreadyRead(
                    userId = it,
                    callback = {
                        booksAlreadyRead.value = it
                        println(booksAlreadyRead.value)
                    },
                    errorCallback = { errorMessage.value = it.message }
                )
            }
        }
    }

    fun getBooksAlreadyReadByQuery(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            authenticationRepository.getUserUid()?.let {
                realtimeDatabaseRepository.getBooksAlreadyReadByQuery(
                    userId = it,
                    query = query,
                    callback = {
                        booksAlreadyRead.value = it
                        println(booksAlreadyRead.value)
                    },
                    errorCallback = { errorMessage.value = it.message }
                )
            }
        }
    }
}