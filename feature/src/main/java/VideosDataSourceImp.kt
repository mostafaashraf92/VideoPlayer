package com.realeyes.feature

import com.realeyes.data.repositories.VideosDataSource
import com.realeyes.domain.entities.VideoModel
import retrofit2.Response

class VideosDataSourceImp(private val videosApi: VideosApi) :
    VideosDataSource<Response<VideoModel?>> {

    override suspend fun getAllVideos(): Response<VideoModel?>{
        return videosApi.getAllVideos().await()
    }


}