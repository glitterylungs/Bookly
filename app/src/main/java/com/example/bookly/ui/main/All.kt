package com.example.bookly.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.example.bookly.ui.common.BookCard

@Composable
internal fun All(
    viewModel: BookListViewModel,
    navigateToBookDetails: (String) -> Unit
) {
    val allBooks by remember { viewModel.allBooks }

    LaunchedEffect(key1 = true) {
        viewModel.getBooks("search")
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
    ) {
        items(allBooks) { item ->
            BookCard(book = item, onClick = navigateToBookDetails)
        }
    }
}