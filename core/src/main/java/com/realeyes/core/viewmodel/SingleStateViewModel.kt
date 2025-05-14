package com.realeyes.core.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class SingleStateViewModel<Action : ViewAction, UiState : ViewUIState, SideEffect : ViewSideEffect> :
    BaseViewModel<Action, SideEffect>() {
    protected abstract fun initializeUiState(): UiState

    private val initialUiState: UiState by lazy { initializeUiState() }

    private val _uiState = MutableStateFlow(initialUiState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    protected fun updateUiState(block: UiState.() -> UiState) {
        viewModelScope.launch {
            _uiState.update(block)
        }
    }
}
