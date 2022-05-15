package ru.itmo.kotik2.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotik2.dto.KotikDto;
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

    @Override
    public Kotiki create(Kotiki kotik) {

        return repositoryKotiki.save(kotik);
    }
    @Override
    public Collection<Kotiki> GetAllAdmin(){
        return repositoryKotiki.findAll();
    }

    @Override
    public KotikDto readById(Long key) {
        Kotiki kotik = repositoryKotiki.findById(key).get();
        KotikDto kotikDto = modelMapper.map(kotik, KotikDto.class);
        return kotikDto;
    }

    @Override
    public Kotiki update(Kotiki entity) {
        return repositoryKotiki.save(entity);
    }

    @Override
    public void delete(Long key) {
        KotikDto kotikiDto = readById(key);
        Kotiki kotiki = modelMapper.map(kotikiDto, Kotiki.class);
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
