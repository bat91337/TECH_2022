package ru.itmo.kotik2.wrappers;

import ru.itmo.kotik2.entitites.Kotiki;
import ru.itmo.kotik2.entitites.Users;

import javax.persistence.*;
import java.util.List;

public class OwnerWrapper {
    private Long id;
    private String name;
    private String date;
    private List<Kotiki> kotiks;
    private Users user;

    public OwnerWrapper(Long id, String name, String date, List<Kotiki> kotiks, Users user) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.kotiks = kotiks;
        this.user = user;
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

    public List<Kotiki> getKotiks() {
        return kotiks;
    }

    public Users getUser() {
        return user;
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

    public void setKotiks(List<Kotiki> kotiks) {
        this.kotiks = kotiks;
    }

    public void setUser(Users user) {
        this.user = user;
    }

}
