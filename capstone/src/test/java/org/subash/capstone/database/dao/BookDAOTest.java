package org.subash.capstone.database.dao;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.subash.capstone.database.entity.Book;

import java.util.Date;

@SpringBootTest
public class BookDAOTest {


    @Autowired
    private BookDAO bookDAO;

    @Test
    public void findByBookIdTest(){
        Integer bookId = 2;
        Book book = bookDAO.findBookByBookId(bookId);
        Assertions.assertNotNull(book);
        Assertions.assertEquals(bookId, book.getBookId());

    }
    @Test
    public void findByInvalidIdTest(){
        Integer givenId = 100200;
        Book book = bookDAO.findBookByBookId(givenId);
        Assertions.assertNull(book);
    }

    @Test
    public void crudTest() {
        //Create Test
        Book book = new Book();
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setGenre("Programming");
        book.setPrice(40.00);
        book.setStock(5);
        book.setIsbn("978-0132350884");
        book.setDescription("A guide to writing clean and maintainable code.");
        book.setPublisher("Prentice Hall");
        book.setPublishedDate(new Date());
        book.setPictureURL("http://example.com/clean-code.jpg");

        Book savedBook = bookDAO.save(book);
        Assertions.assertNotNull(savedBook.getBookId());

        Book foundBook = bookDAO.findBookByBookId(savedBook.getBookId());
        Assertions.assertNotNull(foundBook);
        Assertions.assertEquals("Clean Code", foundBook.getTitle());
        Assertions.assertEquals("Robert C. Martin", foundBook.getAuthor());
        //Update Test
        savedBook.setStock(25);
        bookDAO.save(savedBook);
        Assertions.assertEquals(25, bookDAO.findBookByBookId(savedBook.getBookId()).getStock());

        //Delete Test
        bookDAO.delete(savedBook);

        Assertions.assertNull(bookDAO.findBookByBookId(savedBook.getBookId()));
    }

    @ParameterizedTest
    @CsvSource({

            "2, To Kill a Mockingbird",
            "3, 1984",
            "100, null" // assuming this ID does not exist
    })
    public void testFindBookByBookId(int bookId, String expectedTitle) {
        Book book = bookDAO.findBookByBookId(bookId);

        if ("null".equalsIgnoreCase(expectedTitle)) {
            Assertions.assertNull(book);
        } else {
            Assertions.assertNotNull(book);
            Assertions.assertEquals(expectedTitle, book.getTitle());
        }
    }
}
