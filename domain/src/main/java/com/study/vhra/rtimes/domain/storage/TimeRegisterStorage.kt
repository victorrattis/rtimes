package com.study.vhra.rtimes.domain.storage

import com.study.vhra.rtimes.domain.usecase.TimeRegisterOfDayInfo

interface TimeRegisterStorage{
    fun getTimeRegisters(callback: (List<TimeRegisterOfDayInfo>) -> Unit)
}