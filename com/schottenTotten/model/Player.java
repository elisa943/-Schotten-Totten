package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.Scanner;
import com.schottenTotten.controller.Color;
import com.schottenTotten.controller.Card_Combination; 
import com.schottenTotten.view.ColoredText;
import java.util.InputMismatchException;

public class Player {
    // Attributes
    protected int id;
    protected String name; 
    protected ArrayList<Card> hand;
    protected ArrayList<TacticCard> tacticHand;
    public static final int MAX_NUM_TAC_CARDS = 7;
    public static final int NUM_TAC_CARDS = 0;

    public Player(String name, int id) {
        this.name = name;
        this.id = id; 
        this.tacticHand = new ArrayList<>();
    }

    // Methodes
    public void addCardToHand(Card card) {
        if (card != null) {
            hand.add(card);
        }
    }
    
    public void addCardToTacticHand(TacticCard card) {
        if (card != null) {
            tacticHand.add(card);
        }
    }

    public void removeCardFromHand(Card card) {
        hand.remove(card);
    }

    public void removeCardFromTacticHand(TacticCard card) {
        tacticHand.remove(card);
    }
    
    // Getters et setters
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setTacticHand(ArrayList<TacticCard> tacticHand) {
        this.tacticHand = tacticHand;
    }

    public ArrayList<TacticCard> getTacticHand() {
        return tacticHand;
    }

    public Card getCardFromHand(int index) {
        return hand.get(index);
    }

    public TacticCard getTacticCardFromHand(int index) {
        return tacticHand.get(index);
    }

    public int[] getCardIndexFromUser(Border border) {
        /* Returns the card index (starts at 0) picked by the player */
        Scanner scanner = new Scanner(System.in);
        int cardNumber = -1; 
        int borderNumber = -1;

        while (cardNumber <= 0 || cardNumber > hand.size()) {
            try {
                int total_size = hand.size() + tacticHand.size();
                System.out.print("Choose a card to play [1-" + total_size + "]: ");

                if (scanner.hasNextInt()) {
                    cardNumber = scanner.nextInt();
                } else {
                    scanner.next(); // Clear the invalid input
                    throw new InputMismatchException();
                }
                if (cardNumber >= 1 && cardNumber <= hand.size() + tacticHand.size()) {
                    break;
                }
            } catch (InputMismatchException e) {
                cardNumber = -1;
                scanner.next();
            }
        }
        
        while (borderNumber <= 0 || borderNumber > Border.NUM_BORDER_CARDS) {
            try {
                System.out.print("Choose a border to put the card: ");
                borderNumber = scanner.nextInt();

                if (borderNumber >= 1 && borderNumber <= Border.NUM_BORDER_CARDS) {
                    if (!border.isBorderIndexFull(borderNumber - 1, this)) {
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                borderNumber = -1;
                scanner.next();
            }
        }

        //scanner.close();
        return new int[] { cardNumber - 1, borderNumber - 1 };
    }

    public void setDynamicCardValueAndColor(Card card, int value, Color color) {
        if (card instanceof TacticCard && (card.isDynamicValue() || card.isDynamicColor())) {
            ((TacticCard) card).applyTacticEffect(value, color);
        }
    }

    public boolean hasJokerInPlay(Border border) {
        int playerID = this.getId() == 0 ? 0 : this.getId() - 1;
        for (int i = 0; i < Border.NUM_BORDER_CARDS; i++) {
            Card_Combination combination = border.getCombinations(playerID, i);
            for (int j = 0; j < combination.getCardSize(); j++) {
                Card card = combination.getCard(j);
                if (card instanceof TacticCard && ((TacticCard) card).getTacticCard() == TacticCards.JOKER) {
                    return true; // Joker already in play
                }
            }
        }
        return false; // No joker in play
    }

    public void selectValueAndColorForCard(TacticCard card) {
        Scanner scanner = new Scanner(System.in);

        // Ask for the value
        int value = -1;
        if (card.getTacticCard() == TacticCards.SHIELD_BEARER || card.getTacticCard() == TacticCards.JOKER) {
            System.out.print("Select a value for your " + TacticCards.getTacticCardName(card.getTacticCard()));
            if (card.getTacticCard() == TacticCards.SHIELD_BEARER) {
                System.out.print(" [1, 2, or 3]");
            } else if (card.getTacticCard() == TacticCards.JOKER) {
                System.out.print(" [1 to 9]");
            }
            System.out.print(" : ");

            while (value <= 0 || value > 9 || (card.getTacticCard() == TacticCards.SHIELD_BEARER && value > 3)) {
                value = scanner.nextInt();
                if (value <= 0 || value > 9 || (card.getTacticCard() == TacticCards.SHIELD_BEARER && value > 3)) {
                    System.out.println("Invalid value, please try again:");
                }
            }
        } else if (card.getTacticCard() == TacticCards.SPY) {
            value = 7; // Spy has a fixed value of 7
        }

        // Ask for the color
        for (int i = 0; i < Color.values().length; i++) {
            System.out.println(i + ": " + Color.values()[i]);
        }
        System.out.print("Select a color for your " + TacticCards.getTacticCardName(card.getTacticCard()) + ":");

        int colorIndex = -1;
        while (colorIndex < 0 || colorIndex >= Color.values().length) {
            colorIndex = scanner.nextInt();
            if (colorIndex < 0 || colorIndex >= Color.values().length) {
                System.out.println("Invalid color, please try again:");
            }
        }
        Color selectedColor = Color.values()[colorIndex];

        // Apply the effect
        card.applyTacticEffect(value, selectedColor);

        System.out.println("Card set to value " + value + " and color " + selectedColor + ".");
    }


}
