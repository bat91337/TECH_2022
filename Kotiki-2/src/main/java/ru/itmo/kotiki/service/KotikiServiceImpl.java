package ru.itmo.kotiki.service;

import org.springframework.stereotype.Service;
import ru.itmo.kotiki.entitites.Colors;
import ru.itmo.kotiki.entitites.Kotiki;
import ru.itmo.kotiki.entitites.Owner;
import ru.itmo.kotiki.repository.KotikiRepository;

import java.awt.*;
import java.util.Collection;

@Service
public class KotikiServiceImpl implements KotikiService{
    private final KotikiRepository repositoryKotiki;

    public KotikiServiceImpl(KotikiRepository repositoryKotiki) {
        this.repositoryKotiki = repositoryKotiki;
    }

    @Override
    public Kotiki create(Kotiki kotik) {
        return repositoryKotiki.save(kotik);
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
    public void delete(Kotiki entity) {
        repositoryKotiki.delete(entity);
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
}
