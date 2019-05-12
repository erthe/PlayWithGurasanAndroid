package com.aochdjp.playwithgura_san.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aochdjp.playwithgura_san.R
import com.aochdjp.playwithgura_san.databinding.FragmentLogBinding
import com.aochdjp.playwithgura_san.view.adapter.LogRecyclerAdapter
import com.aochdjp.playwithgura_san.viewmodel.LogViewModel

class LogFragment : Fragment() {
    private lateinit var logViewModel: LogViewModel
    private val logListAdapter by lazy { LogRecyclerAdapter(mContext) }
    var mContext : Context? = null
    private lateinit var binding: FragmentLogBinding

    companion object {
        fun createInstance(mc : Context): LogFragment {
            val carDetailFragment = LogFragment()
            carDetailFragment.mContext = mc

            return carDetailFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_log, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listRefresh()
        with(binding.logList) {
            adapter = logListAdapter
            layoutManager = LinearLayoutManager(mContext)
        }
    }

    private fun listRefresh() {
        logViewModel = ViewModelProviders.of(this).get(LogViewModel::class.java)
        lifecycle.addObserver(logViewModel)

        logViewModel.logs.observe(this, Observer { logListAdapter.logs = it?.lines })

        binding.viewModel = logViewModel
    }
}