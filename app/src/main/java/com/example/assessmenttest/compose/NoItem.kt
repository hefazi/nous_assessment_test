package com.example.assessmenttest.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.assessmenttest.ui.theme.AssessmentTestTheme

/**
 * Inside the Column, there is a Text composable that displays the text "No Item!" with a large title style
 * and the primary color from the MaterialTheme.
 */
@Composable
fun NoItem(size: Int = 0) {
    if (size > 0) return
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "No Item!",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NoItemReview() {
    AssessmentTestTheme {
        NoItem()
    }
}