package com.bookmanagement.repository;

import com.bookmanagement.dto.AuthorsDto;
import com.bookmanagement.entity.Authors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorsRepository extends JpaRepository<Authors, Integer> {

    Authors findAuthorBySurname(String surname);

}

