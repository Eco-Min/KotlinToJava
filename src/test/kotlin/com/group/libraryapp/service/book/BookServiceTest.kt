package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceTest(
    @Autowired
    private val bookService: BookService,
    @Autowired
    private val bookRepository: BookRepository,
    @Autowired
    private val userRepository: UserRepository,
    @Autowired
    private val userLoanHistoryRepository: UserLoanHistoryRepository
) {

    @AfterEach
    fun clean(){
        bookRepository.deleteAll()
    }

    @Test
    @DisplayName("책 저장")
    fun saveBookTest() {
        val bookRequest = BookRequest("Kotlin")

        bookService.saveBook(bookRequest)

        val book = bookRepository.findByName(bookRequest.name)

        assertThat(book.get().name).isEqualTo(bookRequest.name)
    }

    @Test
    @DisplayName("책 대출")
    fun loanBookTest() {
        val bookRequest = BookRequest("Kotlin")
        bookService.saveBook(bookRequest)

        val user = User("user1", 20)
        userRepository.save(user)

        val bookLoanRequest = BookLoanRequest("user1", "Kotlin")

        bookService.loanBook(bookLoanRequest)

        val userLoanHistory = userLoanHistoryRepository.findByBookNameAndIsReturn(bookLoanRequest.bookName, false)

        assertThat(userLoanHistory.bookName).isEqualTo(bookLoanRequest.bookName)
    }
}