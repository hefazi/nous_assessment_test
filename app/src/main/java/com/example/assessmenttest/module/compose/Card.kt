package com.example.assessmenttest.module.compose


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.assessmenttest.R
import com.example.assessmenttest.module.models.ImageModel

/**
 * This is a Composable function called "ImageCard" that displays an image card with a clickable behavior.
 * It takes in an "image" parameter of type "ImageModel" which contains the data for the image to be
 * displayed. It also takes in an "onImageClicked" parameter, which is a callback function that will
 * be invoked when the image card is clicked.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageCard(image: ImageModel, onImageClicked: (ImageModel) -> Unit) {
    Card(
        modifier = Modifier
            .padding(vertical = 5.dp, horizontal = 4.dp)
            .fillMaxWidth(),
        onClick = { onImageClicked(image) }
    ) {
        Column {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 130.dp),
                painter = rememberAsyncImagePainter(
                    image.imageUrl,
                    error = painterResource(id = R.drawable.ic_launcher_foreground)
                ),
                contentDescription = image.description,
                contentScale = ContentScale.FillBounds,
            )
        }
    }
}