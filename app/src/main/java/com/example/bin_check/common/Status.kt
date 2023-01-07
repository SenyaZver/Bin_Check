package com.example.bin_check.common

import com.example.bin_check.common.error_type.ErrorType

sealed class Status<T>(val data: T? = null, val errorType: ErrorType? = null) {
    class Success<T>(data: T) : Status<T>(data)
    class Error<T>(errorType: ErrorType, data: T? = null) : Status<T>(data, errorType)
    class Loading<T>(data: T? = null) : Status<T>(data)
}