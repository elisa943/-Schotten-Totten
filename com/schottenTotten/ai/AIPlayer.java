package com.schottenTotten.ai;
import com.schottenTotten.model.Player;
import com.schottenTotten.model.Border;

public class AIPlayer extends Player {
    public AIPlayer(String name, int id) {
        super(name, id);
    }

    public int[] getCardIndexFromAI(Border border, Player player) {
        int[] cardIndex = new int[2];
        
        // AI picks a random cards from the player's hand 
        cardIndex[0] = (int) (Math.random() * player.getHand().size());
    
        // AI picks a random border card and checks if it is full or not
        cardIndex[1] = -1;
        while (cardIndex[1] == -1) {
            cardIndex[1] = (int) (Math.random() * border.NUM_BORDER_CARDS);
            System.out.println("AI picked card number: " + cardIndex[0]);
            System.out.println("AI picked border card: " + cardIndex[1]);

            if (border.isBorderIndexFull(cardIndex[1], player)) {
                cardIndex[1] = -1;
            }
        }

        return cardIndex;
    }
}