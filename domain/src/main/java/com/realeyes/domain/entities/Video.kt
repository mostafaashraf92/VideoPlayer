package com.realeyes.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class Video(var videos: MutableList<VideoItem>?)
