package com.realeyes.data.repositories

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.realeyes.domain.entities.ErrorModel
import com.realeyes.domain.entities.Output
import retrofit2.Response

open class BaseRepository {
    suspend fun <T : Any?> safeApiCall(call: suspend () -> Response<T>): Output<T?> {
        return apiCallOutput(call)
    }

    private suspend fun <T : Any?> apiCallOutput(
        call: suspend () -> Response<T>
    ): Output<T> {
        var response: Response<T>? = null
        var output: Output<T>? = null

        runCatching {
            response = call.invoke()
        }
            .onSuccess {
                if (response?.isSuccessful!!)
                    output = Output.Success(response?.body())
                else
                    output = Output.Error(constructErrorModel(response))
            }
            .onFailure {
                output = Output.Error(constructErrorModel<T>(null))
            }
        return output!!
    }

    private fun <T> constructErrorModel(response: Response<T>?): ErrorModel {
        return if (response?.errorBody() != null) {
            val type = object : TypeToken<ErrorModel>() {}.type
            (Gson().fromJson(response.errorBody()!!.charStream(), type))
        } else {
            (ErrorModel("500", ""))
        }
    }
}