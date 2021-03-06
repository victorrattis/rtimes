package com.study.vhra.rtimes

import android.os.Handler
import android.os.Looper
import com.study.vhra.rtimes.domain.usecase.UseCase
import java.util.concurrent.Executor

class UseCaseRunner<Input, Output> constructor(
    private val useCase: UseCase<Input, Output>,
    private val executor: Executor
): UseCase<Input, Output> {
    override fun run(input: Input, callback: (Output) -> Unit) {
        val handler: Handler? = getHandler()
        executor.execute {
            useCase.run(input) {
                handler?.post { callback(it) } ?: callback(it)
            }
        }
    }

    private fun getHandler(): Handler? {
        return Looper.myLooper()?.let { Handler(it) } ?: null
    }
}