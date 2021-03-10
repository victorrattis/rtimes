package com.study.vhra.rtimes.utils

data class Resource<Data, Error> constructor(
    val data: Data? = null,
    val error: Error? = null,
    val loading: Boolean = false
) {
    fun isSuccess(): Boolean = error == null && data != null
    fun isLoading(): Boolean = loading
    fun isEmptyData(): Boolean = data == null
}