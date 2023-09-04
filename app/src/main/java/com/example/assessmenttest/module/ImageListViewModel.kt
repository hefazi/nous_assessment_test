package com.example.assessmenttest.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessmenttest.module.models.ImageModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


/**
 * ViewModel class for managing the state and data related to the list of images.
 */
class ImageListViewModel : ViewModel() {
    private val repository: ImageRepository = ImageRepository()
    private val _uiState = MutableStateFlow<ImageUiState>(ImageUiState.Loading)

    val uiState: StateFlow<ImageUiState>
        get() = _uiState.asStateFlow()

    init {
        loadImages()
    }

    /**
     * Loads the images from the repository and updates the UI state accordingly.
     */
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

    /**
     * Filters the images based on the given query and updates the UI state.
     */
    fun filterImages(query: String) {
        _uiState.value = ImageUiState.Success(repository.filterImages(query))
    }

    /**
     * Finds an image by its ID from the repository.
     */
    fun findImageById(id: Long): ImageModel? {
        return repository.findImageById(id)
    }
}

/**
 * Sealed interface representing the different states of the UI related to the list of images.
 */
sealed interface ImageUiState {

    /**
     * Represents the success state with a list of image data.
     */
    data class Success(
        val data: List<ImageModel>
    ) : ImageUiState

    /**
     * Represents the error state with an error message.
     */
    data class Error(
        var message: String
    ) : ImageUiState

    /**
     * Represents the loading state.
     */
    object Loading : ImageUiState
}