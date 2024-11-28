public class TacticDeck extends Deck {
    public TacticDeck() {
        stack.add(new TacticCard(TacticCards.getByIndex(0)));  // Two Joker cards
        for (int i = 0; i < TacticCards.NUM_TACTIC_CARDS; i++) {
            stack.add(new TacticCard(TacticCards.getByIndex(i)));
        }
    }
}