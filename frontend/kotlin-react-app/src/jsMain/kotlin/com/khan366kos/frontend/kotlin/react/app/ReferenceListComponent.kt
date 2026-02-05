package com.khan366kos.frontend.kotlin.react.app

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.khan366kos.common.models.business.Reference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface ReferenceListUiState {
    data object Initial : ReferenceListUiState
    data object Loading : ReferenceListUiState
    data class Success(val references: List<Reference>) : ReferenceListUiState
    data class Error(val message: String) : ReferenceListUiState
}

class ReferenceListComponent(
    componentContext: ComponentContext,
    private val apiClient: ApiClient
) : ComponentContext by componentContext {

    private val scope = coroutineScope(Dispatchers.Main + SupervisorJob())

    private val _state = MutableStateFlow<ReferenceListUiState>(ReferenceListUiState.Initial)
    val state: StateFlow<ReferenceListUiState> = _state

    init {
        loadReferences()
    }

    fun loadReferences() {
        scope.launch {
            _state.value = ReferenceListUiState.Loading
            try {
                val references = apiClient.fetchReferences()
                _state.value = ReferenceListUiState.Success(references)
            } catch (e: Exception) {
                _state.value = ReferenceListUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}