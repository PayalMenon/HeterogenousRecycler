package com.example.recycler.util

import retrofit2.Response

sealed class NetworkResponse<out T> {

    data class Success<T>(val body: T) : NetworkResponse<T>()
    data class Failure<T>(val errorResponse: Response<T>? = null) : NetworkResponse<T>()
}