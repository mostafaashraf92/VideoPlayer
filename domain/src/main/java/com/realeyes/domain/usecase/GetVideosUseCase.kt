package com.realeyes.domain.usecase

import com.realeyes.domain.entities.VideoModel
import com.realeyes.domain.repository.VideosRepo
import com.realeyes.domain.entities.Output

class GetVideosUseCase(private val videosRepo: VideosRepo) :
    UseCase<VideoModel>() {

    suspend fun getAllVideos(): Output<VideoModel?> {
        return videosRepo.getAllVideos()
    }
}