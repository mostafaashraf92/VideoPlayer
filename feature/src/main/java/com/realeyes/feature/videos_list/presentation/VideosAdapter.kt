package com.realeyes.feature.videos_list.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.realeyes.core.interfaces.IOnVideoClickedListener
import com.realeyes.domain.entities.VideoItemModel
import com.realeyes.feature.databinding.VideoItemBinding

import org.koin.core.KoinComponent

class VideosAdapter :
    RecyclerView.Adapter<VideosAdapter.ItemViewHolder>(),
    KoinComponent {
    override fun getItemCount(): Int {
        return list.size
    }

    private var list: ArrayList<VideoItemModel> = ArrayList()

    fun setAllItems(items: ArrayList<VideoItemModel>) {
        list = items
        notifyDataSetChanged()
    }

    companion object {
        var onItemClickedListener: IOnVideoClickedListener<VideoItemModel>? = null
        @JvmStatic
        @BindingAdapter("app:setItemClickListener")
        fun setItemClickListener(
            recyclerView: RecyclerView,
            listener: IOnVideoClickedListener<VideoItemModel>
        ) {
            onItemClickedListener = listener
        }

    }

    fun onItemClicked(view: View, model: VideoItemModel) {
        onItemClickedListener?.onListItemClicked(model)

    }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ItemViewHolder {
        val binding =
            VideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(
            binding,
            this@VideosAdapter
        )
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val characterModel = list[position]
        holder.bind(characterModel)

    }

    class ItemViewHolder constructor(
        private val binding: VideoItemBinding,
        private val adapter: VideosAdapter
    ) : RecyclerView.ViewHolder(binding.root), KoinComponent {
        fun bind(videoItem: VideoItemModel) {
            binding.model = videoItem
            binding.adapter = adapter
        }
    }

}

