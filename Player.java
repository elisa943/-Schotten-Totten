import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    // Attributs
    int id;
    String name; 
    private ArrayList<Card> hand;

    // Constructeur
    public Player(String name) {
        this.name = name;
        //this.hand = new ArrayList<>();      
    }

    // Methodes
    public void addCardToHand(Card card) {
        hand.add(card);
    }
    
    public void removeCardFromHand(Card card) {
        hand.remove(card);
    }
    
    // Setters et getters
    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
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

    public int[] getCardIndexFromUser() {
        /* Returns the card index picked by the player */
        Scanner scanner = new Scanner(System.in);
        int cardNumber = -1; 
        int borderNumber = -1;
        while ((cardNumber < 0 || cardNumber >= hand.size()) && (borderNumber < 0 || borderNumber >= Border.NUM_BORDER_CARDS)) {
            System.out.print("Choose a card to play: ");
            cardNumber = scanner.nextInt();
            System.out.print("Choose a border to put the card: ");
            borderNumber = scanner.nextInt();
        }
        //scanner.close();
        int[] numbers = {cardNumber, borderNumber};
        return numbers;
    }

}
