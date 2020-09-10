package com.bookmanagement.prototype;

import com.bookmanagement.dto.AuthorsDto;
import com.bookmanagement.entity.Authors;

import java.time.LocalDate;

public class AuthorsPrototype {
    public static Authors prAuthor() {
        Authors authors = new Authors();
        authors.setFirstName("TestFirstName");
        authors.setLastName("TestLastName");
        authors.setYearOfBirth(LocalDate.of(1900, 1, 1));
        return authors;
    }

    public static AuthorsDto prAuhorDto() {
        return new AuthorsDto(
                1,
                "TestFirstName",
                "TestLastName",
                LocalDate.of(1900, 1, 1));
    }
}
