package com.study.vhra.rtimes.domain.usecase

import com.study.vhra.rtimes.domain.ILog
import com.study.vhra.rtimes.domain.model.DateInfo
import com.study.vhra.rtimes.domain.model.HourInfo
import com.study.vhra.rtimes.domain.storage.TimeRegisterStorage

class ListTimRegisterUseCase constructor(
    private val timeRegisterStorage: TimeRegisterStorage,
    private val log: ILog
): UseCase<ListTimRegisterUseCase.Input, ListTimRegisterUseCase.Output> {
    companion object {
        const val TAG = "ListTimRegisterUseCase"
    }

    override fun run(input: Input, callback: (Output) -> Unit) {
        log.debug(TAG, "Run ListTimRegisterUseCase")
        timeRegisterStorage.getTimeRegisters { timeRegisters ->
            log.debug(TAG, "Get time registers: ${timeRegisters.count()}")
            callback(Output(timeRegisters))
        }
    }

    data class Input(val filter: String = "")
    data class Output(val timeRegisters: List<TimeRegisterOfDayInfo>)
}

data class TimeRegisterOfDayInfo(
    val date: DateInfo,
    val hour: List<HourInfo>
)