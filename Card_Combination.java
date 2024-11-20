import java.util.ArrayList;

public class Card_Combination {
    private ArrayList<Card> cards;
    public static final int NUM_CARDS = 3;

    public Card_Combination() {
        cards = new ArrayList<Card>();
    }

    public Card getCard(int index) {
        if (cards.size() <= index) {
            return null;
        } 
        return cards.get(index);
    }

    public int getCardSize() {
        return cards.size();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void sort() {
        // for (int i = 0; i < NUM_CARDS; i++) {
        //     for (int j = i + 1; j < NUM_CARDS; j++) {
        //         if (cards.get(i).getNumber() > cards.get(j).getNumber()) {
        //             // Card temp = cards.get(i);
        //             // cards.get(i) = cards.get(j);
        //             // cards.get(j) = temp;
        //         }
        //     }
        // }
    }

    public boolean isStraightFlush() {
        return isStraight() && isFlush();
    }

    public boolean maybeStraightFlush(int highestNumber) {
        return maybeStraight(highestNumber) && maybeFlush();
    }

    public boolean isThreeOfAKind() {
        return cards.get(0).getNumber() == cards.get(1).getNumber() && cards.get(1).getNumber() == cards.get(2).getNumber();
    }

    public boolean maybeThreeOfAKind(int number) {
        if (getCardSize() < 2) {
            return getCard(0).getNumber() >= number;
        }
        sort();
        return getCard(0).getNumber() == getCard(1).getNumber();
    }

    public boolean isFlush() {
        return cards.get(0).getColor() == cards.get(1).getColor() && cards.get(1).getColor() == cards.get(2).getColor();
    }

    public boolean maybeFlush() {
        if (getCardSize() < 2) {
            return true;
        }
        sort();
        return getCard(0).getColor() == getCard(1).getColor();
    }

    public boolean isStraight() {
        return cards.get(0).getNumber() + 1 == cards.get(1).getNumber() && cards.get(1).getNumber() + 1 == cards.get(2).getNumber();
    }

    public boolean maybeStraight(int highestNumber) {
        if (getCardSize() < 2) {
            return getCard(0).getNumber() >= highestNumber - NUM_CARDS + 1;
        }
        sort();
        return getCard(0).getNumber() + 1 == getCard(1).getNumber();
    }

    public int sum() {
        return cards.get(0).getNumber() + cards.get(1).getNumber() + cards.get(2).getNumber();
    }

    public Combination getCombination() {
        sort();
        if (isStraightFlush()) {
            return Combination.STRAIGHT_FLUSH;
        } else if (isThreeOfAKind()) {
            return Combination.THREE_OF_A_KIND;
        } else if (isFlush()) {
            return Combination.FLUSH;
        } else if (isStraight()) {
            return Combination.STRAIGHT;
        } else {
            return Combination.SUM;
        }
    }

    public static boolean betterCombination(Card_Combination completeCombination, Card_Combination cardCombination) {
        /* Returns true if cardCombination might have a better combination */ 
        Combination combination = completeCombination.getCombination();
        int num_cards = cardCombination.getCardSize();

        for (int i = Combination.NUM_COMBINATIONS - 1; i >= Combination.getIndex(combination); i--) {
            switch (Combination.getByIndex(i)) {
                case STRAIGHT_FLUSH: 
                    if (cardCombination.maybeStraightFlush(completeCombination.getCard(NUM_CARDS - 1).getNumber())) {
                        return true;
                    }
                    break; 

                case THREE_OF_A_KIND: 
                    if (cardCombination.maybeThreeOfAKind(completeCombination.getCard(0).getNumber())) {
                        return true;
                    }
                    break; 

                case FLUSH: 
                    if (cardCombination.maybeFlush()) {
                        return true;
                    }
                    break; 

                case STRAIGHT: 
                    if (cardCombination.maybeStraight(completeCombination.getCard(NUM_CARDS - 1).getNumber())) {
                        return true;
                    }
                    break; 

                case SUM: 
                    int sum = completeCombination.sum();
                    int sum_cards = 0; 
                    for (int j = 0; j < num_cards; j++) {
                        sum_cards += cardCombination.getCard(j).getNumber();
                    }

                    if (sum_cards > sum) {
                        return true;
                    } 
                    return ((sum - sum_cards < (NUM_CARDS - num_cards) * Card.MAX_NUMBER)); 
            }
        }
        return false;
    }
}