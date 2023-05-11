package com.selab.core.service

import com.selab.core.dto.PostCreateRequest
import com.selab.core.dto.PostCreateResponse
import com.selab.core.entity.Post
import com.selab.core.repository.BoardRepository
import com.selab.core.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postRepository: PostRepository,
    private val boardRepository: BoardRepository
) {
    @Transactional
    fun create(boardId: Long, request: PostCreateRequest): PostCreateResponse {
        // board 존재하지 않음
        val board = boardRepository.findByIdOrNull(boardId)
            ?: throw RuntimeException()

        // post 생성
        val post = postRepository.save(
            Post(
                boardId = boardId,
                title = request.title,
                content = request.content,
                createdBy = 1231 // TODO
            )
        )

        return PostCreateResponse(
            id = post.id,
            boardId = board.id,
            title = post.title,
            content = post.content,
            createdBy = post.createdBy,
            createdAt = post.createdAt,
            modifiedAt = post.modifiedAt
        )
    }
}
