package com.study.vhra.rtimes.injection

import android.app.Activity
import com.study.vhra.rtimes.RegisterTimeForCurrentDateUseCase
import com.study.vhra.rtimes.ui.home.HomeActivity
import com.study.vhra.rtimes.ui.home.HomeContractor
import com.study.vhra.rtimes.ui.home.HomePresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object HomeModule {
    @Provides
    fun provideView(activity: Activity): HomeContractor.View = activity as HomeActivity

    @Provides
    fun provideHomePresenter(
        view: HomeContractor.View,
        registerTimeForCurrentDate: RegisterTimeForCurrentDateUseCase
    ): HomeContractor.Presenter = HomePresenter(view, registerTimeForCurrentDate)
}