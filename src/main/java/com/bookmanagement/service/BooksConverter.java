package com.bookmanagement.service;

import com.bookmanagement.dto.AuthorsDto;
import com.bookmanagement.dto.BooksDto;
import com.bookmanagement.entity.Authors;
import com.bookmanagement.entity.Books;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BooksConverter {

    private final AuthorsConverter authorsConverter;

    public BooksConverter(AuthorsConverter authorsConverter) {
        this.authorsConverter = authorsConverter;
    }

    public Books fromBooksDtoToBooks(BooksDto booksDto) {

        Set<Authors> authorsDtos = booksDto.getAuthorsDtos().stream()
                .map(authorsConverter::fromAuthorsDtoToAuthors)
                .collect(Collectors.toSet());

        Books books = new Books();
        books.setId(booksDto.getId());
        books.setTitle(booksDto.getTitle());
        books.setYearPublishing(booksDto.getYearPublishing());
        books.setAnnotation(booksDto.getAnnotation());
        books.setAuthorList(authorsDtos);
        return books;
    }

    public BooksDto fromBooksToBooksDto(Books books) {

        List<AuthorsDto> authorsDtos = books.getAuthorList().stream()
                .map(authorsConverter::fromAuthorsToAuthorsDto)
                .collect(Collectors.toList());

        return new BooksDto(
                books.getId(),
                books.getTitle(),
                books.getYearPublishing(),
                books.getAnnotation(),
                authorsDtos);
    }
}
