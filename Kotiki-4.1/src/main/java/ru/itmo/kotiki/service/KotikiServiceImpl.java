package ru.itmo.kotiki.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dto.KotikDto;
import ru.itmo.kotiki.dto.OwnerDto;
import ru.itmo.kotiki.entitites.Colors;
import ru.itmo.kotiki.entitites.Kotiki;
import ru.itmo.kotiki.entitites.Owner;
import ru.itmo.kotiki.repository.KotikiRepository;
import ru.itmo.kotiki.repository.OwnerRepository;
import ru.itmo.kotiki.tools.KafkaTemplateTool;
import java.util.List;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class KotikiServiceImpl implements KotikiService{
    private final ModelMapper modelMapper;

    private final KotikiRepository repositoryKotiki;
    private final OwnerRepository ownerRepository;
    @Autowired
    private KafkaTemplateTool kafkaTemplateTool;

    public KotikiServiceImpl(KotikiRepository repositoryKotiki, ModelMapper modelMapper,
                             OwnerRepository ownerRepository) {
        this.repositoryKotiki = repositoryKotiki;
        this.modelMapper = modelMapper;
        this.ownerRepository = ownerRepository;
    }

    @Override
    @KafkaListener(topics = "createKotik")
    public Kotiki create(KotikDto kotik) {
        Kotiki kotiki = modelMapper.map(kotik, Kotiki.class);
        return repositoryKotiki.save(kotiki);
    }
    @Override
    @KafkaListener(topics = "getAllAdmin")
    public Collection<Kotiki> GetAllAdmin(List<KotikDto> kotikDtos){
        List<Kotiki> kotiki = repositoryKotiki.findAll();
        for(Kotiki kotik : kotiki) {
            KotikDto kotikDto = modelMapper.map(kotik, KotikDto.class);
            kotikDtos.add(kotikDto);
        }
        kafkaTemplateTool.kafkaKotikiTemplate.send("sendKotiki", kotikDtos);
        return repositoryKotiki.findAll();
    }

    @Override
    @KafkaListener(topics = "SearchByIdKotik")
    public KotikDto readById(KotikDto kotiki) {
        Kotiki kotik = repositoryKotiki.findById(kotiki.getId()).get();
        KotikDto kotikDto = modelMapper.map(kotik, KotikDto.class);
        kafkaTemplateTool.kafkaKotikTemplate.send("sendKotik", kotikDto);
        return kotikDto;
    }

    @Override
    @KafkaListener(topics = "updateKotik")
    public Kotiki update(KotikDto entity) {
        Kotiki kotik = modelMapper.map(entity, Kotiki.class);
        return repositoryKotiki.save(kotik);
    }

    @Override
    public void delete(KotikDto kotikDto) {
        KotikDto kotikiDto = readById(kotikDto);
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
    @KafkaListener(topics = "getByColor")
    public Collection<Kotiki> GetKotikByColor(KotikDto kotikDto1) {
        Collection<Kotiki> kotiki = repositoryKotiki.getKotikiByColor(kotikDto1.getColor());
        List<KotikDto> kotikDtos = new ArrayList<>();
        for(Kotiki kotik : kotiki) {
            KotikDto kotikDto = modelMapper.map(kotik, KotikDto.class);
            kotikDtos.add(kotikDto);
        }
        kafkaTemplateTool.kafkaKotikiTemplate.send("sendKotiki", kotikDtos);

        return repositoryKotiki.getKotikiByColor(kotikDto1.getColor());
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
    @KafkaListener(topics = "getAllByOwner")
    public Collection<Kotiki> GetAllUser(OwnerDto owner) {
        Owner owner1 = ownerRepository.findOwnerByName(owner.getName());
        Collection<Kotiki> kotikis = repositoryKotiki.getKotikiByOwner(owner1);
        List<KotikDto> kotikDto = new ArrayList<>();
       for(Kotiki kotiki : kotikis){
           KotikDto kotikDto1 = modelMapper.map(kotiki, KotikDto.class);
           kotikDto.add(kotikDto1);
       }
        kafkaTemplateTool.kafkaKotikiTemplate.send("sendKotiki", kotikDto);
        return kotikis;
    }
}
