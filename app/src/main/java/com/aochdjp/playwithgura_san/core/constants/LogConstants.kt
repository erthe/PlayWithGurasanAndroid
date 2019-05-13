package com.aochdjp.playwithgura_san.core.constants

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.aochdjp.playwithgura_san.view.activity.LogActivity
import com.aochdjp.playwithgura_san.view.fragment.LogFragment

object Key {
    const val LOG = "log"
    const val YMD = "yyyy/MM/dd"
}

// Instance Builder
fun LogActivity.Companion.createIntent(context: Context, targetDate: String): Intent =
    Intent(context, LogActivity::class.java).apply {
        putExtra(Key.LOG, targetDate)
    }

fun LogFragment.Companion.createInstance(context : Context, targetDate: String): LogFragment =
    LogFragment().apply {
        arguments = Bundle().apply {
            putSerializable(Key.LOG, targetDate)
        }

        this.mContext = context
        this.targetDate = targetDate
        return this
    }

// Parser
fun LogActivity.parseTargetDate(): String =
    intent.getSerializableExtra(Key.LOG) as String

