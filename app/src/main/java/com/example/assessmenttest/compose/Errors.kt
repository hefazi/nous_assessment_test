package com.example.assessmenttest.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorText(message: String) {
    Text(
        message,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.error
    )
}