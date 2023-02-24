package com.group.libraryapp.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JBookRepository extends JpaRepository<JBook, Long> {

  Optional<JBook> findByName(String bookName);

}
