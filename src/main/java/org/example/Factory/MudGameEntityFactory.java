package org.example.Factory;

import org.example.Item;
import org.example.Entity.Player;
import org.example.Entity.Room;

public abstract class MudGameEntityFactory {
    public abstract Player createPlayer(Room startRoom);
    public abstract Room createRoom(String name, String description);
    public abstract Item createItem(String name, String description);

}
