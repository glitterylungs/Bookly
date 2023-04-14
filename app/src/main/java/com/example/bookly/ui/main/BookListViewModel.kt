package com.example.bookly.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookly.api.model.Item
import com.example.bookly.manager.SharedPreferencesManager
import com.example.bookly.repository.AuthenticationRepository
import com.example.bookly.repository.BookRepository
import com.example.bookly.repository.RealtimeDatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class BookListViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val realtimeDatabaseRepository: RealtimeDatabaseRepository,
    private val bookRepository: BookRepository,
    private val sharedPreferencesManager: SharedPreferencesManager,
) : ViewModel() {

    var allBooks: MutableState<List<Item>> = mutableStateOf(emptyList())
        private set

    var searchText: MutableState<String> = mutableStateOf("")

    var booksToRead: MutableState<List<Item>> = mutableStateOf(emptyList())
        private set

    var booksAlreadyRead: MutableState<List<Item>> = mutableStateOf(emptyList())
        private set

    val isLargeViewEnabled = MutableStateFlow(sharedPreferencesManager.getBoolean("accessibility"))

    var errorMessage: MutableState<String?> = mutableStateOf(null)
        private set

    fun updatePreferences() {
        isLargeViewEnabled.value = sharedPreferencesManager.getBoolean("accessibility")
    }

    fun onSearchTextChange(text: String) {
        searchText.value = text
        getBooks(text)
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
}