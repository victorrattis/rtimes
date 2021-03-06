package com.study.vhra.rtimes.manager

import com.study.vhra.rtimes.domain.CalendarManager
import com.study.vhra.rtimes.domain.model.DateInfo
import java.util.*

class CalendarManagerImpl : CalendarManager {
    override fun getCurrentDate(): DateInfo {
        return Calendar.getInstance().let {
            DateInfo(
                it.get(Calendar.DAY_OF_MONTH),
                it.get(Calendar.MONTH) + 1,
                it.get(Calendar.YEAR)
            )
        }
    }
}