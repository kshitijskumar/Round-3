package com.example.trellandroid.utils

import com.example.trellandroid.data.responses.CommentResponse
import com.example.trellandroid.data.responses.UserResponse
import com.example.trellandroid.data.responses.VlogResponse
import com.example.trellandroid.utils.Constants.DUMMY_USER_IMG_URL

object DummyResponses {

    val dummyListOfComments = listOf<CommentResponse>()

    val dummyUserResponse = UserResponse(
        userId = 0L,
        "mellonspacex",
        "Mellon Husk",
        22,
        4000,
        400,
        DUMMY_USER_IMG_URL
    )

    val dummyVlogResponse = VlogResponse(creatorId = 0L)

    val  dummyUserPosts = listOf<VlogResponse>(
        VlogResponse(creatorName = "Mellon Husk", vlogId = 0L, creatorId = 0L),
        VlogResponse(creatorName = "Mellon Husk", vlogId = 1L, creatorId = 0L),
        VlogResponse(creatorName = "Mellon Husk", vlogId = 2L, creatorId = 0L),
        VlogResponse(creatorName = "Mellon Husk", vlogId = 3L, creatorId = 0L),
        VlogResponse(creatorName = "Mellon Husk", vlogId = 4L, creatorId = 0L)
    )
}