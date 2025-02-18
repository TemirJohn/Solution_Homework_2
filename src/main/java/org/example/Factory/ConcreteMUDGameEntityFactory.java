package org.example.Factory;

import org.example.Item;
import org.example.Entity.Player;
import org.example.Entity.Room;

public class ConcreteMUDGameEntityFactory extends MudGameEntityFactory {
    public Player createPlayer(Room startRoom) {
        return new Player(startRoom);
    }

    public Room createRoom(String name, String description) {
        return new Room(name, description);
    }

    public Item createItem(String name, String description) {
        return new Item(name, description);
    }
}
