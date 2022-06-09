package ru.itmo.kotiki.service;

import ru.itmo.kotiki.dto.OwnerDto;
import ru.itmo.kotiki.entitites.Owner;

import java.util.Collection;
import java.util.List;

public interface OwnerService {
    Owner create(Owner entity);
    OwnerDto readById(OwnerDto ownerDto);
    Owner update(Owner entity);
    void delete(OwnerDto ownerDto);
    Owner addUser (Long owner, Long user);
    Owner addKotik(Long owner, Long kotik);
    Collection<Owner> GetOwnerByDate(String date);
    Collection<Owner> getAll(List<OwnerDto> ownerDto);
    Owner getOwnerByUsername(String name);
}
