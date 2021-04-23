package com.example.trellandroid.data.responses

import com.example.trellandroid.utils.Constants.DUMMY_USER_IMG_URL

data class VlogResponse(
    val vlogId: Long? = null,
    val title: String = "Unnamed",
    val tags: List<String> = listOf(),
    val creatorName: String = "Unnamed",
    val creatorImgUrl: String = DUMMY_USER_IMG_URL,
    val creatorId: Long? = null,
    val vlogUrl: String? = null
)