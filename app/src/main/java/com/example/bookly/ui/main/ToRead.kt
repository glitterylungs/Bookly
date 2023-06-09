package com.example.bookly.ui.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import com.example.bookly.ui.common.BookCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ToRead(
    viewModel: BookListViewModel,
    navigateToBookDetails: (String) -> Unit
) {
    val booksToRead by remember { viewModel.booksToRead }
    val isLargeViewEnabled by remember { viewModel.isLargeViewEnabled }.collectAsState()

    LaunchedEffect(true) {
        viewModel.getBooksToRead()
        viewModel.updatePreferences()
    }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(if (isLargeViewEnabled) 1 else 2),
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp
    ) {
        items(booksToRead) { item ->
            if (item.volumeInfo?.imageLinks?.thumbnail != null) {
                BookCard(
                    book = item,
                    onClick = navigateToBookDetails,
                    isLargeViewEnabled = isLargeViewEnabled
                )
            }
        }
    }
}