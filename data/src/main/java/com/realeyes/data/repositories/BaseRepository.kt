package com.realeyes.data.repositories

import com.realeyes.domain.entities.ErrorModel
import com.realeyes.domain.entities.Output
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.serialization.json.Json

open class BaseRepository {
    inline fun <reified T : Any?, R : Any?> safeApiCall(
        crossinline call: suspend () -> HttpResponse,
        crossinline mapper: (T) -> R
    ): Flow<Output<R?>> {
        return apiCallOutput(call, mapper)
    }

    inline fun <reified T : Any?, R : Any?> apiCallOutput(
        crossinline call: suspend () -> HttpResponse,
        crossinline mapper: (T) -> R
    ): Flow<Output<R>> = flow {
        var response: HttpResponse? = null
        runCatching {
            response = call.invoke()
        }
            .onSuccess {
                when (response?.status?.value) {
                    in 200..299 -> {
                        val body = response?.body() as T
                        emit(Output.Success(body?.let { mapper(it) }))
                    }

                    else -> {
                        val errorBody = response?.bodyAsText()
                        emit(Output.Error(constructErrorModel(errorBody)))
                    }
                }
            }
            .onFailure {
                emit(Output.Error(constructErrorModel(null)))
            }
    }.onStart {
        emit(Output.Loading)
    }

    fun constructErrorModel(response: String?): ErrorModel {
        return if (!response.isNullOrEmpty()) {
            val errorModel = Json.decodeFromString<ErrorModel>(response)
            return errorModel
        } else {
            (ErrorModel("500", ""))
        }
    }
}