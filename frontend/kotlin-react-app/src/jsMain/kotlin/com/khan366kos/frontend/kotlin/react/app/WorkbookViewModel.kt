package com.khan366kos.frontend.kotlin.react.app

import com.khan366kos.etl.assistant.transport.models.EtlWorkbookTransport
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import web.file.File as BrowserFile

sealed interface WorkbookUiState {
    data object Initial : WorkbookUiState
    data object Loading : WorkbookUiState
    data class Success(val workbook: EtlWorkbookTransport) : WorkbookUiState
    data class Error(val message: String) : WorkbookUiState
}

class WorkbookViewModel {
    private val _state = MutableStateFlow<WorkbookUiState>(WorkbookUiState.Initial)
    val state: StateFlow<WorkbookUiState> = _state
    private val viewModelScope = MainScope()

    fun loadSheets() {
        viewModelScope.launch {
            _state.value = WorkbookUiState.Loading
            try {
                val workbook = ApiClient.fetchSheets()
                _state.value = WorkbookUiState.Success(workbook)
            } catch (e: Exception) {
                console.error("Failed to load sheets", e)
                _state.value = WorkbookUiState.Error(
                    e.message ?: "Неизвестная ошибка"
                )
            }
        }
    }

    fun uploadFile(file: BrowserFile) {
        viewModelScope.launch {
            _state.value = WorkbookUiState.Loading
            try {
                val workbook = ApiClient.uploadFile(file)
                _state.value = WorkbookUiState.Success(workbook)
            } catch (e: Exception) {
                console.error("Failed to upload file", e)
                _state.value = WorkbookUiState.Error(
                    e.message ?: "Ошибка загрузки файла"
                )
            }
        }
    }

    fun cleanup() {
        viewModelScope.cancel()
    }
}
