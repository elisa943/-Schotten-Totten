public class Card_Combination {
    private Card[] cards;

    public Card_Combination() {
        cards = new Card[3];
    }

    public Card_Combination(Card card1, Card card2, Card card3) {
        cards = new Card[3];
        cards[0] = card1;
        cards[1] = card2;
        cards[2] = card3;
    }

    public Card getCard(int index) {
        return cards[index];
    }

    public void setCard(Card card, int index) {
        cards[index] = card;
    }

    public void sort() {
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 3; j++) {
                if (cards[i].getNumber() > cards[j].getNumber()) {
                    Card temp = cards[i];
                    cards[i] = cards[j];
                    cards[j] = temp;
                }
            }
        }
    }

    public boolean isStraightFlush() {
        sort();
        return cards[0].getColor() == cards[1].getColor() && cards[1].getColor() == cards[2].getColor() && cards[0].getNumber() + 1 == cards[1].getNumber() && cards[1].getNumber() + 1 == cards[2].getNumber();
    }

    public boolean isThreeOfAKind() {
        sort();
        return cards[0].getNumber() == cards[1].getNumber() && cards[1].getNumber() == cards[2].getNumber();
    }

    public boolean isFlush() {
        return cards[0].getColor() == cards[1].getColor() && cards[1].getColor() == cards[2].getColor();
    }

    public boolean isStraight() {
        sort();
        return cards[0].getNumber() + 1 == cards[1].getNumber() && cards[1].getNumber() + 1 == cards[2].getNumber();
    }

    public int sum() {
        return cards[0].getNumber() + cards[1].getNumber() + cards[2].getNumber();
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