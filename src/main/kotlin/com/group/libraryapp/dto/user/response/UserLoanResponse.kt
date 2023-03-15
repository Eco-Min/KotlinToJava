package com.group.libraryapp.dto.user.response

class UserLoanResponse(
    val name : String,
    val books: List<BookHistoryResponse>
) {
}