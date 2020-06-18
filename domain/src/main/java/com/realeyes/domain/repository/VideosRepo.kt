package com.realeyes.domain.repository

import com.realeyes.domain.entities.VideoModel
import com.realeyes.domain.entities.Output

interface VideosRepo {

    suspend fun getAllVideos(): Output<VideoModel?>
}