package com.study.vhra.rtimes

import com.study.vhra.rtimes.domain.usecase.ListTimRegisterUseCase
import com.study.vhra.rtimes.domain.usecase.RegisterTimeForCurrentDateUseCase
import com.study.vhra.rtimes.domain.usecase.UseCase

typealias IRegisterTimeForCurrentDateUseCase =
        UseCase<RegisterTimeForCurrentDateUseCase.Input, RegisterTimeForCurrentDateUseCase.Output>

typealias IListTimRegisterUseCase =
        UseCase<ListTimRegisterUseCase.Input, ListTimRegisterUseCase.Output>
