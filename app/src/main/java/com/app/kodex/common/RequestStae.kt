package com.app.kodex.common

data class RequestState<T>(
    val isLoading : Boolean = false,
    val isSuccess : Boolean = false,
    val result : T? = null,
    val isError : Boolean = false
)