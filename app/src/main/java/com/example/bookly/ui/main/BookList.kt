package com.example.bookly.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
internal fun BookList(
    viewModel: BookListViewModel = koinViewModel(),
    navigateToBookDetails: (String) -> Unit
) {
    var tabIndex by remember { mutableStateOf(0) }
    val searchText by remember { viewModel.searchText }
    val booksToReadSearchText by remember { viewModel.booksToReadSearchText }
    val booksAlreadyReadSearchText by remember { viewModel.booksAlreadyReadSearchText }

    var isFocused by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val tabs = listOf("ALL BOOKS", "TO READ", "ALREADY READ")

    if (!isFocused) {
        LaunchedEffect(keyboardController) {
            keyboardController?.hide()
        }
    }

    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            TextField(
                value = when (tabIndex) {
                    0 -> searchText
                    1 -> booksToReadSearchText
                    2 -> booksAlreadyReadSearchText
                    else -> searchText
                },
                onValueChange = {
                    when (tabIndex) {
                        0 -> viewModel.onSearchTextChange(it)
                        1 -> viewModel.onBooksToReadSearchTextChange(it)
                        2 -> viewModel.onBooksAlreadyReadSearchTextChange(it)
                        else -> viewModel.onSearchTextChange(it)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 20.dp)
                    .shadow(3.dp, shape = CircleShape)
                    .onFocusChanged { isFocused = it.isFocused },
                label = { Text(text = "Search") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
            )

            TabRow(selectedTabIndex = tabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
            when (tabIndex) {
                0 -> All(viewModel, navigateToBookDetails)
                1 -> ToRead(viewModel, navigateToBookDetails)
                2 -> AlreadyRead(viewModel, navigateToBookDetails)
            }
        }
    }
}