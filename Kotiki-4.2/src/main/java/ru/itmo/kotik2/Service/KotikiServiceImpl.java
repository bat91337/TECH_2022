package ru.itmo.kotik2.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.itmo.kotik2.dto.KotikDto;
import ru.itmo.kotik2.entitites.Colors;
import ru.itmo.kotik2.entitites.Kotiki;
import ru.itmo.kotik2.entitites.Owner;
import ru.itmo.kotik2.repository.KotikiRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class KotikiServiceImpl implements KotikiService {

    private KotikDto kotikDto;
    private List<KotikDto> kotiksDto;


    @Autowired
    public KotikiServiceImpl() {
    }


    @Override
    @KafkaListener(topics = "sendKotik")
    public void getKotik(KotikDto kotikDto) {
        this.kotikDto = kotikDto;
    }

    @Override
    @KafkaListener(topics = "sendKotiki")
    public void getKotiki(List<KotikDto> kotikDtos) {
        this.kotiksDto = kotikDtos;
    }

    public KotikDto getKotikDto() {
        return kotikDto;
    }

    public void setKotikDto(KotikDto kotikDto) {
        this.kotikDto = kotikDto;
    }

    public List<KotikDto> getKotiksDto() {
        return kotiksDto;
    }

    public void setKotiksDto(List<KotikDto> kotiksDto) {
        this.kotiksDto = kotiksDto;
    }
}
