package Entitites;

import javax.persistence.*;

public class Friendship {
    private Kotiki firstKotik;
    @ManyToMany
    @JoinColumn(referencedColumnName = "idfirstkotik")
    private Kotiki secondKotik;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idFriendShip;

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
