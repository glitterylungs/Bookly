package com.example.bookly.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

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
                title = { Text(text = "Book Details") },
                navigationIcon = {
                    IconButton(onClick = navigateToPreviousScreen) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Return")
                    }
                }
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(
                    start = 16.dp,
                    top = it.calculateTopPadding(),
                    end = 16.dp,
                    bottom = it.calculateBottomPadding()
                )
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .align(Alignment.CenterHorizontally)
                    .shadow(3.dp, RoundedCornerShape(15.dp))
            ) {
                AsyncImage(
                    model = book.volumeInfo?.imageLinks?.thumbnail?.replace("http", "https"),
                    contentDescription = book.volumeInfo?.title,
                    modifier = Modifier.aspectRatio(2f / 3f),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            book.volumeInfo?.title?.let { title ->
                Text(text = title, style = MaterialTheme.typography.headlineMedium)
            }

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isBookToReadChecked,
                    onCheckedChange = { viewModel.onBookToReadCheckedChange(it) }
                )
                Text(
                    text = "To read",
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isBookAlreadyReadChecked,
                    onCheckedChange = { viewModel.onBookAlreadyReadCheckedChange(it) }
                )
                Text(
                    text = "Already read",
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            book.volumeInfo?.description?.let {
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(text = it, style = MaterialTheme.typography.titleMedium)
            }

            Spacer(modifier = Modifier.height(15.dp))

            book.volumeInfo?.authors?.let {
                Text(
                    text = "Authors",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = it.joinToString(separator = ", "),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            book.volumeInfo?.categories?.let {
                Text(
                    text = "Categories",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = it.joinToString(separator = ", "),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            book.volumeInfo?.pageCount?.let {
                Row() {
                    Text(
                        text = "Pages: ",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = it.toString(), style = MaterialTheme.typography.titleMedium)
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}