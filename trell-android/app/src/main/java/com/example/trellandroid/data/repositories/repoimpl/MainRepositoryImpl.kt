package com.example.trellandroid.data.repositories.repoimpl

import com.example.trellandroid.data.api.ApiService
import com.example.trellandroid.data.repositories.BaseRepository
import com.example.trellandroid.data.repositories.MainRepository
import com.example.trellandroid.data.responses.CommentResponse
import com.example.trellandroid.data.responses.UserResponse
import com.example.trellandroid.data.responses.VlogResponse
import com.example.trellandroid.utils.DummyResponses
import com.example.trellandroid.utils.Result
import kotlinx.coroutines.delay

class MainRepositoryImpl(
    private val api : ApiService = ApiService.getApiService()
) : BaseRepository(), MainRepository {

    //these two functions actually call an api to perform task
    override suspend fun fetchAllVlogs(): Result<List<VlogResponse>> = safeApiCall {
        api.getAllVlogs()
    }

    override suspend fun setInterestScore(vlogId: Double, score: Int) = safeApiCall {
        api.setInterestScore(vlogId, score)
    }

    //these two functions will have a delay of 2 sec and then return dummy result
    override suspend fun fetchAllComments(vlogId: Double): Result<List<CommentResponse>> {
        delay(2000L)
        return Result.Success(DummyResponses.dummyListOfComments)
    }

    override suspend fun getProfile(userId: Double): Result<UserResponse> {
        delay(2000L)
        return Result.Success(DummyResponses.dummyUserResponse)
    }

    override suspend fun getVlogDetails(vlogId: Long): Result<VlogResponse> {
        delay(2000L)
        return Result.Success(DummyResponses.dummyVlogResponse)
    }
}