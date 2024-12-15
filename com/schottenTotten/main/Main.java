package com.schottenTotten.main;

import java.util.Scanner;
import com.schottenTotten.view.UserInterface;
import com.schottenTotten.model.Board;
import com.schottenTotten.model.TacticalVariant;

public class Main {
    public static Scanner scanner;

    public static void main(String[] args) {
        int choice = -1; 
        int variant = -1;
        int ai = -1;
        String name1 = "";
        String name2 = "";

        // Display the main menu
        while (choice == -1 && variant == -1 && ai == -1) {
            choice = UserInterface.pick_menu();

            if (choice == 1) {
                variant = UserInterface.pick_variant();
                ai = UserInterface.pick_ai(); // 1 for AI, 2 for 2 players
            }
        }

        // Player names
        if (ai == 1) {
            name1 = UserInterface.askPlayerName("Player 1");
            name2 = "AI";
        } else {
            name1 = UserInterface.askPlayerName("Player 1");
            name2 = UserInterface.askPlayerName("Player 2");
        }

        // Lauching the game
        if (variant == 1) {
            // Classic mode
            System.out.println("Starting the game in Classic Mode...");
            Board board = new Board(ai == 1, name1, name2);
            board.startGame();
        } else if (variant == 2) {
            // Tactical variant
            System.out.println("Starting the game in Tactical Variant...");
            TacticalVariant board = new TacticalVariant(ai == 1, name1, name2);
            board.startGame();
        }

        UserInterface.close();
    }
}
