package com.realeyes.core.viewmodel

import androidx.lifecycle.viewModelScope
import com.realeyes.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineScope
import org.koin.core.component.KoinComponent


abstract class BaseUseCaseViewModel<T, Action : ViewAction, UiState : ViewUIState, ModelState: BusinessModelState, SideEffect : ViewSideEffect>(
    useCase: UseCase<T>
) : MultipleStatesViewModel<Action, UiState, ModelState ,SideEffect>(), KoinComponent {
    var coroutineScope: CoroutineScope = viewModelScope
}