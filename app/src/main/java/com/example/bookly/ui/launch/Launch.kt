package com.example.bookly.ui.launch

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bookly.R
import kotlinx.coroutines.delay

@Composable
internal fun Launch(
    navigateToNextScreen: () -> Unit
) {

    LaunchedEffect(key1 = true) {
        delay(1000)
        navigateToNextScreen()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_book),
            contentDescription = "Bookly",
            modifier = Modifier
                .padding(bottom = 30.dp)
        )

        Text(
            text = "Bookly",
            modifier = Modifier.padding(bottom = 50.dp),
            style = MaterialTheme.typography.displayMedium
        )
    }
}