package com.realeyes.core.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.realeyes.core.extensions.toTimeFormat

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("app:toTimeFormat")
    fun setTimeFormat(textView: TextView, duration: Long?) {
        textView.text = duration?.toTimeFormat()
    }

    @JvmStatic
    @BindingAdapter("app:setImgSrc")
    fun setImageSrc(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            .override(256, 256)
            .fitCenter().into(imageView)
    }
}