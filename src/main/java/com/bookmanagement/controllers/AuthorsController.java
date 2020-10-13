package com.bookmanagement.controllers;

import com.bookmanagement.dto.AuthorsDto;
import com.bookmanagement.exception.NotFoundException;
import com.bookmanagement.exception.ValidationException;
import com.bookmanagement.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@Api(description = "Operations pertaining to authors in Online Store")
public class AuthorsController {

    private static final Logger log = Logger.getLogger("AuthorsController.class");

    AuthorService authorService;

    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/admin/saveAuthor")
    @ApiOperation(value = "Create a author", response = AuthorsDto.class)
    AuthorsDto saveAuthor(@RequestBody AuthorsDto authorsDto) throws ValidationException {
        log.info("handling create author request: " + authorsDto);
        return authorService.saveAuthor(authorsDto);
    }

    @PutMapping("/admin/updateAuthor")
    @ApiOperation(value = "Update a author", response = AuthorsDto.class)
    AuthorsDto updateAuthor(@RequestBody AuthorsDto authorsDto) throws NotFoundException {
        log.info("handling update author request: " + authorsDto);
        return authorService.updateAuthor(authorsDto);
    }

    @DeleteMapping("/admin/deleteAuthor/{id}")
    @ApiOperation(value = "Delete a author", response = Integer.class)
    ResponseEntity<Void> deleteAuthor(@PathVariable Integer id) {
        log.info("handling delete author request: id=" + id);
        authorService.deleteAuthor(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/costumer/findByLastName")
    @ApiOperation(value = "Find author by last name", response = AuthorsDto.class)
    AuthorsDto findByLastName(@RequestParam String lastName) {
        log.info("handling findByLastName request: " + lastName);
        return authorService.findAuthorByLastName(lastName);
    }

    @GetMapping("/costumer/findAllAuthors")
    @ApiOperation(value = "Find all authors", response = AuthorsDto.class)
    List<AuthorsDto> findAllAuthors() {
        log.info("handling findAllAuthors request");
        return authorService.findAllAuthors();
    }
}
