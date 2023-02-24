package com.group.libraryapp.controller.book;

import com.group.libraryapp.dto.book.request.JBookLoanRequest;
import com.group.libraryapp.dto.book.request.JBookRequest;
import com.group.libraryapp.dto.book.request.JBookReturnRequest;
import com.group.libraryapp.service.book.JBookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JBookController {

  private final JBookService jBookService;

  public JBookController(JBookService jBookService) {
    this.jBookService = jBookService;
  }

  @PostMapping("/book")
  public void saveBook(@RequestBody JBookRequest request) {
    jBookService.saveBook(request);
  }

  @PostMapping("/book/loan")
  public void loanBook(@RequestBody JBookLoanRequest request) {
    jBookService.loanBook(request);
  }

  @PutMapping("/book/return")
  public void returnBook(@RequestBody JBookReturnRequest request) {
    jBookService.returnBook(request);
  }

}
