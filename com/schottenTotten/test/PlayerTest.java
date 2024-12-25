package com.schottenTotten.test; 
import com.schottenTotten.model.Player;

public class PlayerTest {
    public static void main(String[] args) {
        Player player = new Player("Player 1", 1);
        
        if (player.getName().equals("Player 1") && player.getId() == 1) {
            System.out.println("Player created successfully");
        } else {
            System.out.println("Error: Player creation failed");
        }
        

        player.setName("Player 2");

        if (player.getName().equals("Player 2")) {
            System.out.println("Player name changed successfully");
        } else {
            System.out.println("Error: Player name change failed");
        }

        player.setId(2);

        if (player.getId() == 2) {
            System.out.println("Player id changed successfully");
        } else {
            System.out.println("Error: Player id change failed");
        }
    }
}