package com.realeyes.feature

import android.net.Uri
import com.realeyes.feature.videos_list.presentation.VideoItemUIModel
import kotlinx.serialization.json.Json

sealed class Screen(val route: String) {
    data object MediaList : Screen(route = "mediaList")
    data object PlayerScreen : Screen(route = "playerScreen/{video}") {
        const val ARG_VIDEO = "video"
        fun createRoute(video: VideoItemUIModel): String {
            val encoded = Json.encodeToString(VideoItemUIModel.serializer(), video)
            val safe = Uri.encode(encoded)
            return "playerScreen/$safe"
        }
    }
}
