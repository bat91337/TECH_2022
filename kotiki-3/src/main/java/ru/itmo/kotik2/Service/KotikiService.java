package ru.itmo.kotik2.service;

import ru.itmo.kotik2.entitites.Colors;
import ru.itmo.kotik2.entitites.Kotiki;
import ru.itmo.kotik2.entitites.Owner;

import java.util.Collection;

public interface KotikiService {
    Kotiki create(Kotiki kotik);
    Kotiki readById(Long key);
    Kotiki update(Kotiki entity);
    void delete(Long key);
    Kotiki addFriend(Kotiki kotik, Long id);
    Collection<Kotiki> GetKotikByColor(Colors color);
    Collection<Kotiki> GetKotikByBreed(String breed);
    Collection<Kotiki> GetKotikByOwner(Owner owner);
    Collection<Kotiki> GetAllAdmin();
    Collection<Kotiki> GetAllUser(Owner owner);
}
