package com.bookmanagement.controllers;

import com.bookmanagement.dto.AuthorsDto;
import com.bookmanagement.exception.NotFoundException;
import com.bookmanagement.exception.ValidationException;
import com.bookmanagement.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class AuthorsController {

    private static final Logger log = Logger.getLogger("AuthorsController.class");
    @Autowired
    AuthorService authorService;

    @PostMapping("/saveAuthor")
    AuthorsDto saveAuthor(@RequestBody AuthorsDto authorsDto) throws ValidationException {
        log.info("handling create author request: " + authorsDto);
        return authorService.saveAuthor(authorsDto);
    }

    @PutMapping("/updateAuthor")
    AuthorsDto updateAuthor(@RequestBody AuthorsDto authorsDto) throws NotFoundException {
        log.info("handling update author request: " + authorsDto);
        return authorService.updateAuthor(authorsDto);
    }

    @DeleteMapping("/deleteAuthor/{id}")
    ResponseEntity<Void> deleteAuthor(@PathVariable Integer id) {
        log.info("handling delete author request: id=" + id);
        authorService.deleteAuthor(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findByLastName")
    AuthorsDto findByLastName(@RequestParam String lastName) {
        log.info("handling findByLastName request: " + lastName);
        return authorService.findAuthorByLastName(lastName);
    }

    @GetMapping("/findAllAuthors")
    List<AuthorsDto> findAllAuthors() {
        log.info("handling findAllAuthors request");
        return authorService.findAllAuthors();
    }
}
