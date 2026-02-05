package com.khan366kos.frontend.kotlin.react.app

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.khan366kos.etl.assistant.transport.models.AuthorizationRequestTransport
import com.khan366kos.etl.assistant.transport.models.StorageDefinitionTransport
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface AuthorizationUiState {
    data object Initial : AuthorizationUiState
    data object Loading : AuthorizationUiState
    data class Success(val message: String) : AuthorizationUiState
    data class Error(val message: String) : AuthorizationUiState
}

sealed interface StorageLoadingState {
    data object Initial : StorageLoadingState
    data object Loading : StorageLoadingState
    data class Success(val storageDefinitions: List<StorageDefinitionTransport>) : StorageLoadingState
    data class Error(val message: String) : StorageLoadingState
}

class AuthComponent(
    componentContext: ComponentContext,
    private val apiClient: ApiClient,
    private val onAuthSuccess: () -> Unit
) : ComponentContext by componentContext {

    private val scope = coroutineScope(Dispatchers.Main + SupervisorJob())

    private val _authState = MutableStateFlow<AuthorizationUiState>(AuthorizationUiState.Initial)
    val authState: StateFlow<AuthorizationUiState> = _authState

    private val _storageState = MutableStateFlow<StorageLoadingState>(StorageLoadingState.Initial)
    val storageState: StateFlow<StorageLoadingState> = _storageState

    init {
        loadStorageDefinitions()
    }

    fun loadStorageDefinitions() {
        scope.launch {
            _storageState.value = StorageLoadingState.Loading
            try {
                val definitions = apiClient.fetchStorageDefinitions()
                _storageState.value = StorageLoadingState.Success(definitions)
            } catch (e: Exception) {
                console.error("Failed to load storage definitions", e)
                _storageState.value = StorageLoadingState.Error(
                    e.message ?: "Неизвестная ошибка"
                )
            }
        }
    }

    fun submitAuthorization(username: String, password: String, storageId: String) {
        scope.launch {
            _authState.value = AuthorizationUiState.Loading
            try {
                val request = AuthorizationRequestTransport(
                    username = username,
                    password = password,
                    storageId = storageId
                )
                val response = apiClient.authorize(request)
                _authState.value = AuthorizationUiState.Success(response)
                // Navigate to Workbook screen on success
                onAuthSuccess()
            } catch (e: Exception) {
                console.error("Authorization failed", e)
                _authState.value = AuthorizationUiState.Error(
                    e.message ?: "Ошибка авторизации"
                )
            }
        }
    }
}