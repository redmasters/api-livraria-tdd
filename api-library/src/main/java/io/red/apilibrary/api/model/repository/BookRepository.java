package io.red.apilibrary.api.model.repository;

import io.red.apilibrary.api.entity.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
