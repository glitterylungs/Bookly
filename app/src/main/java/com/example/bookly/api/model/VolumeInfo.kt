package com.example.bookly.api.model

data class VolumeInfo(
    val authors: List<String?>? = null,
    val categories: List<String?>? = null,
    val description: String? = null,
    val imageLinks: ImageLinks? = null,
    val pageCount: Int? = null,
    val publishedDate: String? = null,
    val publisher: String? = null,
    val subtitle: String? = null,
    val title: String? = null
)