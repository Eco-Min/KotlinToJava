package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.book.JBook;
import com.group.libraryapp.domain.user.loanhistory.JUserLoanHistory;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class JUser {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotNull
  private String name;

  private Integer age;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private final List<JUserLoanHistory> userLoanHistories = new ArrayList<>();

  public JUser() {

  }

  public JUser(String name, Integer age) {
    if (name.isEmpty()) {
      throw new IllegalArgumentException("이름은 비어 있을 수 없습니다");
    }
    this.name = name;
    this.age = age;
  }

  public void updateName(String name) {
    this.name = name;
  }

  public void loanBook(JBook book) {
    this.userLoanHistories.add(new JUserLoanHistory(this, book.getName(), false));
  }

  public void returnBook(String bookName) {
    JUserLoanHistory targetHistory = this.userLoanHistories.stream()
        .filter(history -> history.getBookName().equals(bookName))
        .findFirst()
        .orElseThrow();
    targetHistory.doReturn();
  }

  public String getName() {
    return name;
  }

  public Integer getAge() {
    return age;
  }

  public Long getId() {
    return id;
  }

}
