package com.bookmanagement.service;


import com.bookmanagement.dto.AuthorsDto;
import com.bookmanagement.entity.Authors;
import org.springframework.stereotype.Component;

@Component
public class AuthorsConverter {

    public Authors fromAuthorsDtoToAuthors(AuthorsDto authorsDto) {
        Authors authors = new Authors();
        authors.setId(authorsDto.getId());
        authors.setFirstName(authorsDto.getFirstName());
        authors.setLastName(authorsDto.getLastName());
        authors.setYearOfBirth(authorsDto.getYearOfBirth());
        return authors;
    }

    public AuthorsDto fromAuthorsToAuthorsDto(Authors authors) {
        return new AuthorsDto(
                authors.getId(),
                authors.getFirstName(),
                authors.getLastName(),
                authors.getYearOfBirth());
    }

}
