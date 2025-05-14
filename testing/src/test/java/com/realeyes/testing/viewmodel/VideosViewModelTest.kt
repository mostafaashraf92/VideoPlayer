package com.realeyes.testing.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.realeyes.domain.entities.Output
import com.realeyes.domain.entities.VideoItem
import com.realeyes.domain.entities.Video
import com.realeyes.domain.usecase.GetVideosUseCase
import com.realeyes.feature.videos_list.presentation.VideosViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class VideosViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
    }

    @Test
    fun executeUseCaseSuccessFully() {
        val useCase = mockk<GetVideosUseCase>()

        every { runBlocking { useCase.getAllVideos() } } returns Output.Success<Video>(
            constructVideoModel()
        )
        val viewModel = VideosViewModel(useCase)
        viewModel.coroutineScope = CoroutineScope(Dispatchers.Unconfined)
        viewModel.executeUseCase()
        val expected = viewModel.responseLiveData.value
        Assert.assertNotNull(expected)

    }

    private fun constructVideoModel(): Video {
        val videoModel1 = VideoItem(
            "Mission",
            "",
            "Action Movie",
            36000,
            ""
        )
        val videoModel2 = VideoItem(
            "Titanic",
            "",
            "Romantic Movie",
            36000,
            ""
        )
        val arrayList = ArrayList<VideoItem>()
        arrayList.add(videoModel1)
        arrayList.add(videoModel2)
        return Video(arrayList)
    }

}