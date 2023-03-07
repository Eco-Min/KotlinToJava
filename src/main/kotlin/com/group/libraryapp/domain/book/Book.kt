package com.group.libraryapp.domain.book

import javax.persistence.*

@Entity
class Book(
    @Column(nullable = false)
    val name: String,

    @Column
    @Enumerated(EnumType.STRING)
    val type: BookType,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

){
    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("이름이 비어 있을 수 없습니다.")
        }
    }
}