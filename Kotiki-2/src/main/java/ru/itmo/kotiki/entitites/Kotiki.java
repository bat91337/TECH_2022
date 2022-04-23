package ru.itmo.kotiki.entitites;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "kotiki")
public class Kotiki {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "birthday")
    private String date;
    @Column(name = "breed")
    private String breed;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private Colors color;
    @ManyToMany
    @JoinTable(name = "friendship",
            joinColumns = @JoinColumn(name = "idfirstkotik"),
            inverseJoinColumns = @JoinColumn(name = "idsecondkotik"))
    private Set<Kotiki> kotiks;

    public Kotiki(Long id, String name, String date, String breed, Owner owner, Colors color) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.breed = breed;
        this.owner = owner;
        this.color = color;
        this.kotiks = new HashSet<>();
    }

    public Kotiki() {

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

    public Owner getOwner() {
        return owner;
    }

    public Colors getColor() {
        return color;
    }

    public Set<Kotiki> getKotiks() {
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

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public void setKotiks(Set<Kotiki> kotiks) {
        this.kotiks = kotiks;
    }
}
