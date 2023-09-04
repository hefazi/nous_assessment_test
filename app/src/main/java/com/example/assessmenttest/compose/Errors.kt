package com.example.assessmenttest.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * The function is used to display an error message in a text format, which can be used in a Compose UI.
 * It is a reusable component that can be easily integrated into any Compose UI hierarchy.
 */
@Composable
fun ErrorText(message: String) {
    Text(
        message,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.error
    )
}