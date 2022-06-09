package ru.itmo.kotiki.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dto.OwnerDto;
import ru.itmo.kotiki.entitites.Kotiki;
import ru.itmo.kotiki.entitites.Owner;
import ru.itmo.kotiki.entitites.Users;
import ru.itmo.kotiki.repository.KotikiRepository;
import ru.itmo.kotiki.repository.OwnerRepository;
import ru.itmo.kotiki.repository.UserRepository;
import ru.itmo.kotiki.tools.KafkaTemplateTool;

import java.util.Collection;
import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService{
    private final OwnerRepository repositoryOwner;
    private final KotikiRepository repositoryKotiki;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    private KafkaTemplateTool kafkaTemplateTool;

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
    @KafkaListener(topics = "searchOwnerById")
    public OwnerDto readById(OwnerDto ownerDtos) {
        Owner owner = repositoryOwner.findById(ownerDtos.getId()).get();
        OwnerDto ownerDto = modelMapper.map(owner, OwnerDto.class);
        kafkaTemplateTool.kafkaOwnerTemplate.send("sendOwner", ownerDto);
        return ownerDto;
    }

    @Override
    public Owner update(Owner entity) {
        return repositoryOwner.save(entity);
    }

    @Override
    @KafkaListener(topics = "deleteOwner")
    public void delete(OwnerDto ownerDto) {
        OwnerDto ownerDtos = readById(ownerDto);
        Owner owner = modelMapper.map(ownerDtos, Owner.class);
        repositoryOwner.delete(owner);
    }
    @Override
    public Owner addUser(Long owner, Long user) {
        OwnerDto ownerDtos = new OwnerDto();
        ownerDtos.setId(owner);
        OwnerDto ownerDto = readById(ownerDtos);
        Owner owner1 = modelMapper.map(ownerDto, Owner.class);
        Users user1 = userRepository.getById(user);
        owner1.setUser(user1);
        return owner1;
    }

    @Override
    public Owner addKotik(Long owner1, Long kotik1) {
        Kotiki kotik = repositoryKotiki.getById(kotik1);
        OwnerDto ownerDtos = new OwnerDto();
        ownerDtos.setId(owner1);
        OwnerDto ownerDto = readById(ownerDtos);
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
    @KafkaListener(topics = "getAllOwners")
    public Collection<Owner> getAll(List<OwnerDto> ownerDtos) {
        List<Owner> owners = repositoryOwner.findAll();
        for(Owner owner : owners) {
            OwnerDto ownerDto = modelMapper.map(owner, OwnerDto.class);
            ownerDtos.add(ownerDto);
        }
        kafkaTemplateTool.kafkaOwnersTemplate.send("sendOwners", ownerDtos);
        return repositoryOwner.findAll();
    }

    @Override
    public Owner getOwnerByUsername(String name) {
        return repositoryOwner.findOwnerByName(name);
    }
}
