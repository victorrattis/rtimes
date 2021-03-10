package com.study.vhra.rtimes.injection

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.study.vhra.rtimes.IListTimRegisterUseCase
import com.study.vhra.rtimes.IRegisterTimeForCurrentDateUseCase
import com.study.vhra.rtimes.ui.home.*
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
    fun providerViewModelStoreOwner(
        activity: Activity
    ): ViewModelStoreOwner = activity as ViewModelStoreOwner

    @Provides
    fun provideHomePresenter(
        view: HomeContractor.View,
        registerTimeForCurrentDate: IRegisterTimeForCurrentDateUseCase,
        listTimRegisterUseCase: IListTimRegisterUseCase
    ): HomeContractor.Presenter = HomePresenter(
        view, registerTimeForCurrentDate, listTimRegisterUseCase)

    @Provides
    fun provideMyFactory(
        registerTimeForCurrentDate: IRegisterTimeForCurrentDateUseCase,
        listTimRegisterUseCase: IListTimRegisterUseCase
    ): HomeViewModelFactory = HomeViewModelFactory(
        registerTimeForCurrentDate,
        listTimRegisterUseCase
    )

    @Provides
    fun providerMyViewModel(owner: ViewModelStoreOwner, factory: HomeViewModelFactory): HomeViewModel =
        ViewModelProvider(owner, factory).get(HomeViewModel::class.java)

}