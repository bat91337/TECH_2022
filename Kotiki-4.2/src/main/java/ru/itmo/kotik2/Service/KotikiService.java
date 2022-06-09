package ru.itmo.kotik2.Service;

import ru.itmo.kotik2.dto.KotikDto;
import ru.itmo.kotik2.entitites.Colors;
import ru.itmo.kotik2.entitites.Kotiki;
import ru.itmo.kotik2.entitites.Owner;

import java.util.Collection;
import java.util.List;

public interface KotikiService {
    void getKotik(KotikDto kotikDto);
    void getKotiki(List<KotikDto> kotikDtos);
}
