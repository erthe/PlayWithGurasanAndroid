package com.aochdjp.playwithgura_san.view.activity

import android.arch.lifecycle.LifecycleOwner
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.aochdjp.playwithgura_san.R
import com.aochdjp.playwithgura_san.core.constants.createInstance
import com.aochdjp.playwithgura_san.core.constants.parseTargetDate
import com.aochdjp.playwithgura_san.view.fragment.LogFragment

class LogActivity : AppCompatActivity(), LifecycleOwner {

    companion object;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        val targetDate: String = parseTargetDate()
        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.detailContainer, LogFragment.createInstance(this, targetDate))
            transaction.commit()
        }
    }
}
