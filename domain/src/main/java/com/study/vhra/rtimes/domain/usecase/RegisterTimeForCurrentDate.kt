package com.study.vhra.rtimes.domain.usecase

import com.study.vhra.rtimes.domain.CalendarManager
import com.study.vhra.rtimes.domain.ILog
import com.study.vhra.rtimes.domain.storage.TimeStorage
import com.study.vhra.rtimes.domain.validate.TimeValidator
import com.study.vhra.rtimes.domain.model.HourInfo

class RegisterTimeForCurrentDate constructor(
    private val calendarManager: CalendarManager,
    private val validator: TimeValidator,
    private val timeStorage: TimeStorage,
    private val log: ILog?
): UseCase<RegisterTimeForCurrentDate.Input, RegisterTimeForCurrentDate.Output> {
    companion object {
        const val TAG: String = "RegisterTimeForCurrentDate"
    }

    override fun run(input: Input, callback: (Output) -> Unit) {
        log?.debug(TAG, "Run register time for current date use case")
        if (validator.validate(input.hour)) {
            val currentDate = calendarManager.getCurrentDate()
            log?.debug(TAG, "Save current date and hour: $currentDate, ${input.hour}")
            timeStorage.save(currentDate, input.hour) { isSaved ->
                log?.debug(TAG, "Saving result: $isSaved")
                callback(Output(isSaved))
            }
        } else {
            log?.debug(TAG, "Hour data is invalidated!")
            callback(Output(false))
        }
    }

    data class Input(
        val hour: HourInfo
    )

    data class Output(
        val isSavedSuccessfully: Boolean
    )
}