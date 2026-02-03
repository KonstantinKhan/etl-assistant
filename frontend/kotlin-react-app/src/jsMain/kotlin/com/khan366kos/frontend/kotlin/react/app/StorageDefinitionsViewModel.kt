package com.khan366kos.frontend.kotlin.react.app

import com.khan366kos.etl.assistant.transport.models.StorageDefinitionTransport
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface StorageDefinitionsUiState {
    data object Initial : StorageDefinitionsUiState
    data object Loading : StorageDefinitionsUiState
    data class Success(val storageDefinitions: List<StorageDefinitionTransport>) : StorageDefinitionsUiState
    data class Error(val message: String) : StorageDefinitionsUiState
}

class StorageDefinitionsViewModel {
    private val _state = MutableStateFlow<StorageDefinitionsUiState>(StorageDefinitionsUiState.Initial)
    val state: StateFlow<StorageDefinitionsUiState> = _state
    private val viewModelScope = MainScope()

    fun loadStorageDefinitions() {
        viewModelScope.launch {
            _state.value = StorageDefinitionsUiState.Loading
            try {
                val definitions = ApiClient.fetchStorageDefinitions()
                _state.value = StorageDefinitionsUiState.Success(definitions)
            } catch (e: Exception) {
                console.error("Failed to load storage definitions", e)
                _state.value = StorageDefinitionsUiState.Error(
                    e.message ?: "Неизвестная ошибка"
                )
            }
        }
    }

    fun cleanup() {
        viewModelScope.cancel()
    }
}