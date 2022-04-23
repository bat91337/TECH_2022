package ru.itmo.kotiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.kotiki.entitites.Owner;

import java.util.Collection;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Collection<Owner> getOwnerByDate(String Date);
}
