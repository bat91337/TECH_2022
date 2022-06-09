package ru.itmo.kotiki.dto;

import java.util.List;

public class OwnerDto {
    private Long id;
    private String name;
    private String date;
    private List<KotikDto> kotiki;
    private String user;

    public OwnerDto(Long id, String name, String date, List<KotikDto> kotiki, String user) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.kotiki = kotiki;
        this.user = user;
    }

    public OwnerDto() {
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

    public List<KotikDto> getKotiki() {
        return kotiki;
    }

    public void setKotiki(List<KotikDto> kotiki) {
        this.kotiki = kotiki;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
