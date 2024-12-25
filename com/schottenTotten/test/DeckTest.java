package com.schottenTotten.test;

import com.schottenTotten.model.Deck;
import com.schottenTotten.controller.Color;

public class DeckTest {
    public static void main(String[] args) {
        Deck deck = new Deck();
        int NUM_COLORS = Color.values().length;
        
        if (deck.getStackSize() != Deck.NUM_NUMBERS * NUM_COLORS) {
            System.out.println("Error: the deck should contain " + Deck.NUM_NUMBERS * NUM_COLORS + " cards");
        }   else {
            System.out.println("The deck contains " + Deck.NUM_NUMBERS * NUM_COLORS + " cards");
        }

        deck.getCard();

        if (deck.getStackSize() != Deck.NUM_NUMBERS * NUM_COLORS - 1) {
            System.out.println("Error: the deck should contain " + (Deck.NUM_NUMBERS * NUM_COLORS - 1) + " cards");
        }   else {
            System.out.println("The deck contains " + (Deck.NUM_NUMBERS * NUM_COLORS - 1) + " cards");
        }

        for (int i = 0; i < Deck.NUM_NUMBERS * NUM_COLORS - 1; i++) {
            deck.getCard();
        }

        if (deck.isEmpty()) {
            System.out.println("The deck is empty");
        }   else {
            System.out.println("Error: the deck should be empty");
        }
    }
}