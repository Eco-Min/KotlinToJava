package com.group.libraryapp.domain.user

import com.group.libraryapp.domain.user.QUser.user
import com.group.libraryapp.domain.user.loanhistory.QUserLoanHistory.userLoanHistory
import com.group.libraryapp.dto.user.response.BookHistoryResponse
import com.group.libraryapp.dto.user.response.UserLoanResponse
import com.querydsl.core.group.GroupBy.groupBy
import com.querydsl.core.group.GroupBy.list
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory

class UserRepositoryCustomImpl(private val queryFactory: JPAQueryFactory) : UserRepositoryCustom {
    override fun findAllUserHistory(): List<UserLoanResponse> {
        return queryFactory.from(user)
            .join(user.userLoanHistory, userLoanHistory)
            .transform(
                groupBy(user.id).list(
                    Projections.constructor(
                        UserLoanResponse::class.java,
                        user.name,
                        list(
                            Projections.constructor(
                                BookHistoryResponse::class.java,
                                userLoanHistory.bookName,
                                userLoanHistory.isReturn
                            )
                        )
                    )
                )
            )
    }

    override fun test(): List<BookHistoryResponse> {
        return queryFactory.select(
            Projections.constructor(
                BookHistoryResponse::class.java,
                userLoanHistory.bookName,
                userLoanHistory.isReturn
            )
        ).from(userLoanHistory)
            .fetch()
    }
}