package ru.itmo.kotik2.service;

import ru.itmo.kotik2.entitites.Users;

import java.util.Collection;

public interface UserService {
    Users create(Users user);
    Collection<Users> getAll();
    void delete(Long key);
    Users getById(Long id);
    Users getUsersByUsername(String user);
}
