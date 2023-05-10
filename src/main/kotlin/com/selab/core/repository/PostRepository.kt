package com.selab.core.repository

import com.selab.core.entity.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface PostRepository : JpaRepository<Post, Long> {
    @Transactional(readOnly = true)
    fun findAllByBoardIdOrderByCreatedByDesc(boardId: Long, pageable: Pageable): Page<Post>
}
