package com.schottenTotten.test;
import com.schottenTotten.model.Board;

public class BoardTest {
    public static void main(String[] args) {
        // Create a new board
        Board board = new Board(false, "Player 1", "Player 2");
        
        // Test the creation of the board
        if (board.getPlayer1().getName().equals("Player 1") && board.getPlayer1().getId() == 1 &&
            board.getPlayer2().getName().equals("Player 2") && board.getPlayer2().getId() == 2) {
            System.out.println("Board created successfully");
        } else {
            System.out.println("Error: Board creation failed");
        }
        
        // Test the change of the player's name
        board.getPlayer1().setName("Player 3");
        board.getPlayer2().setName("Player 4");
        
        if (board.getPlayer1().getName().equals("Player 3") && board.getPlayer2().getName().equals("Player 4")) {
            System.out.println("Player name changed successfully");
        } else {
            System.out.println("Error: Player name change failed");
        }
        
        // Test the change of the player's id
        board.getPlayer1().setId(3);
        board.getPlayer2().setId(4);
        
        if (board.getPlayer1().getId() == 3 && board.getPlayer2().getId() == 4) {
            System.out.println("Player id changed successfully");
        } else {
            System.out.println("Error: Player id change failed");
        }
    }
}
