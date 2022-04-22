package ru.itmo.kotiki.service;

import ru.itmo.kotiki.entitites.Kotiki;
import ru.itmo.kotiki.entitites.Owner;

import java.util.Collection;

public interface OwnerService {
    Owner create(Owner entity);
    Owner readById(Long key);
    Owner update(Owner entity);
    void delete(Owner entity);
    Owner addKotik(Long owner, Long kotik);
    Collection<Owner> GetOwnerByDate(String date);
}
