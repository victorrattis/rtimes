package com.study.vhra.rtimes.data

import android.util.Log
import com.study.vhra.rtimes.domain.model.DateInfo
import com.study.vhra.rtimes.domain.model.HourInfo
import com.study.vhra.rtimes.domain.storage.TimeStorage

class TimeStorageLocal: TimeStorage {
    override fun save(date: DateInfo, hour: HourInfo, callback: (Boolean) -> Unit) {
        // TODO: Implement that!
        Log.d("devlog", "TimeStorageLocal.save: $date, $hour")
        callback(true)
    }
}