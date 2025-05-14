package com.realeyes.core.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import java.lang.reflect.Modifier

abstract class MultipleStatesViewModel<Action : ViewAction, UiState : ViewUIState, State : BusinessModelState, SideEffect : ViewSideEffect> :
    SingleStateViewModel<Action, UiState, SideEffect>() {
    abstract fun initializeState(): State
    abstract fun reduceToUi(state: State, uiState: UiState): UiState

    private val initialState: State by lazy { initializeState() }

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    @VisibleForTesting(otherwise = Modifier.PROTECTED)
    fun updateModelState(block: State.() -> State) {
        viewModelScope.launch {
            _state.update(block)
        }
    }

    protected fun updateModelStateAndReduceToUi(block: State.() -> State) {
        viewModelScope.launch {
            updateUiState {
                reduceToUi(_state.updateAndGet(block), uiState.value)
            }

        }
    }
}
