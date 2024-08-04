package org.subash.capstone.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
    public ModelAndView submit(@Valid CreateBookFormBean form, BindingResult bindingResult) {
        ModelAndView response = new ModelAndView();
        if (form.getBookId() == null) {
            Book book = bookDAO.findBookByIsbn(form.getIsbn());
            if (book != null) {
                bindingResult.rejectValue("isbn", "isbn-error", "ISBN already exists");
            }
        }

        if (bindingResult.hasErrors()) {
            response.addObject("bindingResult", bindingResult);
            response.addObject("form", form);
            response.setViewName("book/create");
            return response;
        } else {
            Book book = bookDAO.findBookByBookId(form.getBookId());
            if (book == null) {
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
            String saveFilename = "./src/main/webapp/pub/images/" + form.getImage().getOriginalFilename();

            try {
                Files.copy(form.getImage().getInputStream(), Paths.get(saveFilename), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                log.error("Unable to finish reading image", e);
            }

            String url = "/pub/images/" + form.getImage().getOriginalFilename();
            book.setPictureURL(url);

            book = bookDAO.save(book);

            response.setViewName("redirect:/book/index/" + book.getBookId());
            return response;
        }

    }

}
