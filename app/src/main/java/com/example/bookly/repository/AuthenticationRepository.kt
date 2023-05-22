package com.example.bookly.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

internal interface AuthenticationRepository {

    fun checkIfSignedIn(): Boolean

    fun getUserUid(): String?

    fun signUp(email: String, password: String): Task<AuthResult>

    fun signIn(email: String, password: String): Task<AuthResult>

    fun signOut()
}

internal class AuthenticationRepositoryImpl(
    private val auth: FirebaseAuth
) : AuthenticationRepository {

    override fun getUserUid() = auth.currentUser?.uid

    override fun checkIfSignedIn() = auth.currentUser != null

    override fun signUp(email: String, password: String): Task<AuthResult> =
        auth.createUserWithEmailAndPassword(email, password)

    override fun signIn(email: String, password: String): Task<AuthResult> =
        auth.signInWithEmailAndPassword(email, password)

    override fun signOut() {
        auth.signOut()
    }
}