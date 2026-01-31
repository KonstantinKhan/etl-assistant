package com.khan366kos.frontend.kotlin.react.app

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WorkbookViewModel {
    private val _state: MutableStateFlow<List<String>> = MutableStateFlow(listOf("default"))
    val state: StateFlow<List<String>> = _state
    private val viewModelScope = MainScope()

    fun sheets() {
        viewModelScope.launch {
            _state.value = listOf("Лист1", "Лист2", "Лист3")
        }
    }

    fun cleanup() {
        viewModelScope.cancel()
    }
}