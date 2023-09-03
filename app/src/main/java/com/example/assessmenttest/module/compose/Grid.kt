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

@Composable
fun ImageGrid(images: List<ImageModel>) {
    Column {
        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 5.dp),
            columns = GridCells.Adaptive(minSize = 150.dp)
        ) {
            items(images) { photo ->
                ImageCard(photo)
            }
        }
        NoItem(images.size)
    }
}