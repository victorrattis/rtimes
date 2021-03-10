package com.study.vhra.rtimes.data

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.study.vhra.rtimes.domain.model.DateInfo
import com.study.vhra.rtimes.domain.model.HourInfo
import com.study.vhra.rtimes.domain.storage.TimeRegisterStorage
import com.study.vhra.rtimes.domain.storage.TimeStorage
import com.study.vhra.rtimes.domain.usecase.TimeRegisterOfDayInfo

class TimeStorageLocal: TimeStorage, TimeRegisterStorage {
    init {
        Log.d("devlog", "TimeStorageLocal.init")
    }

    override fun save(date: DateInfo, hour: HourInfo, callback: (Boolean) -> Unit) {
        // TODO: Implement that!
        Log.d("devlog", "TimeStorageLocal.save: $date, $hour")
        callback(true)
    }

    override fun getTimeRegisters(callback: (List<TimeRegisterOfDayInfo>) -> Unit) {
        Log.d("devlog", "TimeRegisterStorageLocal.getTimeRegisters")
        Handler(Looper.getMainLooper()).postDelayed({
            callback(arrayListOf(TimeRegisterOfDayInfo(DateInfo(10, 3, 2021), emptyList())))
        }, 2000)
    }
}