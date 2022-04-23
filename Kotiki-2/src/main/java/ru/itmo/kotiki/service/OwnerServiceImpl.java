package ru.itmo.kotiki.service;

import org.springframework.stereotype.Service;
import ru.itmo.kotiki.entitites.Kotiki;
import ru.itmo.kotiki.entitites.Owner;
import ru.itmo.kotiki.repository.KotikiRepository;
import ru.itmo.kotiki.repository.OwnerRepository;

import java.util.Collection;

@Service
public class OwnerServiceImpl implements OwnerService{
    private final OwnerRepository repositoryOwner;
    private final KotikiRepository repositoryKotiki;

    public OwnerServiceImpl(OwnerRepository repositoryOwner, KotikiRepository repositoryKotiki) {
        this.repositoryOwner = repositoryOwner;
        this.repositoryKotiki = repositoryKotiki;
    }

    @Override
    public Owner create(Owner entity) {
        return repositoryOwner.save(entity);
    }

    @Override
    public Owner readById(Long key) {
        return repositoryOwner.findById(key).get();
    }

    @Override
    public Owner update(Owner entity) {
        return repositoryOwner.save(entity);
    }

    @Override
    public void delete(Owner entity) {
        repositoryOwner.delete(entity);
    }

    @Override
    public Owner addKotik(Long owner1, Long kotik1) {
        Kotiki kotik = repositoryKotiki.getById(kotik1);
        Owner owner = readById(owner1);
        owner.getKotiks().add(kotik);
        kotik.setOwner(owner);
        repositoryKotiki.save(kotik);
        return repositoryOwner.save(owner);
    }

    @Override
    public Collection<Owner> GetOwnerByDate(String date) {
        return repositoryOwner.getOwnerByDate(date);
    }
}
