package com.example.bookly.api.service

import com.example.bookly.api.model.Books
import com.example.bookly.api.model.Item
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface BookService {

    @GET("volumes")
    suspend fun getBooksByQuery(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int,
        @Query("key") apiKey: String
    ): Books

    @GET("volumes/{id}")
    suspend fun getBookById(
        @Path("id") id: String
    ): Item
}