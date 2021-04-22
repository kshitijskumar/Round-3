package com.example.trellandroid.data.api

import com.example.trellandroid.data.responses.VlogResponse
import com.example.trellandroid.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit

interface ApiService {

    suspend fun getAllVlogs() : Response<List<VlogResponse>>

    suspend fun setInterestScore(vlogId: Double, score: Int) : Response<Unit>

    companion object {
        private val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        private val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .build()

        fun getApiService(): ApiService = retrofit.create(ApiService::class.java)
    }
}