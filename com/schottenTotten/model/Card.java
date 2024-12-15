package com.schottenTotten.model;
import com.schottenTotten.controller.Color;

public class Card {
    protected int number; 
    protected Color color; 
    public static final int MAX_NUMBER = 9;

    // Indique si la valeur et la couleur peut changer (carte tactique)
    private boolean dynamicValue = false;
    private boolean dynamicColor = false; 
    
    public Card() {
        this.number = -1;
        this.color = null;
    }

    public Card(int number, Color color) {
        this.number = number;
        this.color = color;
    }

    public boolean isDynamicValue() {
        return dynamicValue;
    }

    public void setDynamicValue(boolean dynamicValue) {
        this.dynamicValue = dynamicValue;
    }

    public boolean isDynamicColor() {
        return dynamicColor;
    }

    public void setDynamicColor(boolean dynamicColor) {
        this.dynamicColor = dynamicColor;
    }

    public int getNumber() {
        return number;
    }

    public Color getColor() {
        return color;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static Card copy(Card card) {
        if (card == null) {
            return null; 
        }
        return new Card(card.getNumber(), card.getColor());
    }
}