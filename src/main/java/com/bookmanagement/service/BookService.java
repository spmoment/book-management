package com.bookmanagement.service;

import com.bookmanagement.dto.BooksDto;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface BookService {

    BooksDto saveBook(BooksDto saveBook) throws ValidationException;

    BooksDto updateAuthor(BooksDto updateBook);

    void deleteBook(Integer id);

    List<BooksDto> findAllBooks();

    BooksDto findBookById(Integer id);

}
