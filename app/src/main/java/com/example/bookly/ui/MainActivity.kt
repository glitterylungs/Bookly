package com.example.bookly.ui

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.bookly.ui.navigation.NavigationHost
import com.example.bookly.ui.theme.BooklyTheme
import org.koin.android.ext.android.inject

internal class MainActivity : ComponentActivity() {

    private val viewModel by inject<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BooklyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationHost(
                        isLoggedIn = viewModel.isLoggedIn.value
                    )
                }
            }
        }
    }
}