package com.aochdjp.playwithgura_san.view.fragment

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aochdjp.playwithgura_san.R
import com.aochdjp.playwithgura_san.databinding.FragmentStreamBinding
import com.aochdjp.playwithgura_san.view.adapter.StreamCavetubeRecyclerAdapter
import com.aochdjp.playwithgura_san.view.adapter.StreamNicoRecyclerAdapter
import com.aochdjp.playwithgura_san.view.adapter.StreamTwitchRecyclerAdapter
import com.aochdjp.playwithgura_san.view.adapter.StreamYoutubeRecyclerAdapter
import com.aochdjp.playwithgura_san.viewmodel.StreamViewModel
import java.util.stream.Stream

class StreamFragment : Fragment() {
    private lateinit var streamViewModel: StreamViewModel
    private val streamYoutubeListAdapter by lazy { StreamYoutubeRecyclerAdapter(mContext) }
    private val streamTwitchListAdapter by lazy { StreamTwitchRecyclerAdapter(mContext) }
    private val streamNicoListAdapter by lazy { StreamNicoRecyclerAdapter(mContext) }
    private val streamCavetubeListAdapter by lazy { StreamCavetubeRecyclerAdapter(mContext) }
    var mContext : Context? = null
    private lateinit var binding: FragmentStreamBinding

    companion object {
        fun createInstance(context: Context): StreamFragment {
            val streamFragment = StreamFragment()
            streamFragment.mContext = context
            return streamFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stream, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listRefresh()
        with(binding.youtubeList) {
            adapter = streamYoutubeListAdapter
            layoutManager = LinearLayoutManager(mContext)
        }

        with(binding.twitchList) {
            adapter = streamTwitchListAdapter
            layoutManager = LinearLayoutManager(mContext)
        }

        with(binding.nicoList) {
            adapter = streamNicoListAdapter
            layoutManager = LinearLayoutManager(mContext)
        }

        with(binding.cavetubeList) {
            adapter = streamCavetubeListAdapter
            layoutManager = LinearLayoutManager(mContext)
        }

        val parentContext = mContext

        val mSwipeRefreshLayout: SwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_stream)
        mSwipeRefreshLayout.setOnRefreshListener {
            if (parentContext is Context) {
                val transaction = fragmentManager!!.beginTransaction()
                transaction.replace(R.id.detailContainer,  StreamFragment.createInstance(parentContext))
                transaction.commit()
            }
        }
    }

    private fun listRefresh() {
        streamViewModel = ViewModelProviders.of(this).get(StreamViewModel::class.java)
        streamViewModel.getStreamList()

        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            lifecycle.addObserver(streamViewModel)
        }

        streamViewModel.streams.observe(this, Observer { streamYoutubeListAdapter.streams = it?.youtube })
        streamViewModel.streams.observe(this, Observer { streamTwitchListAdapter.streams = it?.twitch })
        streamViewModel.streams.observe(this, Observer { streamNicoListAdapter.streams = it?.nico })
        streamViewModel.streams.observe(this, Observer { streamCavetubeListAdapter.streams = it?.cavetube })

        binding.viewModel = streamViewModel
    }
}