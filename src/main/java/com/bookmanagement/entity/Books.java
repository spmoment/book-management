package com.bookmanagement.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "year_publishing")
    private LocalDate yearPublishing;

    @Column(name = "annotation")
    private String annotation;

    @ManyToMany()
    @JoinTable(name = "books_authors",
            joinColumns = {@JoinColumn(name = "books_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authors_id", referencedColumnName = "id")})
    private Set<Authors> authorList;

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

    public Set<Authors> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(Set<Authors> authorList) {
        this.authorList = authorList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return Objects.equals(id, books.id) &&
                Objects.equals(title, books.title) &&
                Objects.equals(yearPublishing, books.yearPublishing) &&
                Objects.equals(annotation, books.annotation) &&
                Objects.equals(authorList, books.authorList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, yearPublishing, annotation, authorList);
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", yearPublishing=" + yearPublishing +
                ", annotation='" + annotation + '\'' +
                '}';
    }
}
