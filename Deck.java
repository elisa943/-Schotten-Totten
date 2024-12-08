import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    // pioche (pile)
    protected ArrayList<Card> stack;
    public static final int NUM_NUMBERS = 9;
    public static final int NUM_CARDS_PER_PLAYER = 6;

    public Deck() {
        this.stack = new ArrayList<Card>();
        for (int i = 1; i <= NUM_NUMBERS; i++) {
            for (Color color : Color.values()) {
                this.stack.add(new Card(i, color));
            }
        }
        shuffle();
    }

    public Deck(Player player1, Player player2) {
        this.stack = new ArrayList<Card>();

        for (int i = 1; i <= NUM_NUMBERS; i++) {
            for (Color color : Color.values()) {
                this.stack.add(new Card(i, color));
            }
        }
        shuffle();

        distribute(player1);
        distribute(player2);
    }

    public ArrayList<Card> getStack() {
        return stack;
    }

    public int getStackSize() {
        return stack.size();
    }

    public Card getCard() {
        if (getStackSize() == 0) {
            return null;
        }
        return stack.remove(0);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void shuffle() {
        Collections.shuffle(stack);
    }

    public void distribute(Player player) {
        /* Distributes cards to the player and removes them from the stack */
        ArrayList<Card> hand = new ArrayList<Card>();
        Card card;
        for (int i = 0; i < NUM_CARDS_PER_PLAYER; i++) {
            card = getCard();
            hand.add(card);
            stack.remove(card);
        }
        player.setHand(hand);
    }
}