package Controllers;

public interface Controller<Entity, Key> {
    void create();
    Entity read();
}
