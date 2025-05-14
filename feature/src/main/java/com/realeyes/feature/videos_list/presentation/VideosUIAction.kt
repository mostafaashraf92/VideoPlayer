package com.realeyes.feature.videos_list.presentation

import com.realeyes.core.viewmodel.ViewAction

sealed class VideosUIAction : ViewAction {
    data object LoadVideos : VideosUIAction()
}
