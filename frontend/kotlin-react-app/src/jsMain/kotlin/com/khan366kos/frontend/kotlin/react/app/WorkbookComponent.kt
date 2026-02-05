package com.khan366kos.frontend.kotlin.react.app

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.khan366kos.etl.assistant.transport.models.EtlWorkbookTransport
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
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

class WorkbookComponent(
    componentContext: ComponentContext,
    private val apiClient: ApiClient
) : ComponentContext by componentContext {
    private val scope = coroutineScope(Dispatchers.Main + SupervisorJob())

    private val _state = MutableStateFlow<WorkbookUiState>(WorkbookUiState.Initial)

    val state: StateFlow<WorkbookUiState> = _state

    fun loadSheets() {
        scope.launch {
            _state.value = WorkbookUiState.Loading
            try {
                val workbook = apiClient.fetchSheets()
                _state.value = WorkbookUiState.Success(workbook)
            } catch (e: Exception) {
                _state.value = WorkbookUiState.Error(
                    e.message ?: "Error while fetching sheets."
                )
            }
        }
    }

    fun uploadFile(file: BrowserFile) {
        scope.launch {
            _state.value = WorkbookUiState.Loading
            try {
                val workbook = apiClient.uploadFile(file)
                _state.value = WorkbookUiState.Success(workbook)
            } catch (e: Exception) {
                console.error("Failed to upload file", e)
                _state.value = WorkbookUiState.Error(
                    e.message ?: "Ошибка загрузки файла"
                )
            }
        }
    }
}