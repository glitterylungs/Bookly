package com.example.bookly.manager

internal interface SharedPreferencesManager {

    fun getBoolean(key: String): Boolean

    fun setBoolean(key: String, value: Boolean)
}