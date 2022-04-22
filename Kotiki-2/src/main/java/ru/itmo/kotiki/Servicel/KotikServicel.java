package ru.itmo.kotiki.Servicel;

import ru.itmo.kotiki.entitites.Kotiki;
import ru.itmo.kotiki.jdbc.KotikiDao;

public class KotikServicel implements Servicel<Kotiki, Long>{
    private KotikiDao kotikDAO;

    public KotikServicel(KotikiDao kotikDAO) {
        this.kotikDAO = kotikDAO;
    }

    @Override
    public void create(Kotiki kotiki) {
        kotikDAO.create(kotiki);
    }

    @Override
    public Kotiki read(Long aLong) {
        Kotiki result = kotikDAO.read(aLong);
        return result;
    }

    @Override
    public void update(Kotiki kotiki) {
        kotikDAO.update(kotiki);
    }

    @Override
    public void delete(Kotiki kotiki) {
        kotikDAO.delete(kotiki);
    }
}
