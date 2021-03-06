package com.study.vhra.rtimes.domain

import com.study.vhra.rtimes.domain.model.DateInfo

interface CalendarManager {
    fun getCurrentDate(): DateInfo
}