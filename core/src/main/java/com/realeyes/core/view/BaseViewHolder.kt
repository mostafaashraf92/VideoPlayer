package com.realeyes.core.view

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View,viewbinding:ViewDataBinding)
    : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}