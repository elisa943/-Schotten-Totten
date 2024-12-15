package com.schottenTotten.model;
import java.util.ArrayList;
import com.schottenTotten.controller.Card_Combination;



public class Border {
    public static final int NUM_BORDER_CARDS = 9;
    private int[] border;
    private Card_Combination[][] combinations;

    public Border() {
        border = new int[NUM_BORDER_CARDS];
        combinations = new Card_Combination[2][NUM_BORDER_CARDS];
        for (int i = 0; i < NUM_BORDER_CARDS; i++) {
            border[i] = 0;
        }
        for (int i = 0; i < NUM_BORDER_CARDS; i++){
            combinations[0][i] = new Card_Combination();
            combinations[1][i] = new Card_Combination();
        }
    }

    public int getBorder(int index) {
        return border[index];
    }

    public void setBorder(int value, int index) {
        border[index] = value;
    }

    public void setCombinations(Card card, int i, int j) {
        combinations[i][j].addCard(card);
    }

    public int getBorderSize(int value) {
        int count = 0;
        for (int i = 0; i < NUM_BORDER_CARDS; i++) {
            if (border[i] == value) {
                count++;
            }
        }
        return count;
    }

    public boolean isBorderIndexFull(int index, Player player) {
        int playerID = player.getId() == 0 ? 0 : player.getId() - 1;
        return (combinations[playerID][index].getCardSize() >= 3);
    }

    public Card_Combination getCombinations(int i, int j) {
        return combinations[i][j];
    }

    public ArrayList<Integer> bordersControlledBy(Player player) {
        /* Returns the indexes of the borders controlled by the player */
        ArrayList<Integer> controlledBorders = new ArrayList<>(); 
        int playerID = player.getId() == 0 ? 1 : player.getId();

        for (int i = 0; i < NUM_BORDER_CARDS; i++) {
            if (border[i] == playerID) 
            {
                controlledBorders.add(i);
            }
        }

        return controlledBorders;
    }

    public boolean validMove(Player player, int indexBorder, Card card) {
        /* Returns true if the move is valid */
        int indexPlayer = player.getId() == 0 ? 0 : player.getId() - 1;
        boolean isTactic = card.getNumber() == -1;
        
        // Checks if the border is already controlled by the player or full 
        if (border[indexBorder] != 0 || isBorderIndexFull(indexBorder, player)) {
            return false;
        }

        // If card is a Tactical Card, checks how many tactical cards are already in the border (other player included)
        if (isTactic) {
            int countTactical_1 = 0; 
            int countTactical_2 = 0;
            for (int i = 0; i < combinations[indexPlayer][indexBorder].getCardSize(); i++) {
                if (combinations[indexPlayer][indexBorder].getCard(i).getNumber() == -1) {
                    countTactical_1++;
                }
            }
            for (int i = 0; i < combinations[1 - indexPlayer][indexBorder].getCardSize(); i++) {
                if (combinations[1 - indexPlayer][indexBorder].getCard(i).getNumber() == -1) {
                    countTactical_2++;
                }
            }

            return !(countTactical_1 > countTactical_2);
        }

        return true;
    }

    public boolean gameWon(Player player) {
        /* Returns true if the game is won by the player */

        // 5 wall cards
        int playerID = player.getId() == 0 ? 1 : player.getId();
        if (getBorderSize(playerID) >= 5) {
            return true;
        }

        // 3 adjacent cards
        for (int i = 0; i < NUM_BORDER_CARDS - 2; i++) {
            if (border[i] == playerID && border[i + 1] == playerID && border[i + 2] == playerID) {
                return true;
            }
        }

        return false; 
    }
}