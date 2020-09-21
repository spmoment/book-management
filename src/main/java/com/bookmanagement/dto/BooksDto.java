package com.bookmanagement.dto;


import java.time.LocalDate;
import java.util.List;

public class BooksDto {

    private Integer id;
    private String title;
    private LocalDate yearPublishing;
    private String annotation;
    private List<AuthorsDto> authorsDtos;

    public BooksDto(Integer id, String title, LocalDate yearPublishing, String annotation, List<AuthorsDto> authorsDtos) {
        this.id = id;
        this.title = title;
        this.yearPublishing = yearPublishing;
        this.annotation = annotation;
        this.authorsDtos = authorsDtos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getYearPublishing() {
        return yearPublishing;
    }

    public void setYearPublishing(LocalDate yearPublishing) {
        this.yearPublishing = yearPublishing;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public List<AuthorsDto> getAuthorsDtos() {
        return authorsDtos;
    }

    public void setAuthorsDtos(List<AuthorsDto> authorsDtos) {
        this.authorsDtos = authorsDtos;
    }

    @Override
    public String toString() {
        return "Books [id=" + id + ", title=" + title + ", yearPublishing=" + yearPublishing + ", annotation=" + annotation + "]";
    }
}
