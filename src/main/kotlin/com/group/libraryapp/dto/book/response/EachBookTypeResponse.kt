package com.group.libraryapp.dto.book.response

import com.group.libraryapp.domain.book.BookType

class EachBookTypeResponse (
    val type: BookType,
    val count: Int
){

}