package com.realeyes.data.repositories.dto

import kotlinx.serialization.Serializable

@Serializable
data class VideoDTO(var videos: MutableList<VideoItemDTO>?)
