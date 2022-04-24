package ru.itmo.kotiki.entitites;

import javax.persistence.*;

@Entity
@Table(name = "friend_Ship")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idFriendShip;
    @OneToOne
    @JoinColumn(name = "first_kotik_id")
    private Kotiki firstKotik;
    @OneToOne
    @JoinColumn(name = "second_kotik_id")
    private Kotiki secondKotik;

    public Friendship() {

    }

    public void setFirstKotik(Kotiki firstKotik) {
        this.firstKotik = firstKotik;
    }

    public void setSecondKotik(Kotiki secondKotik) {
        this.secondKotik = secondKotik;
    }

    public void setIdFriendShip(Long idFriendShip) {
        this.idFriendShip = idFriendShip;
    }

    public Kotiki getFirstKotik() {
        return firstKotik;
    }

    public Kotiki getSecondKotik() {
        return secondKotik;
    }

    public Long getIdFriendShip() {
        return idFriendShip;
    }

    public Friendship(Kotiki firstKotik, Kotiki secondKotik, Long idFriendShip) {
        this.firstKotik = firstKotik;
        this.secondKotik = secondKotik;
        this.idFriendShip = idFriendShip;
    }
}
