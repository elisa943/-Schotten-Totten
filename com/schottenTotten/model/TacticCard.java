package com.schottenTotten.model;

import com.schottenTotten.model.TacticCards;
import com.schottenTotten.controller.Color;

public class TacticCard extends Card {
    private TacticCards tacticCard; // Type de carte tactique (JO, SB, etc.)

    public TacticCard(TacticCards tacticCard) {
        super();
        this.tacticCard = tacticCard;
        // Une carte tactique est dynamique par défaut
        this.setDynamicValue(true);
        this.setDynamicColor(true); 
    }

    public TacticCards getTacticCard() {
        return tacticCard;
    }

    public void applyTacticEffect(int value, Color color) {
        this.setNumber(value);
        this.setColor(color);

        // Une fois fixé, la valeur et la couleur ne peuvent plus changer
        this.setDynamicValue(false); 
        this.setDynamicColor(false); 
    }
}
