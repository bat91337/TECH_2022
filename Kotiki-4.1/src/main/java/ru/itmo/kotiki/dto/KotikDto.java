package ru.itmo.kotiki.dto;

import ru.itmo.kotiki.entitites.Colors;

public class KotikDto {
    private Long id;
    private String name;
    private String date;
    private String breed;
    private String owner;
    private Colors color;
    public KotikDto(Long id, String name, String date, String breed, String owner, Colors color) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.breed = breed;
        this.owner = owner;
        this.color = color;
    }

    public KotikDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
