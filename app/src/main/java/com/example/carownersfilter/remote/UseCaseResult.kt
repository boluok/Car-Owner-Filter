package com.example.carownersfilter.remote

sealed class UseCaseResult<out T : Any> {
    class Success<out T : Any>(val data: T) : UseCaseResult<T>()
    class Error(val exception: Throwable) : UseCaseResult<Nothing>()
    class Failed(val errorMessage:String): UseCaseResult<Nothing>()
    class FailedData<out T : Any>(val data: T): UseCaseResult<T>()
    class FailedAPI<out T : Any>(val data: T): UseCaseResult<T>()
}