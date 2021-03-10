package com.study.vhra.rtimes.ui.home

import com.study.vhra.rtimes.IListTimRegisterUseCase
import com.study.vhra.rtimes.IRegisterTimeForCurrentDateUseCase
import com.study.vhra.rtimes.domain.model.HourInfo
import com.study.vhra.rtimes.domain.usecase.ListTimRegisterUseCase
import com.study.vhra.rtimes.domain.usecase.RegisterTimeForCurrentDateUseCase

class HomePresenter constructor(
    private val view: HomeContractor.View,
    private val registerTimeForCurrentDate: IRegisterTimeForCurrentDateUseCase,
    private val listTimRegisterUseCase: IListTimRegisterUseCase
) : HomeContractor.Presenter {
    override fun onRegisterTimeButton() {
        view.showRegisterTimeDialog()
    }

    override fun onTimeSetOfDay(hourOfDay: Int, minute: Int) {
        registerTimeForCurrentDate.run(
            RegisterTimeForCurrentDateUseCase.Input(HourInfo(hourOfDay, minute))
        ) { result ->
            when(result.isSavedSuccessfully) {
                true -> view.showTimeRegisteredSuccessfully()
                else -> view.showErrorForRegisteringTime()
            }
        }
    }

    private fun getTimeRegisters() {
        view.showLoading()
        listTimRegisterUseCase.run(ListTimRegisterUseCase.Input()) { output ->
            view.hideLoading()
            view.showTimeRegisters(output.timeRegisters)
        }
    }
}