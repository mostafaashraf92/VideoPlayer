package com.realeyes.feature.videos_list.presentation

import com.realeyes.core.viewmodel.BaseUseCaseViewModel
import com.realeyes.core.viewmodel.ViewSideEffect
import com.realeyes.domain.entities.Output
import com.realeyes.domain.entities.Video
import com.realeyes.domain.usecase.GetVideosUseCase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class VideosViewModel(private val useCase: GetVideosUseCase) :
    BaseUseCaseViewModel<Video, VideosUIAction, VideosUIState, VideosModelState, ViewSideEffect>(
        useCase
    ),
    KoinComponent {

    override fun reduceToUi(state: VideosModelState, uiState: VideosUIState): VideosUIState {
        val videos = state.videoModel?.toVideoListItems().orEmpty()
        return uiState.copy(
            videos = videos,
            error = state.errorModel,
            showLoading = videos.isEmpty() && state.errorModel == null
        )
    }
    private fun executeUseCase() {
        coroutineScope.launch {
            useCase.getAllVideos().collect { result ->
                when (result) {
                    is Output.Success -> {
                        updateModelStateAndReduceToUi {
                            copy(videoModel = result.output, errorModel = null)
                        }
                    }

                    is Output.Error -> {
                        updateModelStateAndReduceToUi {
                            copy(videoModel = null, errorModel = result.exception)
                        }
                    }

                    Output.Loading -> {
                        updateUiState {
                            copy(showLoading = true, videos = emptyList(), error = null)
                        }
                        updateModelStateAndReduceToUi {
                            copy(videoModel = null, errorModel = null)
                        }
                    }
                }
            }
        }
    }

    override fun initializeState(): VideosModelState = VideosModelState(Video(null), null)
    override fun initializeUiState(): VideosUIState = VideosUIState(emptyList(), null, true)

    override fun onUiAction(uiAction: VideosUIAction) {
        when (uiAction) {
            is VideosUIAction.LoadVideos -> {
                executeUseCase()
            }
        }
    }
}