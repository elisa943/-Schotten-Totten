package com.schottenTotten.model;

public enum TacticCards {
    JOKER, SPY, SHIELD_BEARER;
    public static final int NUM_TACTIC_CARDS = 3;

    public static TacticCards getByIndex(int index) {
        return TacticCards.values()[index];
    }

    public static int getIndex(TacticCards card) {
        return card.ordinal();
    }

    public static String getTacticCardName(TacticCards card) {
        switch (card) {
            case JOKER:
                return "Joker";
            case SPY:
                return "Spy";
            case SHIELD_BEARER:
                return "Shield Bearer";
            default:
                return "";
        }
    }

    public static String getTacticCardAbr(TacticCards card) {
        switch (card) {
            case JOKER:
                return "JO";
            case SPY:
                return "SP";
            case SHIELD_BEARER:
                return "SB";
            default:
                return "";  
        }
    }
}