package com.schottenTotten.model;
import java.util.ArrayList;

public class TacticDeck extends Deck {
    public TacticDeck() {
        stack = new ArrayList<Card>();

        stack.add(new TacticCard(TacticCards.getByIndex(0)));  // Two Joker cards
        for (int i = 0; i < TacticCards.NUM_TACTIC_CARDS; i++) {
            stack.add(new TacticCard(TacticCards.getByIndex(i)));
        }

        shuffle();
    }

    public TacticDeck(Player player1, Player player2) {
        stack = new ArrayList<Card>();

        stack.add(new TacticCard(TacticCards.getByIndex(0)));  // Two Joker cards
        for (int i = 0; i < TacticCards.NUM_TACTIC_CARDS; i++) {
            stack.add(new TacticCard(TacticCards.getByIndex(i)));
        }

        shuffle();

        distributeTacticHand(player1);
        distributeTacticHand(player2);
    }

    public void distributeTacticHand(Player player) {
        ArrayList<TacticCard> tacticHand = new ArrayList<TacticCard>();
        TacticCard card;
        for (int i = 0; i < Player.NUM_TAC_CARDS; i++) {
            card = (TacticCard) getCard();
            tacticHand.add(card);
            stack.remove(card);
        }
        player.setTacticHand(tacticHand);
    }
}