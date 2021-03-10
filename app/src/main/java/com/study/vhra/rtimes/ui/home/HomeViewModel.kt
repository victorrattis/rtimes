package com.study.vhra.rtimes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.study.vhra.rtimes.IListTimRegisterUseCase
import com.study.vhra.rtimes.IRegisterTimeForCurrentDateUseCase
import com.study.vhra.rtimes.domain.model.HourInfo
import com.study.vhra.rtimes.domain.usecase.ListTimRegisterUseCase
import com.study.vhra.rtimes.domain.usecase.RegisterTimeForCurrentDateUseCase
import com.study.vhra.rtimes.domain.usecase.TimeRegisterOfDayInfo
import com.study.vhra.rtimes.utils.Resource
import com.study.vhra.rtimes.utils.SingleLiveEvent

typealias TimeRegistersResource = Resource<List<TimeRegisterOfDayInfo>, Int>

class HomeViewModel constructor(
    private val registerTimeForCurrentDate: IRegisterTimeForCurrentDateUseCase,
    private val listTimRegisterUseCase: IListTimRegisterUseCase
): ViewModel() {
    private val data: MutableLiveData<TimeRegistersResource> by lazy {
        MutableLiveData(TimeRegistersResource(loading = true))
    }

    private val timeSetDialogDialog: SingleLiveEvent<Unit> by lazy {
        SingleLiveEvent()
    }

    private val statusMessage: SingleLiveEvent<HomeStatus> = SingleLiveEvent()

    init {
        loadData()
    }

    fun getTimeRegisters(): LiveData<TimeRegistersResource> {
        return data
    }

    fun getTimeSetDialog(): SingleLiveEvent<Unit> = timeSetDialogDialog
    fun getStatusMessage(): SingleLiveEvent<HomeStatus> = statusMessage

    private fun loadData() {
        data.postValue(TimeRegistersResource(loading = true))
        listTimRegisterUseCase.run(ListTimRegisterUseCase.Input()) { output ->
            data.postValue(TimeRegistersResource(
                data = output.timeRegisters,
                loading = false
            ))
        }
    }

    fun onTimeSetOfDay(hourOfDay: Int, minute: Int) {
        registerTimeForCurrentDate.run(
            RegisterTimeForCurrentDateUseCase.Input(HourInfo(hourOfDay, minute))
        ) { result ->
            when(result.isSavedSuccessfully) {
                true -> showTimeRegisteredSuccessfully()
                else -> showErrorForRegisteringTime()
            }
        }
    }

    fun onRegisterTimeButtonClick() {
        timeSetDialogDialog.call()
    }

    private fun showTimeRegisteredSuccessfully() {
        statusMessage.postValue(HomeStatus.REGISTERED_SUCCESSFULLY)
    }

    private fun showErrorForRegisteringTime() {
        statusMessage.postValue(HomeStatus.ERROR_FOR_REGISTERING_TIME)
    }
}

enum class HomeStatus {
    NONE,
    REGISTERED_SUCCESSFULLY,
    ERROR_FOR_REGISTERING_TIME
}

class HomeViewModelFactory(
    private val registerTimeForCurrentDate: IRegisterTimeForCurrentDateUseCase,
    private val listTimRegisterUseCase: IListTimRegisterUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(registerTimeForCurrentDate, listTimRegisterUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

