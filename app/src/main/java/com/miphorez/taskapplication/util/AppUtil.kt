package com.miphorez.taskapplication.util

//LCE -> Loading/Content/Error
sealed class LCE<out T> {
    data class Success<T>(val data: T) : LCE<T>()
    data class Error<T>(val message: String) : LCE<T>() {
        constructor(t: Throwable) : this(t.message ?: "")
    }
}
