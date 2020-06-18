package com.realeyes.data.repositories

interface VideosDataSource<T> {
    suspend fun getAllVideos(): T?

}