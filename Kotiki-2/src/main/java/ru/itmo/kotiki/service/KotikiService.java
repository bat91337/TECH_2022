package ru.itmo.kotiki.service;

import ru.itmo.kotiki.entitites.Colors;
import ru.itmo.kotiki.entitites.Kotiki;
import ru.itmo.kotiki.entitites.Owner;

import java.awt.*;
import java.util.Collection;

public interface KotikiService {
    Kotiki create(Kotiki kotik);
    Kotiki readById(Long key);
    Kotiki update(Kotiki entity);
    void delete(Kotiki entity);
    Kotiki addFriend(Kotiki kotik, Long id);
    Collection<Kotiki> GetKotikByColor(Colors color);
    Collection<Kotiki> GetKotikByBreed(String breed);
    Collection<Kotiki> GetKotikByOwner(Owner owner);
}
