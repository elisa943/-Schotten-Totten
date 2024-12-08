import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    // Attributs
    protected int id;
    protected String name; 
    protected ArrayList<Card> hand;
    protected ArrayList<TacticCard> tacticHand;
    public static final int MAX_NUM_TAC_CARDS = 7;
    public static final int NUM_TAC_CARDS = 0;

    // Constructors 
    public Player(String name, int id) {
        this.name = name;
        //this.hand = new ArrayList<>();    
        this.id = id; 
    }

    // Methods
    public void addCardToHand(Card card) {
        if (card == null) {
            return;
        }
        hand.add(card);
    }
    
    public void addCardToTacticHand(TacticCard card) {
        if (card == null) {
            return;
        }
        tacticHand.add(card);
    }

    public void removeCardFromHand(Card card) {
        hand.remove(card);
    }

    public void removeCardFromTacticHand(TacticCard card) {
        tacticHand.remove(card);
    }
    
    // Setters et getters
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setTacticHand(ArrayList<TacticCard> tacticHand) {
        this.tacticHand = tacticHand;
    }

    public ArrayList<TacticCard> getTacticHand() {
        return tacticHand;
    }

    public Card getCardFromHand(int index) {
        return hand.get(index);
    }

    public TacticCard getTacticCardFromHand(int index) {
        return tacticHand.get(index);
    }

    public int[] getCardIndexFromUser(Border border, Player player) {
        /* Returns the card index (starts at 0) picked by the player */
        Scanner scanner = new Scanner(System.in);
        int cardNumber = -1; 
        int borderNumber = -1;

        while ((cardNumber <= 0 || cardNumber > hand.size())) {
            System.out.print("Choose a card to play (enter a white number): ");
            cardNumber = scanner.nextInt();

            if (cardNumber >= 1 && cardNumber <= hand.size() + tacticHand.size()) {
                break;
            }
            System.out.println("Invalid card number. Please choose a valid card.");
            cardNumber = -1;
        }
        
        while ((borderNumber <= 0 || borderNumber > Border.NUM_BORDER_CARDS)) {

            System.out.print("Choose a border to put the card: ");
            borderNumber = scanner.nextInt();

            if (borderNumber >= 1 && borderNumber <= Border.NUM_BORDER_CARDS) {
                if (!(border.isBorderIndexFull(borderNumber-1, player))) {
                    break;
                }
            }
            System.out.println("Invalid border number. Please choose a valid border.");
            borderNumber = -1;
        }

        //scanner.close();
        int[] numbers = {cardNumber-1, borderNumber-1};
        return numbers;
    }
}
