package com.group.libraryapp.domain.user

import com.group.libraryapp.dto.user.response.BookHistoryResponse
import com.group.libraryapp.dto.user.response.UserLoanResponse

interface UserRepositoryCustom {
    fun findAllUserHistory() : List<UserLoanResponse>

    fun test(): List<BookHistoryResponse>
}