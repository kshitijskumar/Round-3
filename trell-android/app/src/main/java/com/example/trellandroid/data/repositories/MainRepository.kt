package com.example.trellandroid.data.repositories

import com.example.trellandroid.data.responses.CommentResponse
import com.example.trellandroid.data.responses.UserResponse
import com.example.trellandroid.data.responses.VlogResponse
import com.example.trellandroid.utils.Result

interface MainRepository {
    //actual functions which will call api
    /**
     * fetches all the vlogs calling api
     */
    suspend fun fetchAllVlogs() : Result<List<VlogResponse>>

    /**
     * call the remote data source to notify the interest score
     * @param vlogId vlogId of the vlog
     * @param score score for the interest.
     * Possible score values: WATCH_SCORE, LIKE_SCORE, COMMENT_SCORE, SAVE_SCORE, PROFILE_SCORE
     */
    suspend fun setInterestScore(vlogId: Long, score: Int = 1) : Result<Unit>

    //demo functions that won't be calling any api in MVP but will be required in actual app
    suspend fun fetchAllComments(vlogId: Long) : Result<List<CommentResponse>>
    suspend fun getProfile(userId: Long) : Result<UserResponse>
    suspend fun getVlogDetails(vlogId: Long) : Result<VlogResponse>


}