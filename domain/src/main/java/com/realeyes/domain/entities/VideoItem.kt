package com.realeyes.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class VideoItem(
    var title: String?,
    var imageUri: String?,
    var subTitle: String?,
    var duration: Long?,
    var uri: String?
)
