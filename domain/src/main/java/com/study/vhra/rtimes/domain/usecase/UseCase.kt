package com.study.vhra.rtimes.domain.usecase

interface UseCase<Input, Output> {
    fun run(input: Input, callback: (Output) -> Unit)
}