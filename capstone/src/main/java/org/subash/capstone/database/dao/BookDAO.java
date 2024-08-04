package org.subash.capstone.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.subash.capstone.database.entity.Book;

public interface BookDAO extends JpaRepository<Book, Long> {

    Book findBookByBookId(Integer bookId);
}
