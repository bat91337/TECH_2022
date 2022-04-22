package ru.itmo.kotiki.Servicel;

import ru.itmo.kotiki.entitites.Owner;
import ru.itmo.kotiki.jdbc.OwnerDao;

public class OwnerServicel implements Servicel<Owner,Long>{
    private OwnerDao ownerDAO;

    public OwnerServicel(OwnerDao ownerDAO) {
        this.ownerDAO = ownerDAO;
    }

    @Override
    public void create(Owner owner) {
        ownerDAO.create(owner);

    }

    @Override
    public Owner read(Long aLong) {
        Owner owner = ownerDAO.read(aLong);
        return owner;
    }

    @Override
    public void update(Owner owner) {
        ownerDAO.update(owner);

    }

    @Override
    public void delete(Owner owner) {
        ownerDAO.delete(owner);

    }
}
