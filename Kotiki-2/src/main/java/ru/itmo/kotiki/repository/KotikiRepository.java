package ru.itmo.kotiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.kotiki.entitites.Colors;
import ru.itmo.kotiki.entitites.Kotiki;
import ru.itmo.kotiki.entitites.Owner;

import java.awt.*;
import java.util.Collection;

public interface KotikiRepository extends JpaRepository<Kotiki, Long> {
    Collection<Kotiki> getKotikiByColor(Colors color);
    Collection<Kotiki> getKotikiByBreed(String breed);
    Collection<Kotiki> getKotikiByOwner(Owner owner);
}
