package Controllers;

import javax.swing.text.html.parser.Entity;
import java.security.Key;

public interface Conroller<Entity, Key> {
    void create();
    Entity read();
}
