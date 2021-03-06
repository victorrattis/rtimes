package com.study.vhra.rtimes.domain.storage

import com.study.vhra.rtimes.domain.model.DateInfo
import com.study.vhra.rtimes.domain.model.HourInfo

interface TimeStorage {
    fun save(date: DateInfo, hour: HourInfo, callback: (Boolean) -> Unit)
}