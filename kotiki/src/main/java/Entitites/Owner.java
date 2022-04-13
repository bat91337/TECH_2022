package Entitites;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "owners")
public class Owner {
    public Owner(Long id, String name, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.kotiks = new ArrayList<Kotiki>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDate() {
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

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setKotiks(List<Kotiki> kotiks) {
        this.kotiks = kotiks;
    }
    @Column(name = "name")
    private String name;
    @Column(name = "birthday")
    private LocalDateTime date;
    @OneToMany(mappedBy = "id", cascade=CascadeType.ALL, orphanRemoval=false)
    private List<Kotiki> kotiks;

}
