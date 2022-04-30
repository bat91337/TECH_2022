package ru.itmo.kotik2.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotik2.entitites.Colors;
import ru.itmo.kotik2.entitites.Kotiki;
import ru.itmo.kotik2.entitites.Owner;
import ru.itmo.kotik2.repository.KotikiRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class KotikiServiceImpl implements KotikiService {

    private final ModelMapper modelMapper;

    private final KotikiRepository repositoryKotiki;

    @Autowired
    public KotikiServiceImpl(KotikiRepository repositoryKotiki, ModelMapper modelMapper) {
        this.repositoryKotiki = repositoryKotiki;
        this.modelMapper = modelMapper;
    }


    // TODO: 30.04.2022 Use Dtos instead of Entities in method parameters
    @Override
    public Kotiki create(Kotiki kotik) {
//        TODO: Kotiki kotiki = modelMapper.map(kotikiDto, Kotiki.class);

        return repositoryKotiki.save(kotik);
    }
    @Override
    public Collection<Kotiki> GetAllAdmin(){
        return repositoryKotiki.findAll();
    }

    @Override
    public Kotiki readById(Long key) {
        return repositoryKotiki.findById(key).get();
    }

    @Override
    public Kotiki update(Kotiki entity) {
        return repositoryKotiki.save(entity);
    }

    @Override
    public void delete(Long key) {
        Kotiki kotiki = readById(key);
        repositoryKotiki.delete(kotiki);
    }

    @Override
    public Kotiki addFriend(Kotiki kotik, Long id) {
        Kotiki kotik1 = repositoryKotiki.getById(id);
        kotik.getKotiks().add(kotik1);
        repositoryKotiki.save(kotik1);
        kotik1.getKotiks().add(kotik);
        repositoryKotiki.save(kotik);
        return kotik;
    }

    @Override
    public Collection<Kotiki> GetKotikByColor(Colors color) {
        return repositoryKotiki.getKotikiByColor(color);
    }

    @Override
    public Collection<Kotiki> GetKotikByBreed(String breed) {
        return repositoryKotiki.getKotikiByBreed(breed);
    }

    @Override
    public Collection<Kotiki> GetKotikByOwner(Owner owner) {
        return repositoryKotiki.getKotikiByOwner(owner);
    }

    @Override
    public Collection<Kotiki> GetAllUser(Owner owner) {
        Collection<Kotiki> kotikis = new ArrayList<>();
        for(Kotiki kotik : owner.getKotiks()){
            kotikis.add(kotik);
        }
        return kotikis;
    }
}
