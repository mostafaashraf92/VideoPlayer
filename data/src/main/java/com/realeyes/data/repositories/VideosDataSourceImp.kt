package com.realeyes.data.repositories

import com.realeyes.data.repositories.dto.VideoDTO
import com.realeyes.data.repositories.dto.VideoItemDTO
import com.realeyes.domain.entities.Output
import com.realeyes.domain.entities.Video
import com.realeyes.domain.entities.VideoItem
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class VideosDataSourceImp(private val httpClient: HttpClient) : BaseRepository(),
    VideosDataSource<Flow<Output<Video?>>> {

    override suspend fun getAllVideos(): Flow<Output<Video?>> {
        return withContext(Dispatchers.IO)
        {
            return@withContext safeApiCall<VideoDTO?, Video?>(
                call = {
                    httpClient.get("https://8806-156-213-98-82.ngrok-free.app/getAllVideos")
                },
                mapper = { dto -> dto?.mapToDomain() }
            )
        }
    }
}

fun VideoDTO.mapToDomain(): Video = Video(this.videos?.map { it.mapToDomain() }?.toMutableList())
fun VideoItemDTO.mapToDomain(): VideoItem =
    VideoItem(this.title, this.imageUri, this.subTitle, this.duration, this.uri)