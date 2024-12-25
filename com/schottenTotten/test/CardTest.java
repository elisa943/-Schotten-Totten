package com.schottenTotten.test;
import com.schottenTotten.model.Card;
import com.schottenTotten.controller.Color;

public class CardTest {
    public static void main(String[] args) {
        Card card = new Card(1, Color.RED);
        
        if (card.getNumber() == 1 && card.getColor() == Color.RED) {
            System.out.println("Card created successfully");
        } else {
            System.out.println("Error: Card creation failed");
        }
        
        card.setNumber(2);
        card.setColor(Color.BLUE);
        
        if (card.getNumber() == 2 && card.getColor() == Color.BLUE) {
            System.out.println("Card attributes changed successfully");
        } else {
            System.out.println("Error: Card attributes change failed");
        }
    }
}
