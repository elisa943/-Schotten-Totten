package com.schottenTotten.ai;

import com.schottenTotten.model.*;
import com.schottenTotten.controller.Color;
import java.util.Random;

public class AIPlayer extends Player {
    private Random random = new Random();

    public AIPlayer(String name, int id) {
        super(name, id);
    }

    public int[] getCardIndexFromAI(Border border, Player player) {
        int[] cardIndex = new int[2];

        // AI picks a random card (either from hand or tactic hand)
        int totalCards = player.getHand().size() + player.getTacticHand().size();
        cardIndex[0] = random.nextInt(totalCards);

        // AI picks a random border index, ensuring it's not full
        cardIndex[1] = -1;
        while (cardIndex[1] == -1) {
            cardIndex[1] = random.nextInt(border.NUM_BORDER_CARDS);
            if (border.isBorderIndexFull(cardIndex[1], player)) {
                cardIndex[1] = -1;
            }
        }

        return cardIndex;
    }

    public void configureTacticCard(TacticCard card) {
        if (card == null) return;

        // Randomize the value and color for dynamic cards
        if (card.getTacticCard() == TacticCards.JOKER) {
            int randomValue = random.nextInt(9) + 1; // [1, 9]
            Color randomColor = Color.values()[random.nextInt(Color.values().length)];
            card.applyTacticEffect(randomValue, randomColor);
        } else if (card.getTacticCard() == TacticCards.SPY) {
            Color randomColor = Color.values()[random.nextInt(Color.values().length)];
            card.applyTacticEffect(7, randomColor); // Spy is always value 7
        } else if (card.getTacticCard() == TacticCards.SHIELD_BEARER) {
            int randomValue = random.nextInt(3) + 1; // [1, 3]
            Color randomColor = Color.values()[random.nextInt(Color.values().length)];
            card.applyTacticEffect(randomValue, randomColor);
        }
    }

    public int pickDeck() {
        return random.nextInt(2) + 1; // [1, 2]: 1 for normal deck, 2 for tactic deck
    }
}
