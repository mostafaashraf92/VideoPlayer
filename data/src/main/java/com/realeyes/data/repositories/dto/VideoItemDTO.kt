package com.realeyes.data.repositories.dto

import kotlinx.serialization.Serializable

@Serializable
data class VideoItemDTO(
    var title: String?,
    var imageUri:String?,
    var subTitle: String?,
    var duration: Long?,
    var uri: String?
)
