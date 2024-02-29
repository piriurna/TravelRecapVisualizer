package com.piriurna.data.remote

sealed class AppException(val code : Int?, message : String?) : Exception(message) {

    class CategoryNotFoundException(message: String) : AppException(code = ErrorType.INVALID_CATEGORY.code, message = message)

    class InvalidParameterException(message: String) : AppException(code = ErrorType.INVALID_PARAMETER.code, message = message)

    class NetworkException(code : Int, message : String?) : AppException(code, message)

    object NoInternetException : AppException(code = ErrorType.NO_INTERNET.code, message = "No internet connection available")

    class TimeoutException(message : String?) : AppException(code = ErrorType.TIMEOUT.code, message = message)
}