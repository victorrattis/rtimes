package com.study.vhra.rtimes.ui.home

interface HomeContractor {
    interface View {
        fun showRegisterTimeDialog()
        fun showTimeRegisteredSuccessfully()
        fun showErrorForRegisteringTime()
    }

    interface Presenter {
        fun onRegisterTimeButton()
        fun onTimeSetOfDay(hourOfDay: Int, minute: Int)
    }
}