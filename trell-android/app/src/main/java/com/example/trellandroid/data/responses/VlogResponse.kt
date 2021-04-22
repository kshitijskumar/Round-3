package com.example.trellandroid.data.responses

data class VlogResponse(
    val vlogId: Long? = null,
    val title: String = "Unnamed",
    val tags: List<String> = listOf(),
    val creatorName: String = "Unnamed",
    val creatorImgUrl: String? = null,
    val creatorId: Double? = null
)