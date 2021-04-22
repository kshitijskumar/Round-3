package com.example.trellandroid.data.responses

data class UserResponse(
    val userId: Long? = null,
    val username: String? = null,
    val name: String? = null,
    val totalPosts: Int = 0,
    val followers: Int = 0,
    val following: Int = 0,
    val userImg: String? = null
)
