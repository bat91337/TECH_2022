package ru.itmo.kotik2.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.itmo.kotik2.dto.OwnerDto;
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
    private final ModelMapper modelMapper;

    public OwnerServiceImpl(OwnerRepository repositoryOwner, KotikiRepository repositoryKotiki, UserRepository userRepository
            , ModelMapper modelMapper) {
        this.repositoryOwner = repositoryOwner;
        this.repositoryKotiki = repositoryKotiki;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Owner create(Owner entity) {
        return repositoryOwner.save(entity);
    }

    @Override
    public OwnerDto readById(Long key) {
        Owner owner = repositoryOwner.findById(key).get();
        OwnerDto ownerDto = modelMapper.map(owner, OwnerDto.class);
        return ownerDto;
    }

    @Override
    public Owner update(Owner entity) {
        return repositoryOwner.save(entity);
    }

    @Override
    public void delete(Long key) {
        OwnerDto ownerDto = readById(key);
        Owner owner = modelMapper.map(ownerDto, Owner.class);
        repositoryOwner.delete(owner);
    }
    @Override
    public Owner addUser(Long owner, Long user) {
        OwnerDto ownerDto = readById(owner);
        Owner owner1 = modelMapper.map(ownerDto, Owner.class);
        Users user1 = userRepository.getById(user);
        owner1.setUser(user1);
        return owner1;
    }

    @Override
    public Owner addKotik(Long owner1, Long kotik1) {
        Kotiki kotik = repositoryKotiki.getById(kotik1);
        OwnerDto ownerDto = readById(owner1);
        Owner owner = modelMapper.map(ownerDto, Owner.class);
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
