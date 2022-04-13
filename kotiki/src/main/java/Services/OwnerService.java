package Services;

import Entitites.Owner;
import jdbc.OwnerDao;

public class OwnerService implements Service<Owner,Long>{
    private OwnerDao ownerDAO;

    public OwnerService(OwnerDao ownerDAO) {
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
