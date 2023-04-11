package com.example.bookly.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

internal interface AuthenticationRepository {

    fun checkIfSignedIn(): Boolean

    fun signUp(email: String, password: String): Task<AuthResult>

    fun signIn(email: String, password: String): Task<AuthResult>
}