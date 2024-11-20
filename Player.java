import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    // Attributs
    int id;
    String name; 
    private ArrayList<Card> hand;

    // Constructeur
    public Player(String name, int id) {
        this.name = name;
        //this.hand = new ArrayList<>();    
        this.id = id; 
    }

    // Methodes
    public void addCardToHand(Card card) {
        if (card == null) {
            return;
        }
        hand.add(card);
    }
    
    public void removeCardFromHand(Card card) {
        hand.remove(card);
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

    public Card getCardFromHand(int index) {
        return hand.get(index);
    }

    public int[] getCardIndexFromUser() {
        /* Returns the card index (starts at 0) picked by the player */
        Scanner scanner = new Scanner(System.in);
        int cardNumber = -1; 
        int borderNumber = -1;

        int validCardNumber = 0;
        int validBorderNumber = 0;

        while ((cardNumber <= 0 || cardNumber > hand.size()) && (validCardNumber == 0)) {

            System.out.print("Choose a card to play (enter a white number): ");
            cardNumber = scanner.nextInt();

            if (cardNumber < 1 || cardNumber > hand.size()) {
                System.out.println("Invalid card number. Please choose a valid card.");
                cardNumber = -1;
            }
            else
            {
                validCardNumber = 1;
            }
        }
        
        while ((borderNumber <= 0 || borderNumber > Border.NUM_BORDER_CARDS) && (validBorderNumber == 0)) {

            System.out.print("Choose a border to put the card: ");
            borderNumber = scanner.nextInt();

            if (borderNumber < 1 || borderNumber > Border.NUM_BORDER_CARDS) {
                System.out.println("Invalid border number. Please choose a valid border.");
                borderNumber = -1;
            }
            else
            {
                validBorderNumber = 1;
            }
        }

        //scanner.close();
        int[] numbers = {cardNumber-1, borderNumber-1};
        return numbers;
    }

}
