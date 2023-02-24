package com.group.libraryapp.domain.user

import javax.persistence.*
import kotlin.properties.Delegates
import
@Entity
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(nullable = false)
    var name: String = ""

    var age : Int = 0;
}