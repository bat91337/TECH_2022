package ru.itmo.kotiki.jdbc;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.itmo.kotiki.entitites.Kotiki;

public class KotikiDao implements Dao<Kotiki, Long>{

    private final SessionFactory factory;

    public KotikiDao(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Kotiki kotiki) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.save(kotiki);
            session.getTransaction().commit();
        }
    }

    @Override
    public Kotiki read(Long s) {
        try (Session session = factory.openSession()) {
            Kotiki result = session.get(Kotiki.class, s);
            if (result != null){
                Hibernate.initialize(result.getOwner());
            }
            return result;
        }
    }

    @Override
    public void update(Kotiki kotiki) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(kotiki);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Kotiki kotiki) {
        try(Session session = factory.openSession())
        {
            session.beginTransaction();
            session.delete(kotiki);
            session.getTransaction().commit();
        }

    }
}
