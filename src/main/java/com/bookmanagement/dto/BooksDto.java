package com.bookmanagement.dto;


import java.time.LocalDate;

public class BooksDto {

    private Integer id;
    private String title;
    private LocalDate yearPublishing;
    private String annotation;

    public BooksDto(Integer id, String title, LocalDate yearPublishing, String annotation) {
        this.id = id;
        this.title = title;
        this.yearPublishing = yearPublishing;
        this.annotation = annotation;
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

    @Override
    public String toString() {
        return "Books [id=" + id + ", title=" + title + ", yearPublishing=" + yearPublishing + ", annotation=" + annotation + "]";
    }
}
