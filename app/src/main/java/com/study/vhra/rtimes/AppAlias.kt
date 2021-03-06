package com.study.vhra.rtimes

import com.study.vhra.rtimes.domain.usecase.RegisterTimeForCurrentDate
import com.study.vhra.rtimes.domain.usecase.UseCase

typealias RegisterTimeForCurrentDateUseCase =
        UseCase<RegisterTimeForCurrentDate.Input, RegisterTimeForCurrentDate.Output>
