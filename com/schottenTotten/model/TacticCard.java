package com.schottenTotten.model;
import com.schottenTotten.model.TacticCards;
import com.schottenTotten.model.TacticCard;

public class TacticCard extends Card {
    private TacticCards tacticCard;

    public TacticCard(TacticCards tacticCard) {
        super();
        this.tacticCard = tacticCard;
    }

    public TacticCards getTacticCard() {
        return tacticCard;
    }

    public void setTacticCard(TacticCards tacticCard) {
        this.tacticCard = tacticCard;
    }
}