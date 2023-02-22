package com.group.libraryapp.controller

import com.group.libraryapp.service.BookService
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController private constructor(val bookService: BookService) {



}