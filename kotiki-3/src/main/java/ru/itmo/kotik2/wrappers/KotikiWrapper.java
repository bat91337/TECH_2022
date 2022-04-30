package ru.itmo.kotik2.wrappers;

import ru.itmo.kotik2.entitites.Kotiki;

import java.util.List;
import java.util.Set;

public class KotikiWrapper {
    private Long id;
    private String name;
    private String date;
    private String breed;
    private String owner;
    private String color;
    private List<Long> kotiks;


    public KotikiWrapper(Long id, String name, String date, String breed, String owner, String color, List<Long> kotiks) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.breed = breed;
        this.owner = owner;
        this.color = color;
        this.kotiks = kotiks;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getBreed() {
        return breed;
    }

    public String getOwner() {
        return owner;
    }

    public String getColor() {
        return color;
    }

    public List<Long> getKotiks() {
        return kotiks;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setKotiks(List<Long> kotiks) {
        this.kotiks = kotiks;
    }
}
