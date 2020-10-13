package com.bookmanagement.controllers;

import com.bookmanagement.dto.BooksDto;
import com.bookmanagement.exception.NotFoundException;
import com.bookmanagement.exception.ValidationException;
import com.bookmanagement.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@Api(description = "Operations pertaining to books in Online Store")
public class BooksController {

    private static final Logger log = Logger.getLogger("BooksController.class");

    BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/admin/saveBook")
    @ApiOperation(value = "Create a book", response = BooksDto.class)
    BooksDto saveBook(@RequestBody BooksDto booksDto) throws ValidationException {
        log.info("handling create book request: " + booksDto);
        return bookService.saveBook(booksDto);
    }

    @PutMapping("/admin/updateBook")
    @ApiOperation(value = "Update a book", response = BooksDto.class)
    BooksDto updateBook(@RequestBody BooksDto booksDto) throws NotFoundException {
        log.info("handling update book request: " + booksDto);
        return bookService.updateBook(booksDto);
    }

    @DeleteMapping("/admin/deleteBook/{id}")
    @ApiOperation(value = "Delete a book", response = Integer.class)
    ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        log.info("handling delete book request: id=" + id);
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/costumer/findBookById")
    @ApiOperation(value = "Find book by id", response = BooksDto.class)
    BooksDto findBookById(@RequestParam Integer id) {
        log.info("handling findBookById request: id=" + id);
        return bookService.findBookById(id);
    }

    @GetMapping("/costumer/findAllBooks")
    @ApiOperation(value = "Find all books", response = List.class)
    List<BooksDto> findAllBooks() {
        log.info("handling findAllBooks request");
        return bookService.findAllBooks();
    }
}
