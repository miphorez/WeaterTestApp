package com.miphorez.taskapplication.network

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: Any?) : Result<T>()
    data class Loading(val status: Boolean) : Result<Nothing>()
    sealed class Error(val exception: Exception) : Result<Nothing>() {
        class RecoverableError(exception: Exception) : Error(exception)
        class NonRecoverableError(exception: Exception) :
            Error(exception)
    }
}