package ru.itmo.kotik2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.kotik2.entitites.Colors;
import ru.itmo.kotik2.entitites.Kotiki;
import ru.itmo.kotik2.entitites.Owner;

import java.util.Collection;

public interface KotikiRepository extends JpaRepository<Kotiki, Long> {
    Collection<Kotiki> getKotikiByColor(Colors color);
    Collection<Kotiki> getKotikiByBreed(String breed);
    Collection<Kotiki> getKotikiByOwner(Owner owner);
}
