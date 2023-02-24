package com.group.libraryapp.domain.user.loanhistory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JUserLoanHistoryRepository extends JpaRepository<JUserLoanHistory, Long> {

  JUserLoanHistory findByBookNameAndIsReturn(String bookName, boolean isReturn);

}
