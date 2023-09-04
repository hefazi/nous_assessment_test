package com.example.assessmenttest.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.assessmenttest.ui.theme.AssessmentTestTheme

/**
 * The function is responsible for displaying a message when an item is not found.
 * It is wrapped inside a theme called "AssessmentTestTheme".
 */
@Composable
fun NoFind() {
    AssessmentTestTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Item not find!",
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}
