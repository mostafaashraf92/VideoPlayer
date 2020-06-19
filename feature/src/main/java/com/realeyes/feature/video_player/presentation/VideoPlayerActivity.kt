package com.realeyes.feature.video_player.presentation

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.realeyes.core.Constants
import com.realeyes.domain.entities.VideoItemModel
import com.realeyes.feature.R
import kotlinx.android.synthetic.main.activity_video_player.*


class VideoPlayerActivity : AppCompatActivity(), Player.EventListener {

    private var exoPlayer: ExoPlayer? = null
    private var hlsMediaSource: HlsMediaSource? = null
    private var model: VideoItemModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_video_player)
        if (intent.hasExtra(Constants.INTENT_EXTRA_VIDEO_MODEL)) {
            model =
                intent.getSerializableExtra(Constants.INTENT_EXTRA_VIDEO_MODEL) as VideoItemModel?
        }
        setUp()

    }

    private fun setUp() {
        initializePlayer()
        buildMediaSource(Uri.parse(model?.uri))
    }

    private fun buildMediaSource(uri: Uri?) {
        val dataSourceFactory: DataSource.Factory =
            DefaultHttpDataSourceFactory(Util.getUserAgent(this, "VideoPlayer"))
        hlsMediaSource =
            HlsMediaSource.Factory(dataSourceFactory)
                .createMediaSource(
                    uri
                )
        exoPlayer?.prepare(hlsMediaSource)
        exoPlayer?.playWhenReady = true
        exoPlayer?.addListener(this@VideoPlayerActivity)
    }


    override fun onPause() {
        super.onPause()
        pausePlayer()
    }

    override fun onRestart() {
        super.onRestart()
        resumePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

    private fun releasePlayer() {
        exoPlayer?.let {
            it.release()
            exoPlayer = null
        }
    }

    private fun pausePlayer() {
        exoPlayer?.let {
            it.playWhenReady = false
            it.playbackState
        }
    }

    private fun resumePlayer() {
        exoPlayer?.let {
            it.playWhenReady = true
            it.playbackState
        }
    }

    private fun initializePlayer() {

        if (exoPlayer == null && videoPlayer != null) {
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this)
            videoPlayer.player = exoPlayer
        }

    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        when (playbackState) {
            Player.STATE_BUFFERING -> {
                videoPlayer.hideController()
                videoPlayer.useController=false
                spinnerVideoContainer.visibility = View.VISIBLE
            }
            Player.STATE_ENDED -> {
            }
            Player.STATE_IDLE -> {
            }
            Player.STATE_READY -> {
                videoPlayer.useController=true
                videoPlayer.showController()
                spinnerVideoContainer.visibility = View.GONE
            }
            else -> {
            }
        }
    }

}
