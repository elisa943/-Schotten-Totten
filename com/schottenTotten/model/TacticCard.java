package com.schottenTotten.model;
import com.schottenTotten.controller.Color;

public class TacticCard extends Card {
    private TacticCards tacticCard; // Type of tactic card

    public TacticCard(TacticCards tacticCard) {
        super();
        this.tacticCard = tacticCard;
        // A tactical card is dynamic by default
        this.setDynamicValue(true);
        this.setDynamicColor(true); 
    }

    public TacticCards getTacticCard() {
        return tacticCard;
    }

    public void applyTacticEffect(int value, Color color) {
        this.setNumber(value);
        this.setColor(color);

        // Once the effect is applied, the card is no longer dynamic
        this.setDynamicValue(false); 
        this.setDynamicColor(false); 
    }
}
