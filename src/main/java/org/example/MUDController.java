package org.example;
import org.example.Entity.NPC;
import org.example.Entity.Player;
import org.example.Entity.Room;

import java.util.*;

public class MUDController {
    private Player player;
    private boolean running;
    private Scanner scanner;

    public MUDController(Player player) {
        this.player = player;
        this.running = true;
        this.scanner = new Scanner(System.in);
    }

    public void runGameLoop() {
        System.out.println("Welcome to the MUD game!");
        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();
            handleInput(input);
        }
    }

    private void handleInput(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String argument = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "look":
                lookAround();
                break;
            case "move":
                move(argument);
                break;
            case "pick":
                if (argument.startsWith("up ")) {
                    pickUp(argument.substring(3));
                } else {
                    System.out.println("Unknown command! Did you mean 'pick up <item>'?");
                }
                break;
            case "inventory":
                checkInventory();
                break;
            case "talk":
                talkToNPC(argument);
                break;
            case "help":
                showHelp();
                break;
            case "quit":
            case "exit":
                running = false;
                System.out.println("Exiting game...");
                break;
            default:
                System.out.println("Unknown command.");
        }
    }

    private void lookAround() {
        Room room = player.getCurrentRoom();
        System.out.println(room.describe());
        System.out.println("Items here: " + room.getItems());
    }

    private void move(String direction) {
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        if (nextRoom != null) {
            player.setCurrentRoom(nextRoom);
            System.out.println("You moved " + direction + ".");
            lookAround();
        } else {
            System.out.println("You can't go that way!");
        }
    }

    private void pickUp(String itemName) {
        Room currentRoom = player.getCurrentRoom();
        List<Item> items = currentRoom.getItems();

        Item itemToPick = null;
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                itemToPick = item;
                break;
            }
        }

        if (itemToPick != null) {
            player.addItem(itemToPick);
            currentRoom.removeItem(itemToPick);
            System.out.println("You picked up the " + itemToPick.getName() + ".");
        } else {
            System.out.println("No item named '" + itemName + "' here!");
        }
    }

    private void checkInventory() {
        List<Item> inventory = player.getInventory();
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("You are carrying:");
            for (Item item : inventory) {
                System.out.println("- " + item.getName());
            }
        }
    }

    private void talkToNPC(String npcName) {
        Room room = player.getCurrentRoom();
        for (NPC npc : room.getNpcs()) {
            if (npc.getName().equalsIgnoreCase(npcName)) {
                System.out.println(npc.talk());
                return;
            }
        }
        System.out.println("No one named " + npcName + " here!");
    }

    private void showHelp() {
        System.out.println("Available commands:");
        System.out.println("  look - Describe the current room");
        System.out.println("  move <direction> - Move in a direction (not implemented yet)");
        System.out.println("  pick up <item> - Pick up an item");
        System.out.println("  inventory - Show your inventory");
        System.out.println("  talk <NPC> - Talk to an NPC");
        System.out.println("  help - Show this help message");
        System.out.println("  quit/exit - Exit the game");
    }
}

