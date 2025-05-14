import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.realeyes.feature.NavigationViewModel
import com.realeyes.feature.videos_list.presentation.VideoItemUIModel

@Composable
fun PlayerScreen(navigationViewModel: NavigationViewModel, video: VideoItemUIModel) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(video.uri ?: "")
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16 / 9f)
    ) {
        AndroidView(
            factory = {
                PlayerView(it).apply {
                    player = exoPlayer
                    useController = true
                }
            }
        )
    }
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.pause()
            exoPlayer.release()
        }
    }

}