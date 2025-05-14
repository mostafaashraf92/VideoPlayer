package com.realeyes.domain.entities


sealed class Output<out T : Any?> {
    data class Success<out T : Any?>(val output: T?) : Output<T>()
    data object Loading : Output<Nothing>()
    data class Error(val exception: ErrorModel) : Output<Nothing>()
}