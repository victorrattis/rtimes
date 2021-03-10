package com.study.vhra.rtimes.injection

import com.study.vhra.rtimes.AndroidLog
import com.study.vhra.rtimes.IListTimRegisterUseCase
import com.study.vhra.rtimes.IRegisterTimeForCurrentDateUseCase
import com.study.vhra.rtimes.UseCaseRunner
import com.study.vhra.rtimes.data.TimeStorageLocal
import com.study.vhra.rtimes.domain.CalendarManager
import com.study.vhra.rtimes.domain.ILog
import com.study.vhra.rtimes.domain.storage.TimeRegisterStorage
import com.study.vhra.rtimes.domain.storage.TimeStorage
import com.study.vhra.rtimes.domain.usecase.ListTimRegisterUseCase
import com.study.vhra.rtimes.domain.usecase.RegisterTimeForCurrentDateUseCase
import com.study.vhra.rtimes.domain.validate.TimeValidator
import com.study.vhra.rtimes.manager.CalendarManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    private val userCaseExecutors: ExecutorService by lazy { Executors.newSingleThreadExecutor() }

    @Provides
    fun provideLog(): ILog = AndroidLog()

    @Provides
    fun providerCalendarManager(): CalendarManager = CalendarManagerImpl()

    @Provides
    fun provideTimeValidator(): TimeValidator = TimeValidator()

    @Provides
    fun provideTimeStorageLocal(): TimeStorage = TimeStorageLocal()

    @Provides
    fun provideTimeRegisterStorage(): TimeRegisterStorage = TimeStorageLocal()

    @Provides
    fun provideRegisterTimeForCurrentDate(
        calendarManager: CalendarManager,
        validator: TimeValidator,
        timeStorage: TimeStorage,
        log: ILog
    ): IRegisterTimeForCurrentDateUseCase = UseCaseRunner(
        RegisterTimeForCurrentDateUseCase(calendarManager, validator, timeStorage, log),
        userCaseExecutors
    )

    @Provides
    fun providerListTimRegisterUseCase(
        timeRegisterStorage: TimeRegisterStorage,
        log: ILog
    ): IListTimRegisterUseCase = UseCaseRunner(
        ListTimRegisterUseCase(timeRegisterStorage, log),
        userCaseExecutors
    )
}