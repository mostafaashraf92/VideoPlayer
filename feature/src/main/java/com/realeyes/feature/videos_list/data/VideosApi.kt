package com.realeyes.feature.videos_list.data

import com.realeyes.domain.entities.VideoModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface VideosApi {

    @GET("getVideos")
    fun getAllVideosAsync()
            : Deferred<Response<VideoModel?>>
}