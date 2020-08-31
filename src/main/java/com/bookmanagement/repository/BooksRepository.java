package com.bookmanagement.repository;

import com.bookmanagement.dto.BooksDto;
import com.bookmanagement.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksRepository extends JpaRepository<Books, Integer> {

    Books findBookById(Integer id);

}
