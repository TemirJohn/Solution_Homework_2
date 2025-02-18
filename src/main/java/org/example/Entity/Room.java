package org.example.Entity;

import org.example.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
    private String name;
    private String description;
    private Map<String, Room> exits;
    private List<Item> items;
    private List<NPC> npcs;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
        this.npcs = new ArrayList<>();
    }

    public void setExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void addNPC(NPC npc) {
        npcs.add(npc);
    }

    public List<NPC> getNpcs() {
        return npcs;
    }

    public String describe() {
        StringBuilder desc = new StringBuilder(name + ": " + description + "\nItems here: ");

        if (!items.isEmpty()) {
            desc.append("Items here: ");
            for (Item item : items) {
                desc.append(item.getName()).append(", ");
            }
            desc.setLength(desc.length() - 2);
            desc.append("\n");
        }

        desc.append("\nNPCs here: ");
        if (npcs.isEmpty()) {
            desc.append("None");
        } else {
            for (NPC npc : npcs) {
                desc.append(npc.getName()).append(", ");
            }
            desc.setLength(desc.length() - 2);
        }

        return desc.toString();
    }
}
