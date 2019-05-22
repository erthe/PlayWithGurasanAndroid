package com.aochdjp.playwithgura_san.view.fragment

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.text.Spannable
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aochdjp.playwithgura_san.R
import com.aochdjp.playwithgura_san.databinding.FragmentRamenBinding
import com.aochdjp.playwithgura_san.viewmodel.RamenViewModel

class RamenFragment : Fragment() {
    private lateinit var binding: FragmentRamenBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ramen, container, false)

        val ramenViewModel = ViewModelProviders.of(this).get(RamenViewModel::class.java)
        ramenViewModel.getRamen()
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            lifecycle.addObserver(ramenViewModel)
        }

        ramenViewModel.ramen.observe(this, Observer {
            if (it?.message is String) {
                binding.ramenMessage.text = it.message
                binding.ramenUrl.text = it.url

                val uri: Uri = Uri.parse(it.url)
                val spannable: Spannable = Spannable.Factory.getInstance().newSpannable(binding.ramenUrl.text)
                val us = UnderlineSpan()
                spannable.setSpan(us, 0, binding.ramenUrl.text.length, spannable.getSpanFlags(us))
                binding.ramenUrl.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mSwipeRefreshLayout: SwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_ramen)
        mSwipeRefreshLayout.setOnRefreshListener {
            val transaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.detailContainer,  RamenFragment())
            transaction.commit()
        }
    }
}