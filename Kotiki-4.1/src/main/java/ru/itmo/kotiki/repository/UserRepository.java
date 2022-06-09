package ru.itmo.kotiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.kotiki.entitites.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findUserByName(String username);
}
