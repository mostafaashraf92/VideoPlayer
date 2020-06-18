package com.realeyes.feature.videos_list.data

import com.realeyes.data.repositories.VideosDataSource
import com.realeyes.domain.entities.VideoModel
import retrofit2.Response

class VideosDataSourceImp(private val videosApi: VideosApi) :
    VideosDataSource<Response<VideoModel?>> {

    override suspend fun getAllVideos(): Response<VideoModel?>{
        return videosApi.getAllVideosAsync().await()
    }


}