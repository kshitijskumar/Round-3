package com.example.trellandroid.data.responses

data class CommentResponse(
    val name: String = "Unnamed",
    val userImg: String? = null,
    val comment: String? = null,
    val userId: Long? = null,
    val vlogId: Long? = null
)