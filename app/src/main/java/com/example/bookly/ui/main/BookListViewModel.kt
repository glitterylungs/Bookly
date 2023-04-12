package com.example.bookly.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BookListViewModel : ViewModel() {

    var title: MutableState<String> = mutableStateOf("")
    var author: MutableState<String> = mutableStateOf("")
    var genre: MutableState<String> = mutableStateOf("")
    var year: MutableState<String> = mutableStateOf("")

    var isDialogVisible: MutableState<Boolean> = mutableStateOf(false)
        private set

    fun changeIsDialogVisible(isVisible: Boolean) {
        isDialogVisible.value = isVisible
    }

    fun addBookToRead() {

    }
}