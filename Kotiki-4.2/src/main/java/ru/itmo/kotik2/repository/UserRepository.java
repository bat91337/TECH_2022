package ru.itmo.kotik2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.kotik2.entitites.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findUserByName(String username);
}
