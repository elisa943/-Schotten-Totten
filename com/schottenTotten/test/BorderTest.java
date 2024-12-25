package com.schottenTotten.test;
import com.schottenTotten.model.Border;
import com.schottenTotten.model.Player;
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
    }
}
