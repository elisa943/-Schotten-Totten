package com.schottenTotten.test;

import com.schottenTotten.controller.Color;
import com.schottenTotten.model.Border;
import com.schottenTotten.model.Player;
import com.schottenTotten.model.Card;
import com.schottenTotten.controller.Card_Combination;

public class BorderTest {
    public static void main(String[] args) {
        Border border = new Border();

        if (border.getBorder(0) != 0) {
            System.out.println("Error: getBorder(0) should return 0");
        } else {
            System.out.println("Border created successfully");
        }

        border.setBorder(1, 0);

        if (border.getBorder(0) != 1) {
            System.out.println("Error: setBorder(1, 0) should set border[0] to 1");
        } else {
            System.out.println("Border set successfully");
        }

        border.setBorder(1, 1);
        border.setBorder(1, 2);
        Player player = new Player("Player 1", 1);

        if (border.gameWon(player)) {
            System.out.println("Game won successfully");
        } else {
            System.out.println("Error: gameWon should return false");
        }

        // Vérifier la gestion des revendications de bornes
        border.setBorder(0, 0);
        border.setBorder(0, 1);
        border.setBorder(0, 2);

        Card card1 = new Card(1, Color.RED);
        Card card2 = new Card(2, Color.GREEN);
        Card card3 = new Card(3, Color.RED);
        Card card4 = new Card(2, Color.RED);
        Card card5 = new Card(3, Color.GREEN);
        Card card6 = new Card(4, Color.RED);

        border.setCombinations(card1, 0, 0);
        border.setCombinations(card2, 0, 0);
        border.setCombinations(card3, 0, 0);
        border.setCombinations(card4, 1, 0);
        border.setCombinations(card5, 1, 0);
        border.setCombinations(card6, 1, 0);

        Card_Combination combination = border.getCombinations(0, 0);
        Card_Combination combination2 = border.getCombinations(1, 0); // best combination

        if (combination.isBetter(combination2)) {
            System.out.println("Error: isBetter should return false");
        } else {
            System.out.println("isBetter works correctly");
        }
    }
}
