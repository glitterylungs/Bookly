package com.example.bookly.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookly.api.model.ImageLinks
import com.example.bookly.api.model.Item
import com.example.bookly.api.model.VolumeInfo
import com.example.bookly.repository.AuthenticationRepository
import com.example.bookly.repository.BookRepository
import com.example.bookly.repository.RealtimeDatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class BookDetailsViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val bookRepository: BookRepository,
    private val realtimeDatabaseRepository: RealtimeDatabaseRepository
) : ViewModel() {

    var book: MutableState<Item> = mutableStateOf(
        Item(
            id = "",
            volumeInfo = VolumeInfo(
                authors = emptyList(),
                categories = emptyList(),
                description = "",
                imageLinks = ImageLinks(
                    smallThumbnail = "",
                    thumbnail = ""
                ),
                pageCount = 0,
                publishedDate = "",
                publisher = "",
                subtitle = "",
                title = ""
            )
        )
    )
        private set

    var isBookToReadChecked: MutableState<Boolean> = mutableStateOf(false)
        private set

    var isBookAlreadyReadChecked: MutableState<Boolean> = mutableStateOf(false)
        private set

    var isSuccessful: MutableState<Boolean> = mutableStateOf(false)
        private set

    var errorMessage: MutableState<String?> = mutableStateOf(null)
        private set

    fun onBookToReadCheckedChange(isChecked: Boolean) {
        isBookToReadChecked.value = isChecked

        if (isChecked) {
            addBookToRead()
        } else {
            deleteBookToRead()
        }
    }

    fun onBookAlreadyReadCheckedChange(isChecked: Boolean) {
        isBookAlreadyReadChecked.value = isChecked

        if (isChecked) {
            addBookAlreadyRead()
        } else {
            deleteBookAlreadyRead()
        }
    }

    fun getBookById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            book.value = bookRepository.getBookById(id)
            checkIfBookToReadExists(id)
            checkIfBookAlreadyReadExists(id)
        }
    }

    private fun addBookToRead() {
        viewModelScope.launch(Dispatchers.IO) {
            authenticationRepository.getUserUid()?.let {
                realtimeDatabaseRepository.addBookToRead(it, book.value)
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

    private fun deleteBookToRead() {
        viewModelScope.launch(Dispatchers.IO) {
            authenticationRepository.getUserUid()?.let {
                realtimeDatabaseRepository.deleteBookToRead(it, book.value.id!!)
                    .addOnSuccessListener {
                        isSuccessful.value = true
                    }
                    .addOnFailureListener { exception ->
                        errorMessage.value = exception.message
                    }
            } ?: run { errorMessage.value = "No such user" }
        }
    }

    private fun checkIfBookToReadExists(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            authenticationRepository.getUserUid()?.let {
                realtimeDatabaseRepository.checkIfBookToReadExists(
                    userId = it,
                    bookId = id,
                    callback = {
                        isBookToReadChecked.value = it
                    },
                    errorCallback = {
                        errorMessage.value = it.message
                    }
                )
            }
        }
    }

    private fun addBookAlreadyRead() {
        viewModelScope.launch(Dispatchers.IO) {
            authenticationRepository.getUserUid()?.let {
                realtimeDatabaseRepository.addBookAlreadyRead(it, book.value)
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

    private fun deleteBookAlreadyRead() {
        viewModelScope.launch(Dispatchers.IO) {
            authenticationRepository.getUserUid()?.let {
                realtimeDatabaseRepository.deleteBookAlreadyRead(it, book.value.id!!)
                    .addOnSuccessListener {
                        isSuccessful.value = true
                    }
                    .addOnFailureListener { exception ->
                        errorMessage.value = exception.message
                    }
            } ?: run { errorMessage.value = "No such user" }
        }
    }

    private fun checkIfBookAlreadyReadExists(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            authenticationRepository.getUserUid()?.let {
                realtimeDatabaseRepository.checkIfBookAlreadyReadExists(
                    userId = it,
                    bookId = id,
                    callback = {
                        isBookAlreadyReadChecked.value = it
                    },
                    errorCallback = {
                        errorMessage.value = it.message
                    }
                )
            }
        }
    }
}