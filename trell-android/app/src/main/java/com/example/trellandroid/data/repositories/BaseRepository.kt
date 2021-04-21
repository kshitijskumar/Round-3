package com.example.trellandroid.data.repositories

import com.example.trellandroid.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception

abstract class BaseRepository {

    suspend fun <T>safeApiCall(call : suspend () -> Response<T>) : Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = call.invoke()
                if (response.isSuccessful && response.body() != null) {
                    Result.Success(response.body()!!)
                }else {
                    when(response.code()) {
                        404 -> Result.Error("What you're looking for is not available.")
                        else -> Result.Error("Something went wrong.")
                    }
                }
            }catch (e: Exception) {
                Result.Error("Please check your internet connection.")
            }
        }
    }
}