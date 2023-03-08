package com.group.libraryapp.domain.user

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import javax.persistence.*

@Entity
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(nullable = false)
    var name: String = ""

    var age : Int = 0;

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    private val userLoanHistories: MutableList<UserLoanHistory> = ArrayList()

    fun loanBook(book: Book) {
        userLoanHistories.add(UserLoanHistory(this, book.name, false))
    }

    fun returnBook(bookName: String) {
        var targetHistory = userLoanHistories.stream()
            .filter { history: UserLoanHistory -> history.bookName == bookName }
            .findFirst()
            .orElseThrow();
        targetHistory.doReturn()
    }
}