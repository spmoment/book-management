package com.bookmanagement.service;

import com.bookmanagement.dto.BooksDto;
import com.bookmanagement.entity.Books;
import org.springframework.stereotype.Component;

@Component
public class BooksConverter {

    public Books fromBooksDtoToBooks(BooksDto booksDto) {
        Books books = new Books();
        books.setId(booksDto.getId());
        books.setTitle(booksDto.getTitle());
        books.setYearPublishing(booksDto.getYearPublishing());
        books.setAnnotation(booksDto.getAnnotation());
        return books;
    }

    public BooksDto fromBooksToBooksDto(Books books) {
        return new BooksDto(
                books.getId(),
                books.getTitle(),
                books.getYearPublishing(),
                books.getAnnotation());
    }
}
