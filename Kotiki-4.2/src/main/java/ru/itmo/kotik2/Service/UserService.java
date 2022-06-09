package ru.itmo.kotik2.Service;

import ru.itmo.kotik2.dto.UserDto;
import ru.itmo.kotik2.entitites.Users;

import java.util.Collection;
import java.util.List;

public interface UserService {
    void getUser(UserDto userDto);
    void getUsers(List<UserDto> usersDto);
}
