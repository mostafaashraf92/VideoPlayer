package com.realeyes.feature

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.realeyes.core.Constants
import com.realeyes.core.interfaces.IOnVideoClickedListener
import com.realeyes.domain.entities.ErrorModel
import com.realeyes.domain.entities.VideoItemModel
import com.realeyes.domain.entities.VideoModel
import com.realeyes.feature.databinding.FragmentVideosListBinding
import kotlinx.android.synthetic.main.fragment_videos_list.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class VideosListFragment : Fragment(), IOnVideoClickedListener<VideoItemModel> {


    private val mViewModel: VideosViewModel by viewModel()
    private val mAdapter: VideosAdapter by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentVideosListBinding>(
            inflater,
            R.layout.fragment_videos_list,
            container,
            false
        )
            .also {
                it.viewModel = mViewModel
                it.lifecycleOwner = viewLifecycleOwner
                it.listener = this@VideosListFragment
            }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        mViewModel.responseLiveData.observe(
            this,
            Observer<VideoModel> { setAdapterData(it) })
        mViewModel.errorLiveData.observe(
            this,
            Observer<ErrorModel> { showErrorMessgae(it) })
    }

    private fun initViews() {
        val linearLayoutManager = LinearLayoutManager(context)
        videosRecycler.layoutManager = linearLayoutManager
        videosRecycler.adapter = mAdapter

    }

    private fun showErrorMessgae(errorModel: ErrorModel?) {
        Toast.makeText(activity, errorModel?.errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun setAdapterData(video: VideoModel?) {
        video?.videos?.let { mAdapter.setAllItems(it) }
    }

    override fun onListItemClicked(item: VideoItemModel) {
        val intent = Intent(this.activity, VideoPlayerActivity::class.java)
        intent.putExtra(Constants.INTENT_EXTRA_VIDEO_MODEL, item)
        startActivity(intent)
    }
}
