package com.example.assessmenttest.module.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import com.example.assessmenttest.R
import com.example.assessmenttest.module.ImageListViewModel
import com.example.assessmenttest.module.models.ImageModel

@Composable
fun ImageDetailsView(
    viewModel: ImageListViewModel,
    id: Long?,
    onSendImageByEmail: (ImageModel?) -> Unit
) {

    val image = viewModel.findImageById(id ?: 0)


    Column {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = rememberAsyncImagePainter(
                image,
                error = painterResource(id = R.drawable.ic_launcher_foreground)
            ),
            contentDescription = image?.description,
            contentScale = ContentScale.FillBounds,
        )
        Text(text = image?.title ?: "-")
        Text(text = image?.description ?: "-")


        Button(
            onClick = { onSendImageByEmail(image) }
        ) {
            Text("Send image by email")
        }
    }
}