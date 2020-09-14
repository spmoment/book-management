package com.bookmanagement.service;


import com.bookmanagement.dto.AuthorsDto;
import com.bookmanagement.exception.NotFoundException;
import com.bookmanagement.exception.ValidationException;

import java.util.List;

public interface AuthorService {

    AuthorsDto saveAuthor(AuthorsDto saveAuthor) throws ValidationException;

    AuthorsDto updateAuthor(AuthorsDto updateAuthor) throws NotFoundException;

    void deleteAuthor(Integer id);

    List<AuthorsDto> findAllAuthors();

    AuthorsDto findAuthorByLastName(String lastName);

}
