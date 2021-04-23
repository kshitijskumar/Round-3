package com.example.trellandroid.utils

import com.example.trellandroid.data.responses.CommentResponse
import com.example.trellandroid.data.responses.UserResponse
import com.example.trellandroid.data.responses.VlogResponse
import com.example.trellandroid.utils.Constants.DUMMY_USER_IMG_URL
import com.example.trellandroid.utils.Constants.DUMMY_VLOG_IMG_URL
import com.example.trellandroid.utils.Constants.DUMMY_VLOG_IMG_URL_2

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

    val dummyVlogResponse = VlogResponse(
            creatorId = 0L,
            vlogId = 0L,
            title = "Title: Unravel-Tokyo Ghoul piano cover",
            creatorName = "Ken Kaneki",
            creatorImgUrl = DUMMY_USER_IMG_URL,
            vlogUrl = DUMMY_VLOG_IMG_URL
            )

    val  dummyUserPosts = listOf<VlogResponse>(
        VlogResponse(creatorName = "Mellon Husk", vlogId = 0L, creatorId = 0L, vlogUrl = DUMMY_VLOG_IMG_URL),
        VlogResponse(creatorName = "Mellon Husk", vlogId = 1L, creatorId = 0L, vlogUrl = DUMMY_VLOG_IMG_URL_2),
        VlogResponse(creatorName = "Mellon Husk", vlogId = 2L, creatorId = 0L, vlogUrl = DUMMY_VLOG_IMG_URL),
        VlogResponse(creatorName = "Mellon Husk", vlogId = 3L, creatorId = 0L, vlogUrl = DUMMY_VLOG_IMG_URL_2),
        VlogResponse(creatorName = "Mellon Husk", vlogId = 4L, creatorId = 0L, vlogUrl = DUMMY_VLOG_IMG_URL)
    )
}