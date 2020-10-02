package com.bookmanagement.repository;

import com.bookmanagement.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findByPhoneNumber(String phoneNumber);

}
