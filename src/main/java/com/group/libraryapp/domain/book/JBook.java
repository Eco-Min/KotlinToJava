package com.group.libraryapp.domain.book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class JBook {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  public JBook() {

  }

  public JBook(String name) {
    if (name.isEmpty()) {
      throw new IllegalArgumentException("이름은 비어있을 수 없습니다.");
    }
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
