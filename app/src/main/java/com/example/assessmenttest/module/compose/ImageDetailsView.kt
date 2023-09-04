package com.example.assessmenttest.module.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.assessmenttest.R
import com.example.assessmenttest.compose.NoFind
import com.example.assessmenttest.module.ImageListViewModel
import com.example.assessmenttest.module.models.ImageModel

/**
 * This composable displays the details of an image, including the image itself, title, description,
 * and a button to share the image by email.
 */
@Composable
fun ImageDetailsView(
    viewModel: ImageListViewModel,
    id: Long?,
    onSendImageByEmail: (ImageModel?) -> Unit
) {

    val image = viewModel.findImageById(id ?: 0)

    if (image == null) {
        NoFind()
    } else {
        Column {
            Image(
                rememberAsyncImagePainter(
                    image.imageUrl,
                    error = painterResource(id = R.drawable.ic_launcher_foreground)
                ),
                image.description,
                Modifier
                    .fillMaxWidth()
                    .height(height = 230.dp),
                contentScale = ContentScale.FillBounds,
            )
            Column(Modifier.padding(horizontal = 15.dp, vertical = 5.dp)) {
                Text(
                    text = image.title,
                    Modifier.padding(vertical = 15.dp),
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = image.description,
                    style = MaterialTheme.typography.bodyLarge
                )
            }


            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 55.dp, horizontal = 15.dp),
                onClick = { onSendImageByEmail(image) }
            ) {
                Text("Share by email")
            }
        }
    }
}