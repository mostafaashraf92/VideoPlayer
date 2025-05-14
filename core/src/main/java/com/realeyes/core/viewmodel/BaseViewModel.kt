package com.realeyes.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<Action : ViewAction, SideEffect : ViewSideEffect> :
    ViewModel() {
    abstract fun onUiAction(uiAction: Action)
    private val _effect: Channel<SideEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    protected fun sendEffect(effect: SideEffect) {
        viewModelScope.launch { _effect.send(effect) }
    }

    protected fun launchIO(block: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            block.invoke()
        }
    }

    protected fun launchMain(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            block.invoke(this)
        }
    }
}

interface ViewAction

interface ViewUIState

interface BusinessModelState

interface ViewSideEffect
