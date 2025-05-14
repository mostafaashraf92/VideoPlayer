package com.realeyes.data.repositories

import com.realeyes.domain.entities.Output
import com.realeyes.domain.entities.Video
import com.realeyes.domain.repository.VideosRepo
import kotlinx.coroutines.flow.Flow

class VideosRepoImp(var videosDataSourceImp: VideosDataSourceImp) : BaseRepository(),
    VideosRepo {

    override suspend fun getAllVideos(): Flow<Output<Video?>> {
        return videosDataSourceImp.getAllVideos()
    }

}