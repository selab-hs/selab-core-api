package com.selab.core.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "post")
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    val title: String,

    @Column(columnDefinition = "MEDIUMTEXT")
    val content: String,

    @Column(name = "created_by")
    val createdBy: Long
) : BaseEntity()
