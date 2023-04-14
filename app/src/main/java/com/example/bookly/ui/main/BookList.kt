package com.example.bookly.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
internal fun BookList(
    viewModel: BookListViewModel = koinViewModel(),
    navigateToBookDetails: (String) -> Unit,
    navigateToSetting: () -> Unit
) {
    var tabIndex by remember { mutableStateOf(0) }
    var searchText by remember { viewModel.searchText }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val tabs = listOf("ALL BOOKS", "TO READ", "ALREADY READ")

    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = searchText,
                    onValueChange = {
                        tabIndex = 0
                        viewModel.onSearchTextChange(it)
                    },
                    modifier = Modifier
                        .padding(vertical = 12.dp, horizontal = 20.dp)
                        .shadow(3.dp, shape = CircleShape),
                    label = { Text(text = "Search") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        })
                )
                IconButton(onClick = navigateToSetting) {
                    Icon(imageVector = Icons.Outlined.Settings, contentDescription = "Settings")
                }
            }

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
                1 -> {
                    searchText = ""
                    ToRead(viewModel, navigateToBookDetails)
                }
                2 -> {
                    searchText = ""
                    AlreadyRead(viewModel, navigateToBookDetails)
                }
            }
        }
    }
}