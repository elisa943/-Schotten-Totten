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

        while (choice == -1 && variant == -1 && ai == -1) {
            choice = UserInterface.pick_menu();

            if (choice == 1) {
                variant = UserInterface.pick_variant();

                if (variant == 1) {
                    ai = UserInterface.pick_ai();
                }
            }
        }

        if (ai == 1) {
            name1 = UserInterface.askPlayerName("Player 1");
            name2 = "AI";
        } else {
            name1 = UserInterface.askPlayerName("Player 1");
            name2 = UserInterface.askPlayerName("Player 2");
        }

        if (variant == 1) {
            // Variant not selected 
            System.out.println("Starting the game...");
            Board board = new Board(ai == 1, name1, name2);
            board.startGame();
        } else if (variant == 2) {
            // Variant selected
            TacticalVariant board = new TacticalVariant(ai == 1, name1, name2);
            board.startGame();
        }

        UserInterface.close();
    }
    

}