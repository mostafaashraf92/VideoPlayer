package com.realeyes.testing.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.realeyes.domain.entities.ErrorModel
import com.realeyes.domain.entities.Output
import com.realeyes.domain.entities.VideoItem
import com.realeyes.domain.entities.Video
import com.realeyes.domain.repository.VideosRepo
import com.realeyes.domain.usecase.GetVideosUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetVideosUseCaseTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
    }

    @Test
    fun getAllVideosSuccess() {
        runBlocking {

            val repo = mockk<VideosRepo>()
            every { runBlocking { repo.getAllVideos() } } returns Output.Success<Video>(
                constructVideoModel()
            )

            val useCase = GetVideosUseCase(repo)
            val expected =
                useCase.getAllVideos() as Output.Success<Video?>
            Assert.assertNotNull(expected)
            Assert.assertEquals(expected.output?.videos?.size, 2)
        }

    }

    @Test
    fun getAllVideosError() {
        runBlocking {

            val repo = mockk<VideosRepo>()
            every { runBlocking { repo.getAllVideos() } } returns Output.Error(
                constructErrorModel()
            )

            val useCase = GetVideosUseCase(repo)
            val expected =
                useCase.getAllVideos() as Output.Error
            Assert.assertNotNull(expected.exception)
            Assert.assertEquals(expected.exception.errorCode, "500")
        }

    }

    private fun constructVideoModel(): Video {
        var videoModel1 = VideoItem(
            "Mission",
            "",
            "Action Movie",
            36000,
            ""
        )
        var videoModel2 = VideoItem(
            "Titanic",
            "",
            "Romantic Movie",
            36000,
            ""
        )
        var arrayList = ArrayList<VideoItem>()
        arrayList.add(videoModel1)
        arrayList.add(videoModel2)
        return Video(arrayList)
    }

    private fun constructErrorModel(): ErrorModel {
        return ErrorModel("500", "error")
    }

}