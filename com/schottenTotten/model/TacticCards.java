package com.schottenTotten.model;

public enum TacticCards {
    JOKER, SPY, SHIELD_BEARER, BLIND_MAN_BLUFF, MUD_FIGHT, RECRUITER, STRATEGIST, BANSHEE, TRAITOR;
    public static final int NUM_TACTIC_CARDS = 9;

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
            case BLIND_MAN_BLUFF:
                return "Blind-Man Bluff";
            case MUD_FIGHT:
                return "Mud Fight";
            case RECRUITER:
                return "Recruiter";
            case STRATEGIST:
                return "Strategist";
            case BANSHEE:
                return "Banshee";
            case TRAITOR:
                return "Traitor";
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
            case BLIND_MAN_BLUFF:
                return "BB";
            case MUD_FIGHT:
                return "MF";
            case RECRUITER:
                return "RE";
            case STRATEGIST:
                return "ST";
            case BANSHEE:
                return "BA";
            case TRAITOR:
                return "TR";  
            default:
                return "";  
        }
    }
}