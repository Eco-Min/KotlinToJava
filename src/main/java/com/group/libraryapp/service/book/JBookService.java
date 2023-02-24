package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.JBook;
import com.group.libraryapp.domain.book.JBookRepository;
import com.group.libraryapp.domain.user.JUser;
import com.group.libraryapp.domain.user.JUserRepository;
import com.group.libraryapp.domain.user.loanhistory.JUserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.JBookLoanRequest;
import com.group.libraryapp.dto.book.request.JBookRequest;
import com.group.libraryapp.dto.book.request.JBookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JBookService {

  private final JBookRepository jbookRepository;
  private final JUserRepository jUserRepository;
  private final JUserLoanHistoryRepository JUserLoanHistoryRepository;

  public JBookService(
      JBookRepository jbookRepository,
      JUserRepository jUserRepository,
      JUserLoanHistoryRepository JUserLoanHistoryRepository
  ) {
    this.jbookRepository = jbookRepository;
    this.jUserRepository = jUserRepository;
    this.JUserLoanHistoryRepository = JUserLoanHistoryRepository;
  }

  @Transactional
  public void saveBook(JBookRequest request) {
    JBook newBook = new JBook(request.getName());
    jbookRepository.save(newBook);
  }

  @Transactional
  public void loanBook(JBookLoanRequest request) {
    JBook book = jbookRepository.findByName(request.getBookName()).orElseThrow(IllegalArgumentException::new);
    if (JUserLoanHistoryRepository.findByBookNameAndIsReturn(request.getBookName(), false) != null) {
      throw new IllegalArgumentException("이미 대출되어 있는 책입니다");
    }

    JUser user = jUserRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);
    user.loanBook(book);
  }

  @Transactional
  public void returnBook(JBookReturnRequest request) {
    JUser user = jUserRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);
    user.returnBook(request.getBookName());
  }

}
