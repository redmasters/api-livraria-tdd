package io.red.apilibrary.model.repository;

import io.red.apilibrary.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
