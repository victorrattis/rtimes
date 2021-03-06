package com.study.vhra.rtimes.domain.validate

import com.study.vhra.rtimes.domain.model.HourInfo

class TimeValidator {
    fun validate(hourInfo: HourInfo): Boolean =
        hourInfo.hourOfDay in 0..24 && hourInfo.minute in 0..60
}