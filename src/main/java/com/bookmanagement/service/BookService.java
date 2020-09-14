package com.bookmanagement.service;

import com.bookmanagement.dto.BooksDto;
import com.bookmanagement.exception.NotFoundException;
import com.bookmanagement.exception.ValidationException;

import java.util.List;

public interface BookService {

    BooksDto saveBook(BooksDto saveBook) throws ValidationException;

    BooksDto updateBook(BooksDto updateBook) throws NotFoundException;

    void deleteBook(Integer id);

    List<BooksDto> findAllBooks();

    BooksDto findBookById(Integer id);

}
