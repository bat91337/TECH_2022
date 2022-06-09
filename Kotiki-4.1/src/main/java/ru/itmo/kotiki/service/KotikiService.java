package ru.itmo.kotiki.service;

import ru.itmo.kotiki.dto.KotikDto;
import ru.itmo.kotiki.dto.OwnerDto;
import ru.itmo.kotiki.entitites.Colors;
import ru.itmo.kotiki.entitites.Kotiki;
import ru.itmo.kotiki.entitites.Owner;

import java.awt.*;
import java.util.Collection;
import java.util.List;

public interface KotikiService {
    Kotiki create(KotikDto kotik);
    KotikDto readById(KotikDto kotiki);
    Kotiki update(KotikDto entity);
    void delete(KotikDto kotiki);
    Kotiki addFriend(Kotiki kotik, Long id);
    Collection<Kotiki> GetKotikByColor(KotikDto kotikDto);
    Collection<Kotiki> GetKotikByBreed(String breed);
    Collection<Kotiki> GetKotikByOwner(Owner owner);
    Collection<Kotiki> GetAllAdmin(List<KotikDto> kotikDto);
    Collection<Kotiki> GetAllUser(OwnerDto owner);
}
