package ru.itmo.kotik2.service;

import org.springframework.stereotype.Service;
import ru.itmo.kotik2.entitites.Kotiki;
import ru.itmo.kotik2.entitites.Owner;
import ru.itmo.kotik2.entitites.Users;
import ru.itmo.kotik2.repository.KotikiRepository;
import ru.itmo.kotik2.repository.OwnerRepository;
import ru.itmo.kotik2.repository.UserRepository;

import java.util.Collection;
@Service
public class OwnerServiceImpl implements OwnerService{
    private final OwnerRepository repositoryOwner;
    private final KotikiRepository repositoryKotiki;
    private final UserRepository userRepository;

    public OwnerServiceImpl(OwnerRepository repositoryOwner, KotikiRepository repositoryKotiki, UserRepository userRepository) {
        this.repositoryOwner = repositoryOwner;
        this.repositoryKotiki = repositoryKotiki;
        this.userRepository = userRepository;
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
    public void delete(Long key) {
        Owner owner = readById(key);
        repositoryOwner.delete(owner);
    }
    @Override
    public Owner addUser(Long owner, Long user) {
        Owner owner1 = readById(owner);
        Users user1 = userRepository.getById(user);
        owner1.setUser(user1);
        return owner1;
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
    @Override
    public Collection<Owner> getAll() {
        return repositoryOwner.findAll();
    }

    @Override
    public Owner getOwnerByUsername(String name) {
        return repositoryOwner.findOwnerByName(name);
    }
}
