package com.realeyes.feature.videos_list.data

import com.realeyes.data.repositories.BaseRepository
import com.realeyes.domain.entities.Output
import com.realeyes.domain.entities.VideoModel
import com.realeyes.domain.repository.VideosRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class VideosRepoImp(var videosDataSourceImp: VideosDataSourceImp) : BaseRepository(),
    VideosRepo {

    override suspend fun getAllVideos(): Output<VideoModel?> {
        return withContext(Dispatchers.IO)
        {
            return@withContext safeApiCall(
                call = { videosDataSourceImp.getAllVideos() }
            )
        }    }

}