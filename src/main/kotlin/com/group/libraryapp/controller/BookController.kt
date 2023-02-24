package com.group.libraryapp.controller

import com.group.libraryapp.dto.book.request.JBookRequest
import com.group.libraryapp.service.book.BookService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController private constructor(val bookService: BookService) {

    @PostMapping("/book")
    fun saveBook(@RequestBody request: JBookRequest?) {
        bookService.saveBook(request)
    }

}