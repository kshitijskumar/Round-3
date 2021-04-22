package com.example.trellandroid.data.repositories

import com.example.trellandroid.data.responses.CommentResponse
import com.example.trellandroid.data.responses.UserResponse
import com.example.trellandroid.data.responses.VlogResponse
import com.example.trellandroid.utils.Result

interface MainRepository {
    //actual functions which will call api
    suspend fun fetchAllVlogs() : Result<List<VlogResponse>>
    suspend fun setInterestScore(vlogId: Double, score: Int = 1) : Result<Unit>

    //demo functions that won't be calling any api in MVP but will be required in actual app
    suspend fun fetchAllComments(vlogId: Double) : Result<List<CommentResponse>>
    suspend fun getProfile(userId: Double) : Result<UserResponse>
    suspend fun getVlogDetails(vlogId: Long) : Result<VlogResponse>


}