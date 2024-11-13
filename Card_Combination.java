import java.util.ArrayList;

public class Card_Combination {
    private ArrayList<Card> cards;

    public Card_Combination() {
        cards = new ArrayList<Card>();
    }

    public Card getCard(int index) {
        if (cards.size() < index) {
            return null;
        } 
        return cards.get(index);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void sort() {
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 3; j++) {
                if (cards.get(i).getNumber() > cards.get(j).getNumber()) {
                    Card temp = cards.get(i);
                    cards.get(i) = cards.get(j);
                    cards.get(j) = temp;
                }
            }
        }
    }

    public boolean isStraightFlush() {
        sort();
        return cards.get(0).getColor() == cards.get(1).getColor() && cards.get(1).getColor() == cards.get(2).getColor() && cards.get(0).getNumber() + 1 == cards.get(1).getNumber() && cards.get(1).getNumber() + 1 == cards.get(2).getNumber();
    }

    public boolean isThreeOfAKind() {
        sort();
        return cards.get(0).getNumber() == cards.get(1).getNumber() && cards.get(1).getNumber() == cards.get(2).getNumber();
    }

    public boolean isFlush() {
        return cards.get(0).getColor() == cards.get(1).getColor() && cards.get(1).getColor() == cards.get(2).getColor();
    }

    public boolean isStraight() {
        sort();
        return cards.get(0).getNumber() + 1 == cards.get(1).getNumber() && cards.get(1).getNumber() + 1 == cards.get(2).getNumber();
    }

    public int sum() {
        return cards.get(0).getNumber() + cards.get(1).getNumber() + cards.get(2).getNumber();
    }

    public Combination getCombination() {
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
}