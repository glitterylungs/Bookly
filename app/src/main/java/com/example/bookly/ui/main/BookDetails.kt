package com.example.bookly.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BookDetails(
    viewModel: BookDetailsViewModel = koinViewModel(),
    bookId: String,
    navigateToPreviousScreen: () -> Unit
) {

    val book by remember { viewModel.book }
    val isBookToReadChecked by remember { viewModel.isBookToReadChecked }
    val isBookAlreadyReadChecked by remember { viewModel.isBookAlreadyReadChecked }

    LaunchedEffect(key1 = true) {
        viewModel.getBookById(bookId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = navigateToPreviousScreen) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Return")
                    }
                }
            )
        },
    ) {
        Column(modifier = Modifier.padding(it)) {
            book.volumeInfo?.title?.let { it1 -> Text(text = it1) }

            Row {
                Checkbox(
                    checked = isBookToReadChecked,
                    onCheckedChange = { viewModel.onBookToReadCheckedChange(it) })
                Text(text = "To read")
            }

            Row {
                Checkbox(
                    checked = isBookAlreadyReadChecked,
                    onCheckedChange = { viewModel.onBookAlreadyReadCheckedChange(it) })
                Text(text = "Already read")
            }

        }
    }
}