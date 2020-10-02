package com.bookmanagement.service;


import com.bookmanagement.entity.Users;
import com.bookmanagement.enums.EnumRole;
import com.bookmanagement.repository.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UsersRepository usersRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users saveUser(Users users) {
        users.setPhoneNumber(users.getPhoneNumber());
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRole(EnumRole.COSTUMER);
        return usersRepository.save(users);
    }

    public Users findByPhoneNumber(String phoneNumber) {
        return usersRepository.findByPhoneNumber(phoneNumber);
    }

    public Users findByPhoneNumberAndPassword(String phoneNumber, String password) {
        Users users = findByPhoneNumber(phoneNumber);
        if (users != null) {
            if (passwordEncoder.matches(password, users.getPassword())) {
                return users;
            }
        }
        return null;
    }
}
