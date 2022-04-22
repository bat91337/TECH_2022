package ru.itmo.kotiki.jdbc;

public interface Dao <Entity, Key> {
    void create(Entity entity);
    Entity read(Key key);
    void update(Entity entity);
    void delete(Entity entity);
}
