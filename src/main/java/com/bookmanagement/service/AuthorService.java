package com.bookmanagement.service;


import com.bookmanagement.dto.AuthorsDto;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface AuthorService {

    AuthorsDto saveAuthor(AuthorsDto saveAuthor) throws ValidationException;

    AuthorsDto updateAuthor(AuthorsDto updateAuthor);

    void deleteAuthor(Integer id);

    List<AuthorsDto> findAllAuthors();

    AuthorsDto findAuthorBySurname(String surname);

}
