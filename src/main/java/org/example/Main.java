package org.example;

import org.example.Entity.NPC;
import org.example.Entity.Player;
import org.example.Entity.Room;

public class Main {
    public static void main(String[] args) {
        Room startRoom = new Room("Village Square", "A peaceful village square with a fountain.");
        Room tavern = new Room("Tavern", "A cozy place with a warm fireplace.");

        Item apple = new Item("Apple", "Eat");
        startRoom.addItem(apple);

        NPC oldMan = new NPC("Old Man", "Hello, traveler! The road ahead is dangerous.");
        startRoom.addNPC(oldMan);

        NPC bartender = new NPC("Bartender", "What will you have? Ale or water?");
        tavern.addNPC(bartender);

        Player player = new Player(startRoom);

        MUDController controller = new MUDController(player);
        controller.runGameLoop();
    }
}
