import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.realeyes.core.extensions.toTimeFormat
import com.realeyes.feature.NavAction
import com.realeyes.feature.NavigationViewModel
import com.realeyes.feature.videos_list.presentation.VideoItemUIModel
import com.realeyes.feature.videos_list.presentation.VideosUIAction
import com.realeyes.feature.videos_list.presentation.VideosViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MediaListScreen(
    navigationViewModel: NavigationViewModel,
    videosViewModel: VideosViewModel = koinViewModel()
) {
    val uistate by videosViewModel.uiState.collectAsStateWithLifecycle()
    Spacer(modifier = Modifier.height(5.dp))
    LaunchedEffect(uistate.videos.isEmpty()) {
        if (uistate.videos.isEmpty()) {
        videosViewModel.onUiAction(VideosUIAction.LoadVideos)
            }
    }

    Box(modifier = Modifier.fillMaxSize())
    {
        if (uistate.showLoading) {
            CircularProgressIndicator(
                color = Color.Blue,
                strokeWidth = 4.dp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(50.dp)
            )
        } else if (uistate.error != null) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = uistate.error?.errorMessage ?: "Something went wrong",
                color = Color.Red,
                textAlign = TextAlign.Center,
            )
        } else {
            LazyColumn {
                items(uistate.videos.size) {
                    VideoListItem(uistate.videos[it], onClick = { item ->
                        navigationViewModel.onUiAction(
                            NavAction.ToPlayerScreen(item)
                        )
                    })
                }
            }
        }
    }

}

@Composable
fun VideoListItem(video: VideoItemUIModel, onClick: (VideoItemUIModel) -> Unit) {
    Row(Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(10.dp)
        .clickable { onClick(video) }) {
        Image(
            painter = rememberAsyncImagePainter(video.imageUri),
            contentDescription = "Random Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
        )

        Column(
            Modifier
                .height(150.dp)
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = video.title ?: "",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Start
            )
            Text(
                text = video.subTitle ?: "",
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Start
            )
            Text(
                text = video.duration?.toTimeFormat() ?: "00:00:00",
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Start
            )
        }

    }
}