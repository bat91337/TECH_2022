package ru.itmo.kotik2.Service;

import ru.itmo.kotik2.dto.OwnerDto;
import ru.itmo.kotik2.entitites.Owner;

import java.util.Collection;
import java.util.List;

public interface OwnerService {
    void getOwner(OwnerDto ownerDto);
    void getOwners(List<OwnerDto> ownersDto);
}
