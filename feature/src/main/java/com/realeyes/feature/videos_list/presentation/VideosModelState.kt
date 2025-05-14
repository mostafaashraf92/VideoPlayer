package com.realeyes.feature.videos_list.presentation

import com.realeyes.core.viewmodel.BusinessModelState
import com.realeyes.core.viewmodel.ViewUIState
import com.realeyes.domain.entities.ErrorModel
import com.realeyes.domain.entities.VideoItem
import com.realeyes.domain.entities.Video
import kotlinx.serialization.Serializable

data class VideosModelState(
    val videoModel: Video? = null,
    val errorModel: ErrorModel? = null,
) : BusinessModelState


data class VideosUIState(
    val videos: List<VideoItemUIModel> = emptyList(),
    val error: ErrorModel? = null,
    val showLoading: Boolean = false,
) : ViewUIState

@Serializable
data class VideoItemUIModel(
    var title: String?,
    var imageUri: String?,
    var subTitle: String?,
    var duration: Long?,
    var uri: String?
)

fun Video.toVideoListItems(): List<VideoItemUIModel> {
    return this.videos?.map {
        it.toVideosItemUIModel()
    }?.toList().orEmpty().toList()
}

private fun VideoItem.toVideosItemUIModel(): VideoItemUIModel = VideoItemUIModel(
    title = this.title,
    imageUri = this.imageUri,
    subTitle = this.subTitle,
    duration = this.duration,
    uri = this.uri
)