package com.example.bookly.model

internal data class Book(
    //pytanie czy trzeba id
    val title: String,
    val author: String,
    val genre: String,
    val year: Int
)
