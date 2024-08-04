package org.subash.capstone.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.subash.capstone.database.dao.BookDAO;
import org.subash.capstone.database.entity.Book;
import org.subash.capstone.database.entity.User;
import org.subash.capstone.form.CreateBookFormBean;
import org.subash.capstone.form.CreateUserFormBean;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookDAO   bookDAO;

    @GetMapping("/index/{id}")
    public ModelAndView indexPathVar(@PathVariable Integer id) {
        ModelAndView response = new ModelAndView("book/detail");
        Book book  = bookDAO.findBookByBookId(id);
        response.addObject("book",book );

        return response;
    }
    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView response = new ModelAndView("book/list");
        List<Book> books = bookDAO.findAll();
        response.addObject("books", books);
        return response;
    }

    @GetMapping("/create")
    public ModelAndView createUser() {
        ModelAndView response = new ModelAndView("book/create");
        return response;
    }
    @GetMapping ("/edit/{id}")
    public ModelAndView edit(@PathVariable Integer id) {
        ModelAndView response = new ModelAndView("book/create");
        if (id != null) {
            Book book = bookDAO.findBookByBookId(id);
            if (book!= null) {
                CreateBookFormBean form = new CreateBookFormBean();
                form.setBookId(book.getBookId());
                form.setTitle(book.getTitle());
                form.setAuthor(book.getAuthor());
                form.setGenre(book.getGenre());
                form.setPrice(book.getPrice());
                form.setStock(book.getStock());
                form.setIsbn(book.getIsbn());
                form.setDescription(book.getDescription());
                form.setPublisher(book.getPublisher());
                form.setPublishedDate(book.getPublishedDate());
                response.addObject("form",form);
            }
        }

        return response;


    }

    @PostMapping("/submit")
    public ModelAndView submit(CreateBookFormBean form, @RequestParam MultipartFile image) {
        ModelAndView response  = new ModelAndView();
        Book book = bookDAO.findBookByBookId(form.getBookId());
        if(book==null){
            book = new Book();
        }

        book.setTitle(form.getTitle());
        book.setAuthor(form.getAuthor());
        book.setGenre(form.getGenre());
        book.setDescription(form.getDescription());
        book.setPrice(form.getPrice());
        book.setStock(form.getStock());
        book.setIsbn(form.getIsbn());
        book.setPublisher(form.getPublisher());
        book.setPublishedDate(form.getPublishedDate());
        String saveFilename = "./src/main/webapp/pub/images/" + image.getOriginalFilename();


        try {
            Files.copy(image.getInputStream(), Paths.get(saveFilename), StandardCopyOption.REPLACE_EXISTING);
        } catch ( Exception e ) {
            log.error("Unable to finish reading image", e);
        }
        // we can load the record from the database based on the incoming employeeId

        // this is the URL to get the image
         String url = "/pub/images/" + image.getOriginalFilename();
       book.setPictureURL(url);

        book = bookDAO.save(book);

        response.setViewName("redirect:/user/index/" + book.getBookId());
        return response;
    }

}
