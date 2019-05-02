package com.aochdjp.playwithgura_san.view.activity

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.aochdjp.playwithgura_san.R
import com.aochdjp.playwithgura_san.databinding.ActivityMainBinding
import com.aochdjp.playwithgura_san.view.adapter.MenuRecyclerAdapter
import com.aochdjp.playwithgura_san.viewmodel.MenuViewModel

class MainActivity : AppCompatActivity(), LifecycleOwner {
    private lateinit var menuViewModel: MenuViewModel
    private val menuListAdapter by lazy { MenuRecyclerAdapter(this@MainActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        menuViewModel = ViewModelProviders.of(this).get(MenuViewModel::class.java)
        lifecycle.addObserver(menuViewModel)

        menuViewModel.menu.observe(this, Observer { menuListAdapter.menu = it?.menu })

        binding.viewModel = menuViewModel

        with(binding.menuList) {
            adapter = menuListAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}