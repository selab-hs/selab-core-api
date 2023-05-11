package com.selab.core.dto

import java.time.ZonedDateTime

data class PostCreateResponse(
    val id: Long,
    val boardId: Long,
    val title: String,
    val content: String,
    val createdBy: Long,
    val createdAt: ZonedDateTime,
    val modifiedAt: ZonedDateTime
)
