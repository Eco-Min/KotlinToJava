package com.group.libraryapp.domain.book

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String
) {

    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("이름이 비어있을수 없습니다.")
        }
    }
}