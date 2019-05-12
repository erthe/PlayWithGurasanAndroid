package com.aochdjp.playwithgura_san.view.activity

import android.arch.lifecycle.LifecycleOwner
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.aochdjp.playwithgura_san.R
import com.aochdjp.playwithgura_san.view.fragment.LogFragment

class LogActivity : AppCompatActivity(), LifecycleOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.detailContainer, LogFragment.createInstance(this))
            transaction.commit()
        }
    }
}
