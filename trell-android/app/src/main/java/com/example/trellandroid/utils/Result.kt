package com.example.trellandroid.utils

sealed class Result<out T> {
    data class Success<T>(val data : T) : Result<T>()
    data class Error(val errorMsg: String) : Result<Nothing>()
    object Loading : Result<Nothing>()
}
