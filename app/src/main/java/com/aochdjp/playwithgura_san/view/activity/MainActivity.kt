package com.aochdjp.playwithgura_san.view.activity

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.aochdjp.playwithgura_san.R
import com.aochdjp.playwithgura_san.core.constants.Key
import com.aochdjp.playwithgura_san.core.constants.createIntent
import com.aochdjp.playwithgura_san.databinding.ActivityMainBinding
import com.aochdjp.playwithgura_san.view.adapter.MenuRecyclerAdapter
import com.aochdjp.playwithgura_san.viewmodel.MenuViewModel
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), LifecycleOwner {
    private lateinit var menuViewModel: MenuViewModel
    private val menuListAdapter by lazy { MenuRecyclerAdapter(this@MainActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        menuViewModel = ViewModelProviders.of(this).get(MenuViewModel::class.java)

        lifecycle.addObserver(menuViewModel)

        menuViewModel.menus.observe(this, Observer { menuListAdapter.menus = it?.menus })

        binding.viewModel = menuViewModel

        with(binding.menuList) {
            adapter = menuListAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            menuListAdapter.setOnClickListener {
                if (it.url == getString(R.string.log)) {
                    val df = SimpleDateFormat(Key.YMD, Locale.JAPAN)
                    val date = Date()
                    startActivity(LogActivity.createIntent(this@MainActivity, df.format(date)))
                } else if(it.url == getString(R.string.ramen)) {
                    val intent = Intent(this@MainActivity, RamenActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}
