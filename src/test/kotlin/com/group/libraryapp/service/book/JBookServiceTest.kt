package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.user.JUser
import com.group.libraryapp.domain.user.JUserRepository
import com.group.libraryapp.domain.user.loanhistory.JUserLoanHistoryRepository
import com.group.libraryapp.dto.book.request.JBookLoanRequest
import com.group.libraryapp.dto.book.request.JBookRequest
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JBookServiceTest(
    @Autowired
    private val bookService: JBookService,
    @Autowired
    private val bookRepository: BookRepository,
    @Autowired
    private val userRepository: JUserRepository,
    @Autowired
    private val userLoanHistoryRepository: JUserLoanHistoryRepository
) {

    @AfterEach
    fun clean(){
        bookRepository.deleteAll()
    }

    @Test
    @DisplayName("책 저장")
    fun saveBookTest() {
        val bookRequest = JBookRequest("Kotlin")

        bookService.saveBook(bookRequest)

        val book = bookRepository.findByName(bookRequest.name)

        assertThat(book.get().name).isEqualTo(bookRequest.name)
    }

    @Test
    @DisplayName("책 대출")
    fun loanBookTest() {
        val bookRequest = JBookRequest("Kotlin")
        bookService.saveBook(bookRequest)

        val user = JUser("user1", 20)
        userRepository.save(user)

        val bookLoanRequest =
            JBookLoanRequest("user1", "Kotlin")

        bookService.loanBook(bookLoanRequest)

        val userLoanHistory = userLoanHistoryRepository.findByBookNameAndIsReturn(bookLoanRequest.bookName, false)

        assertThat(userLoanHistory.bookName).isEqualTo(bookLoanRequest.bookName)
    }
}