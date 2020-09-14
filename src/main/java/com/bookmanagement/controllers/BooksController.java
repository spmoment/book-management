package com.bookmanagement.controllers;

import com.bookmanagement.dto.BooksDto;
import com.bookmanagement.exception.NotFoundException;
import com.bookmanagement.exception.ValidationException;
import com.bookmanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class BooksController {

    private static final Logger log = Logger.getLogger("BooksController.class");
    @Autowired
    BookService bookService;

    @PostMapping("/saveBook")
    BooksDto saveBook(@RequestBody BooksDto booksDto) throws ValidationException {
        log.info("handling create book request: " + booksDto);
        return bookService.saveBook(booksDto);
    }

    @PutMapping("/updateBook")
    BooksDto updateBook(@RequestBody BooksDto booksDto) throws NotFoundException {
        log.info("handling update book request: " + booksDto);
        return bookService.updateBook(booksDto);
    }

    @DeleteMapping("/deleteBook/{id}")
    ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        log.info("handling delete book request: id=" + id);
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findBookById")
    BooksDto findBookById(@RequestParam Integer id) {
        log.info("handling findBookById request: id=" + id);
        return bookService.findBookById(id);
    }

    @GetMapping("/findAllBooks")
    List<BooksDto> findAllBooks() {
        log.info("handling findAllBooks request");
        return bookService.findAllBooks();
    }
}
