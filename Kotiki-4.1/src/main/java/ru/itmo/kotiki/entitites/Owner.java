package ru.itmo.kotiki.entitites;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "birthday")
    private String date;
    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    private List<Kotiki> kotiks;
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users user;
    public Owner(Long id, String name, String date, Users user) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.kotiks = new ArrayList<Kotiki>();
        this.user = user;
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

    public Users getUser() {
        return user;
    }
}
