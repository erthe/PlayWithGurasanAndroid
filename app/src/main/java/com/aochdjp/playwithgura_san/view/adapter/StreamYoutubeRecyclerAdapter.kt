package com.aochdjp.playwithgura_san.view.adapter

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aochdjp.playwithgura_san.R
import com.aochdjp.playwithgura_san.databinding.LayoutStreamYoutubeRowBinding
import com.aochdjp.playwithgura_san.model.Stream
import com.aochdjp.playwithgura_san.model.util.DiffUtilCallback
import kotlinx.android.synthetic.main.layout_stream_twitch_row.view.stream_url

class StreamYoutubeRecyclerAdapter(private val context: Context?): RecyclerView.Adapter<StreamYoutubeRecyclerAdapter.ViewHolder>() {
    var streams: List<Stream.StreamList>? = null
        set(value) {
            DiffUtil.calculateDiff(DiffUtilCallback(field, value)).dispatchUpdatesTo(this)
            field = value
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<LayoutStreamYoutubeRowBinding>(LayoutInflater.from(context), R.layout.layout_stream_youtube_row, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount() = streams?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ev = streams?.getOrNull(position) ?: return
        holder.binding.youtube = ev
        holder.binding.root.setOnClickListener {
            if (context is Context) {
            val uri: Uri = Uri.parse(it.stream_url.text.toString())
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        }
    }
    }

    inner class ViewHolder(val binding: LayoutStreamYoutubeRowBinding): RecyclerView.ViewHolder(binding.root)
}