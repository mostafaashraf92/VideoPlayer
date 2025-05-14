package com.realeyes.feature

import com.realeyes.core.viewmodel.ViewAction
import com.realeyes.feature.videos_list.presentation.VideoItemUIModel


sealed class NavAction : ViewAction {
    data object ToMediaList : NavAction()
    data class ToPlayerScreen(val videoItem: VideoItemUIModel) : NavAction()
}
