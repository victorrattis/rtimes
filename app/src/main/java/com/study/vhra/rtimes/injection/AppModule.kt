package com.study.vhra.rtimes.injection

import com.study.vhra.rtimes.AndroidLog
import com.study.vhra.rtimes.data.TimeStorageLocal
import com.study.vhra.rtimes.domain.CalendarManager
import com.study.vhra.rtimes.domain.ILog
import com.study.vhra.rtimes.domain.storage.TimeStorage
import com.study.vhra.rtimes.domain.usecase.RegisterTimeForCurrentDate
import com.study.vhra.rtimes.domain.usecase.UseCase
import com.study.vhra.rtimes.domain.validate.TimeValidator
import com.study.vhra.rtimes.manager.CalendarManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Provides
    fun provideLog(): ILog = AndroidLog()

    @Provides
    fun providerCalendarManager(): CalendarManager = CalendarManagerImpl()

    @Provides
    fun provideTimeValidator(): TimeValidator = TimeValidator()

    @Provides
    fun provideTimeStorageLocal(): TimeStorage = TimeStorageLocal()

    @Provides
    fun provideRegisterTimeForCurrentDate(
        calendarManager: CalendarManager,
        validator: TimeValidator,
        timeStorage: TimeStorage,
        log: ILog
    ): UseCase<RegisterTimeForCurrentDate.Input, RegisterTimeForCurrentDate.Output> =
        RegisterTimeForCurrentDate(calendarManager, validator, timeStorage, log)
}