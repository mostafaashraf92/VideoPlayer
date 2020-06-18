package com.realeyes.core.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.realeyes.domain.entities.ErrorModel
import com.realeyes.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineScope
import org.koin.core.KoinComponent


abstract class BaseViewModel<T>(useCase: UseCase<T>) : ViewModel(), KoinComponent {
    var responseLiveData: MutableLiveData<T> = MutableLiveData()
    var errorLiveData: MutableLiveData<ErrorModel> = MutableLiveData()
    var coroutineScope: CoroutineScope = viewModelScope
    protected abstract fun handleSuccess(response: T?)
    protected abstract fun handleError(errorModel: ErrorModel?)

}