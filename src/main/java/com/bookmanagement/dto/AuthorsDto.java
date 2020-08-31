package com.bookmanagement.dto;

import java.time.LocalDate;

public class AuthorsDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate yearOfBirth;


    public AuthorsDto(Integer id, String firstName, String lastName, LocalDate yearOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(LocalDate yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString() {
        return "Authors [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", yearOfBirth=" + yearOfBirth + "]";
    }
}
