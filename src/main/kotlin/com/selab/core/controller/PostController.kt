package com.selab.core.controller

import com.selab.core.dto.PostCreateRequest
import com.selab.core.service.PostService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class PostController(
    private val postService: PostService
) {
    @PostMapping(value = ["/api/v1/boards/{boardId}/posts"])
    fun create(
        @PathVariable boardId: Long,
        @RequestBody request: PostCreateRequest
    ) = postService.create(boardId, request)
}
