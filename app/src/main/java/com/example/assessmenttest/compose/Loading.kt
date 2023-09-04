package com.example.assessmenttest.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Displays a circular progress indicator using the Material3 design system.
 * The indicator is centered on the screen and has a width of 64dp. The color of the indicator is set to
 * the surface variant of the MaterialTheme color scheme. This function can be used to display a
 * loading animation while data is being fetched or processed.
 */
@Composable
fun IndeterminateCircularIndicator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(64.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}