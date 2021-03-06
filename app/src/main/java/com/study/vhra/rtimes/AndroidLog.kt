package com.study.vhra.rtimes

import android.util.Log
import com.study.vhra.rtimes.domain.ILog

class AndroidLog : ILog {
    companion object {
        const val TAG = "devlog"
    }

    override fun debug(tag: String, message: String) {
        Log.d(TAG, "[$tag] $message")
    }
}