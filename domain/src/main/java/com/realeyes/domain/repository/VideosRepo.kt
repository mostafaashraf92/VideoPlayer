package com.realeyes.domain.repository

import com.realeyes.domain.entities.Video
import com.realeyes.domain.entities.Output
import kotlinx.coroutines.flow.Flow

interface VideosRepo {

    suspend fun getAllVideos(): Flow<Output<Video?>>
}