package org.subash.capstone.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.subash.capstone.database.entity.Book;

import java.util.List;

public interface BookDAO extends JpaRepository<Book, Long> {

    Book findBookByBookId(Integer bookId);
    Book findBookByTitle(String title);
    Book findBookByIsbn(String isbn);

     @Query(value = "SELECT * FROM books " +
             "WHERE LOWER(title) LIKE LOWER(CONCAT('%', :searchTerm, '%'))" +
             " OR LOWER(genre) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
             "OR LOWER(isbn) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
             "OR LOWER(author) LIKE LOWER(CONCAT('%', :searchTerm, '%'))", nativeQuery = true)

    List<Book> searchBooks( String searchTerm);

}
