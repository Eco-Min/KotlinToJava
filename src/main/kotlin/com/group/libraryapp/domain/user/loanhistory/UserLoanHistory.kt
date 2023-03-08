package com.group.libraryapp.domain.user.loanhistory

import com.group.libraryapp.domain.user.User
import javax.persistence.*

@Entity
class UserLoanHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @ManyToOne
    private var user: User? = null
    var bookName: String? = null
    private var isReturn = false

    constructor()
    constructor(user: User?, bookName: String?, isReturn: Boolean) {
        this.user = user
        this.bookName = bookName
        this.isReturn = isReturn
    }

    fun doReturn() {
        isReturn = true
    }
}