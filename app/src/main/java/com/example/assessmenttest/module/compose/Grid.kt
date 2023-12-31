package com.example.assessmenttest.module.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.assessmenttest.compose.NoItem
import com.example.assessmenttest.module.models.ImageModel

/**
 * This function generates a grid of images with adaptive column sizing and handles the case of no
 * images being present.
 */
@Composable
fun ImageGrid(images: List<ImageModel>, onImageClicked: (ImageModel) -> Unit) {
    Column {
        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 5.dp),
            columns = GridCells.Adaptive(minSize = 150.dp)
        ) {
            items(images) { photo ->
                ImageCard(photo, onImageClicked)
            }
        }
        NoItem(images.size)
    }
}