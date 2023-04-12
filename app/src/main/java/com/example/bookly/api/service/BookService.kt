package com.example.bookly.api.service

import com.example.bookly.api.model.Books
import retrofit2.http.GET
import retrofit2.http.Query

internal interface BookService {

    @GET("volumes")
    suspend fun getBooksByQuery(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int,
        @Query("key") apiKey: String
    ): Result<Books>
}