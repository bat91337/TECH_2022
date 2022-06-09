package ru.itmo.kotik2.Service;

import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.itmo.kotik2.dto.OwnerDto;
import ru.itmo.kotik2.entitites.Kotiki;
import ru.itmo.kotik2.entitites.Owner;
import ru.itmo.kotik2.entitites.Users;
import ru.itmo.kotik2.repository.KotikiRepository;
import ru.itmo.kotik2.repository.OwnerRepository;
import ru.itmo.kotik2.repository.UserRepository;

import java.util.Collection;
import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    private OwnerDto ownerDto;
    private List<OwnerDto> ownersDto;

    public OwnerServiceImpl() {
    }


    @Override
    @KafkaListener(topics = "sendOwner")
    public void getOwner(OwnerDto ownerDto) {
        this.ownerDto = ownerDto;
    }

    @Override
    @KafkaListener(topics = "sendOwners")
    public void getOwners(List<OwnerDto> ownersDto) {
        this.ownersDto = ownersDto;
    }

    public OwnerDto getOwnerDto() {
        return ownerDto;
    }

    public void setOwnerDto(OwnerDto ownerDto) {
        this.ownerDto = ownerDto;
    }

    public List<OwnerDto> getOwnersDto() {
        return ownersDto;
    }

    public void setOwnersDto(List<OwnerDto> ownersDto) {
        this.ownersDto = ownersDto;
    }
}
