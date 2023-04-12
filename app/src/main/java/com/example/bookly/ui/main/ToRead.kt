package com.example.bookly.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ToRead(
    viewModel: BookListViewModel
) {
    var title by remember { viewModel.title }
    var author by remember { viewModel.author }
    var genre by remember { viewModel.genre }
    var year by remember { viewModel.year }
    val isDialogVisible by remember { viewModel.isDialogVisible }

    if (isDialogVisible) AddBookDialog(
        title = title,
        onTitleChange = { title = it },
        author = author,
        onAuthorChange = { author = it },
        genre = genre,
        onGenreChange = { genre = it },
        year = year,
        onYearChange = { year = it },
        onDismiss = { viewModel.changeIsDialogVisible(false) },
        onSave = {
            viewModel.addBookToRead()
            viewModel.changeIsDialogVisible(false)
        }
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.changeIsDialogVisible(true) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AddBookDialog(
    title: String,
    onTitleChange: (String) -> Unit,
    author: String,
    onAuthorChange: (String) -> Unit,
    genre: String,
    onGenreChange: (String) -> Unit,
    year: String,
    onYearChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Add book")
                Spacer(modifier = Modifier.padding(10.dp))
                TextField(
                    value = title,
                    onValueChange = onTitleChange,
                    label = { Text(text = "Title") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                TextField(
                    value = author,
                    onValueChange = onAuthorChange,
                    label = { Text(text = "Author") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                TextField(
                    value = genre,
                    onValueChange = onGenreChange,
                    label = { Text(text = "Genre") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                TextField(
                    value = year,
                    onValueChange = onYearChange,
                    label = { Text(text = "Year") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.padding(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text(text = "Dismiss")
                    }
                    TextButton(onClick = onSave) {
                        Text(text = "Save")
                    }
                }

            }
        }
    }
}