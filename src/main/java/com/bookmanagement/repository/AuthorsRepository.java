package com.bookmanagement.repository;

import com.bookmanagement.entity.Authors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsRepository extends JpaRepository<Authors, Integer> {

    Authors findAuthorByLastName(String lastName);

}

