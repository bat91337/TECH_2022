package ru.itmo.kotik2.service;

import ru.itmo.kotik2.dto.OwnerDto;
import ru.itmo.kotik2.entitites.Owner;

import java.util.Collection;

public interface OwnerService {
    Owner create(Owner entity);
    OwnerDto readById(Long key);
    Owner update(Owner entity);
    void delete(Long key);
    Owner addUser (Long owner, Long user);
    Owner addKotik(Long owner, Long kotik);
    Collection<Owner> GetOwnerByDate(String date);
    Collection<Owner> getAll();
    Owner getOwnerByUsername(String name);
}
