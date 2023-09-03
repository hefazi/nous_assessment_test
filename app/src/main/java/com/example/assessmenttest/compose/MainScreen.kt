package com.example.assessmenttest.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.assessmenttest.module.ImageListViewModel
import com.example.assessmenttest.module.ImageUiState
import com.example.assessmenttest.module.compose.ImageGrid
import com.example.assessmenttest.ui.theme.AssessmentTestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: ImageListViewModel) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    val uiState by viewModel.uiState.collectAsState()

    Column {
        TextField(
            value = text,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                text = it
                viewModel.filterImages(it.text)
            },
            placeholder = { Text("Search images...") }
        )
        when (uiState) {
            is ImageUiState.Success -> ImageGrid(images = (uiState as ImageUiState.Success).data)
            is ImageUiState.Error -> ErrorText(message = (uiState as ImageUiState.Error).message)
            ImageUiState.Loading -> IndeterminateCircularIndicator()
        }

    }
}


@Preview(showBackground = true)
@Composable
fun ImageListScreenPreview() {
    AssessmentTestTheme {
        MainScreen(viewModel = ImageListViewModel())
    }
}