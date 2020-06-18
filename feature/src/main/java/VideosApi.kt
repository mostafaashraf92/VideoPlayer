package com.realeyes.feature

import com.realeyes.domain.entities.VideoModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VideosApi {

    @GET("getVideos")
    fun getAllVideos()
            : Deferred<Response<VideoModel?>>
}