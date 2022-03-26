package jdbc;

import Entitites.Owner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class OwnerDAO implements DAO<Owner,Long>  {

    private final SessionFactory factory;

    public OwnerDAO(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Owner owner) {
        try(Session session = factory.openSession())
        {
            session.beginTransaction();
            session.save(owner);
            session.getTransaction().commit();
        }
    }

    @Override
    public Owner read(Long s) {
        try (Session session = factory.openSession()) {
            return session.get(Owner.class, s);
        }
    }

    @Override
    public void update(Owner owner) {
        try(Session session = factory.openSession())
        {
            session.beginTransaction();
            session.update(owner);
            session.getTransaction().commit();
        }

    }

    @Override
    public void delete(Owner owner) {
        try(Session session = factory.openSession())
        {
            session.beginTransaction();
            session.delete(owner);
            session.getTransaction().commit();
        }

    }
}
