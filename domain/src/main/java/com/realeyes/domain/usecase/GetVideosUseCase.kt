package com.realeyes.domain.usecase

import com.realeyes.domain.entities.Video
import com.realeyes.domain.repository.VideosRepo
import com.realeyes.domain.entities.Output
import kotlinx.coroutines.flow.Flow

class GetVideosUseCase(private val videosRepo: VideosRepo) :
    UseCase<Video>() {

    suspend fun getAllVideos(): Flow<Output<Video?>> {
        return videosRepo.getAllVideos()
    }
}