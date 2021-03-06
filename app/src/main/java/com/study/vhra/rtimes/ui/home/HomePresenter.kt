package com.study.vhra.rtimes.ui.home

import com.study.vhra.rtimes.RegisterTimeForCurrentDateUseCase
import com.study.vhra.rtimes.domain.model.HourInfo
import com.study.vhra.rtimes.domain.usecase.RegisterTimeForCurrentDate

class HomePresenter constructor(
    private val view: HomeContractor.View,
    private val registerTimeForCurrentDate: RegisterTimeForCurrentDateUseCase
) : HomeContractor.Presenter {
    override fun onRegisterTimeButton() {
        view.showRegisterTimeDialog()
    }

    override fun onTimeSetOfDay(hourOfDay: Int, minute: Int) {
        registerTimeForCurrentDate.run(
            RegisterTimeForCurrentDate.Input(HourInfo(hourOfDay, minute))
        ) { result ->
            when(result.isSavedSuccessfully) {
                true -> view.showTimeRegisteredSuccessfully()
                else -> view.showErrorForRegisteringTime()
            }
        }
    }
}