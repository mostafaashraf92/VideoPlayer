package com.realeyes.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class ErrorModel(var errorCode: String?, var errorMessage: String?)
