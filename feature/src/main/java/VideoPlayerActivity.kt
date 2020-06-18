package com.realeyes.feature

import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
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
import kotlinx.android.synthetic.main.activity_video_player.*


class VideoPlayerActivity : AppCompatActivity(), Player.EventListener {

    private var exoPlayer: ExoPlayer? = null
    private var hlsMediaSource: HlsMediaSource? = null
    private var model: VideoItemModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window
            .setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
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


}
