package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.book.BookType
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.dto.book.response.EachBookTypeResponse
import com.group.libraryapp.util.fail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService @Autowired constructor(
    val bookRepository: BookRepository,
    val userRepository: UserRepository,
    val userLoanHistoryRepository: UserLoanHistoryRepository
){

    @Transactional
    fun saveBook(request: BookRequest) {
        val newBook = Book(request.name, BookType.valueOf(request.type))
        bookRepository.save(newBook)
    }

    @Transactional
    fun loanBook(request: BookLoanRequest) {
        val book = bookRepository.findByName(request.bookName) ?: fail()
        if (userLoanHistoryRepository.findByBookNameAndIsReturn(request.bookName, false) != null) {
            throw IllegalArgumentException("대출이 되어있는 책입니다.")
        }
        val user = userRepository.findByName(request.userName) ?: fail()
        user.loanBook(book)
    }

    @Transactional
    fun returnBook(request: BookReturnRequest) {
        userRepository.findByName(request.userName)?: fail()
    }

    fun nowLoanBook(): Int {
        return userLoanHistoryRepository.findAllByIsReturn(false).size
    }

    fun countEachTypeBook(): List<EachBookTypeResponse> {
        return bookRepository.findAll().groupBy { book -> book.type }
            .map { (type, books) -> EachBookTypeResponse(type, books.size)}
    }
}