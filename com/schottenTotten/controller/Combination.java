package com.schottenTotten.controller;

public enum Combination {
    SUM, STRAIGHT, FLUSH, THREE_OF_A_KIND, STRAIGHT_FLUSH;

    public static final int NUM_COMBINATIONS = 5;

    public static Combination getByIndex(int index) {
        return Combination.values()[index];
    }

    public static int getIndex(Combination combination) {
        return combination.ordinal();
    }
};