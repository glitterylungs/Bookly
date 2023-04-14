package com.example.bookly.manager

import android.content.Context

internal class SharedPreferencesManagerImpl(
    context: Context
) : SharedPreferencesManager {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun getBoolean(key: String): Boolean =
        sharedPreferences.getBoolean(key, DEFAULT_BOOLEAN_VALUE)

    override fun setBoolean(key: String, value: Boolean) =
        sharedPreferences.edit().putBoolean(key, value).apply()

    companion object {
        private const val SHARED_PREFERENCES_NAME = "shared_preferences"
        private const val DEFAULT_BOOLEAN_VALUE = false
    }
}