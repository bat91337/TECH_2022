package ru.itmo.kotiki.service;

import ru.itmo.kotiki.dto.UserDto;
import ru.itmo.kotiki.entitites.Users;

import java.util.Collection;
import java.util.List;

public interface UserService {
    Users create(Users user);
    Collection<Users> getAll(List<UserDto> userDto);
    void delete(Long key);
    Users getById(Long id);
    Users getUsersByUsername(UserDto user);
}
