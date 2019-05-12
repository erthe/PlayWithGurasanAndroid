package com.aochdjp.playwithgura_san.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aochdjp.playwithgura_san.R
import com.aochdjp.playwithgura_san.databinding.LayoutLogRowBinding
import com.aochdjp.playwithgura_san.model.Log
import com.aochdjp.playwithgura_san.model.util.DiffUtilCallback

class LogRecyclerAdapter(private val context: Context?): RecyclerView.Adapter<LogRecyclerAdapter.ViewHolder>() {
    var logs: List<Log.LogList>? = null
        set(value) {
            DiffUtil.calculateDiff(DiffUtilCallback(field, value)).dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<LayoutLogRowBinding>(LayoutInflater.from(context), R.layout.layout_log_row, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount() = logs?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ev = logs?.getOrNull(position) ?: return
        holder.binding.log = ev
    }

    inner class ViewHolder(val binding: LayoutLogRowBinding): RecyclerView.ViewHolder(binding.root)
}