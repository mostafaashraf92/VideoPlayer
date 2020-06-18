package com.realeyes.core.view

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<VH : RecyclerView.ViewHolder, T>
    : RecyclerView.Adapter<VH>() {

    protected var items: ArrayList<T>? = null

    fun add(item: T) {
        items?.add(item)
        notifyDataSetChanged()
    }

    fun addAll(items: ArrayList<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun updateItem(index: Int, item: T) {
        items?.set(index, item)
        notifyItemChanged(index)
    }

    fun remove(index: Int) {
        items?.removeAt(index)
        notifyItemRemoved(index)
    }

    fun clear() {
        items?.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }
}