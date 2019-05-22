package com.aochdjp.playwithgura_san.view.fragment

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.aochdjp.playwithgura_san.R
import com.aochdjp.playwithgura_san.databinding.FragmentLogBinding
import com.aochdjp.playwithgura_san.model.util.DatePick
import com.aochdjp.playwithgura_san.view.adapter.LogRecyclerAdapter
import com.aochdjp.playwithgura_san.viewmodel.LogViewModel
import com.aochdjp.playwithgura_san.core.constants.Key
import com.aochdjp.playwithgura_san.core.constants.createInstance
import java.text.SimpleDateFormat
import java.util.*

class LogFragment : Fragment() {
    private lateinit var logViewModel: LogViewModel
    private val logListAdapter by lazy { LogRecyclerAdapter(mContext) }
    var mContext : Context? = null
    private lateinit var binding: FragmentLogBinding
    var targetDate: String? = null

    companion object;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_log, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listRefresh(targetDate)
        with(binding.logList) {
            adapter = logListAdapter
            layoutManager = LinearLayoutManager(mContext)
        }

        val parentContext = mContext
        val parentDate = targetDate

        view.findViewById<Button>(R.id.showDatePickerDialog).setOnClickListener {
            DatePick.openDatePicker(parentDate, mContext)
            val fragmentManager: FragmentManager? = this.fragmentManager
            DatePick.listener = object : DatePick.Companion.Listener {
                override fun onDismiss(date: String) {
                    if (parentContext is Context) {
                        if (fragmentManager is FragmentManager) {
                            val transaction = fragmentManager.beginTransaction()
                            transaction.replace(R.id.detailContainer,  LogFragment.createInstance(parentContext, date))
                            transaction.commit()
                        }
                    }
                }
            }
        }

        val mSwipeRefreshLayout: SwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_log)
        mSwipeRefreshLayout.setOnRefreshListener {
            if (parentContext is Context) {
                val df = SimpleDateFormat(Key.YMD, Locale.JAPAN)
                val now = Date()
                var date: String = df.format(now)

                if (parentDate is String) {
                    date = parentDate
                }

                val transaction = fragmentManager!!.beginTransaction()
                transaction.replace(R.id.detailContainer,  LogFragment.createInstance(parentContext, date))
                transaction.commit()
            }
        }
    }

    private fun listRefresh(targetDate: String?) {
        val df = SimpleDateFormat(Key.YMD, Locale.JAPAN)
        val now = Date()
        var date: String = df.format(now)

        if (targetDate is String) {
            date = targetDate.replace("/", "")
        }

        logViewModel = ViewModelProviders.of(this).get(LogViewModel::class.java)
        logViewModel.getLogList(date)

        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            lifecycle.addObserver(logViewModel)
        }

        logViewModel.logs.observe(this, Observer { logListAdapter.logs = it?.lines })

        binding.viewModel = logViewModel
    }
}