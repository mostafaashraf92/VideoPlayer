package com.realeyes.domain.entities

import java.io.Serializable

data class VideoItemModel(
    var title: String?,
    var imageUri:String?,
    var subTitle: String?,
    var duration: Long?,
    var uri: String?
) : Serializable
