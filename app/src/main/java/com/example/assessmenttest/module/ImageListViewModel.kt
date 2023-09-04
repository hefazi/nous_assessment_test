package com.example.assessmenttest.module


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessmenttest.module.models.ImageModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed interface ImageUiState {
    data class Success(
        val data: List<ImageModel>
    ) : ImageUiState

    data class Error(
        var message: String
    ) : ImageUiState

    object Loading : ImageUiState
}

class ImageListViewModel : ViewModel() {
    private val repository: ImageRepository = ImageRepository()
    private val _uiState = MutableStateFlow<ImageUiState>(ImageUiState.Loading)

    val uiState: StateFlow<ImageUiState>
        get() = _uiState.asStateFlow()

    init {
        loadImages()
    }

    private fun loadImages() {
        // Launch a coroutine to load the images
        viewModelScope.launch {
            try {
                val response = repository.getImages()

                // Update the UI with the loaded images
                _uiState.value = ImageUiState.Success(response)
            } catch (e: Exception) {
                _uiState.value = ImageUiState.Error(message = e.message ?: "Error!")
            }
        }
    }

    fun filterImages(_query: String) {
        _uiState.value = ImageUiState.Success(repository.filterImages(_query))
    }


    fun findImageById(id: Long): ImageModel? {
        return repository.findImageById(id)
    }

//    fun onImageClicked(image: ImageModel) {
//        // Navigate to the details view
//
//    }
//
//    fun sendImageByEmail(image: ImageModel) {
//        // Send the image, title, and description via email
//    }
}