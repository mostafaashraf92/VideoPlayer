package com.realeyes.feature.videos_list.presentation

import androidx.lifecycle.MutableLiveData
import com.realeyes.core.viewmodel.BaseViewModel
import com.realeyes.domain.entities.ErrorModel
import com.realeyes.domain.entities.Output
import com.realeyes.domain.entities.VideoModel
import com.realeyes.domain.usecase.GetVideosUseCase
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class VideosViewModel(private val useCase: GetVideosUseCase) :
    BaseViewModel<VideoModel>(useCase),
    KoinComponent {

    var showEmptyListMessage: MutableLiveData<Boolean> = MutableLiveData()

    init {
        executeUseCase()
    }

    private fun executeUseCase() {
        coroutineScope.launch {
            when (val result = useCase.getAllVideos()) {
                is Output.Success -> handleSuccess(result.output)
                is Output.Error -> handleError(result.exception)
            }
        }
    }


    override fun handleSuccess(response: VideoModel?) {
        responseLiveData.postValue(response)
        showEmptyListMessage.value = shouldShowEmptyMessage(response)
    }

    override fun handleError(errorModel: ErrorModel?) {
        errorLiveData.postValue(errorModel)
        showEmptyListMessage.value = false
    }

    private fun shouldShowEmptyMessage(response: VideoModel?) =
        !(response?.videos != null && response.videos?.size!! > 0)

}