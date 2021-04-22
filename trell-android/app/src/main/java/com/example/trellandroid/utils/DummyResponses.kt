package com.example.trellandroid.utils

import com.example.trellandroid.data.responses.CommentResponse
import com.example.trellandroid.data.responses.UserResponse
import com.example.trellandroid.data.responses.VlogResponse

object DummyResponses {

    val dummyListOfComments = listOf<CommentResponse>()

    val dummyUserResponse = UserResponse()

    val  dummyPosts = listOf<VlogResponse>(
        VlogResponse(creatorName = "Title1", vlogId = 0L),
        VlogResponse(creatorName = "Title2", vlogId = 1L),
        VlogResponse(creatorName = "Title3", vlogId = 2L),
        VlogResponse(creatorName = "Title4", vlogId = 3L),
        VlogResponse(creatorName = "Title5", vlogId = 4L),
        VlogResponse(creatorName = "Title6", vlogId = 5L),
        VlogResponse(creatorName = "Title7", vlogId = 6L)
    )
}