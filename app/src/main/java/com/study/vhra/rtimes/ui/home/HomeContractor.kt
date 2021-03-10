package com.study.vhra.rtimes.ui.home

import com.study.vhra.rtimes.domain.usecase.TimeRegisterOfDayInfo

interface HomeContractor {
    interface View {
        fun showRegisterTimeDialog()
        fun showTimeRegisteredSuccessfully()
        fun showErrorForRegisteringTime()
        fun showTimeRegisters(timeRegisters: List<TimeRegisterOfDayInfo>)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun onRegisterTimeButton()
        fun onTimeSetOfDay(hourOfDay: Int, minute: Int)
    }
}