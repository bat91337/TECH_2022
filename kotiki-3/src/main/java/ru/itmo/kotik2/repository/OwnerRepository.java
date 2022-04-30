package ru.itmo.kotik2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.kotik2.entitites.Owner;

import java.util.Collection;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Collection<Owner> getOwnerByDate(String Date);
    Owner findOwnerByName(String Username);
}
