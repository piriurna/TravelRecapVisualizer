package com.piriurna.data.remote

import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

object HandleApi {

    suspend fun <T> safeApiCall(callFunction: suspend () -> T): T {
        return try {

            val apiResponse: T = callFunction.invoke()

            apiResponse
        }
        catch (ex: Exception){
            when(ex){
                is HttpException -> {
                    throw AppException.NetworkException(code = ex.code(), message = ex.message())
                }
                is UnknownHostException -> throw AppException.NoInternetException

                is IOException -> throw AppException.NetworkException(code = ErrorType.IO.code, message = ex.message)

                else -> throw ex

            }
        }
    }
}