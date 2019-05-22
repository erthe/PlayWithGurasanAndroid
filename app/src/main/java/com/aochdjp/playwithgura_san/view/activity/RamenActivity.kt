package com.aochdjp.playwithgura_san.view.activity

import android.arch.lifecycle.LifecycleOwner
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.aochdjp.playwithgura_san.R
import com.aochdjp.playwithgura_san.view.fragment.RamenFragment



class RamenActivity : AppCompatActivity(), LifecycleOwner {

    companion object;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ramen)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.detailContainer, RamenFragment())
                .commit()
        }
    }
}
