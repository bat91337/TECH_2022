package ru.itmo.kotiki.entitites;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owners")
public class Owner {
    public Owner(Long id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.kotiks = new ArrayList<Kotiki>();
    }

    public Owner() {

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @Column(name = "name")
    private String name;
    @Column(name = "birthday")
    private String date;
    @OneToMany(mappedBy = "id", cascade=CascadeType.ALL, orphanRemoval=false)
    private List<Kotiki> kotiks;
}
