package ru.itmo.kotiki.dto;

public class OwnerKotikDto {
    private OwnerDto ownerDto;
    private KotikDto kotikDto;

    public OwnerKotikDto(OwnerDto ownerDto, KotikDto kotikDto) {
        this.ownerDto = ownerDto;
        this.kotikDto = kotikDto;
    }

    public OwnerDto getOwnerDto() {
        return ownerDto;
    }

    public void setOwnerDto(OwnerDto ownerDto) {
        this.ownerDto = ownerDto;
    }

    public KotikDto getKotikDto() {
        return kotikDto;
    }

    public void setKotikDto(KotikDto kotikDto) {
        this.kotikDto = kotikDto;
    }
}
