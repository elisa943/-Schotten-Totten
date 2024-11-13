import java.util.ArrayList;

public class Player 
{
    // Attributs
    int playerId;
    String name; 
    private ArrayList<Card> hand;
    private List<Borne> wonBorders;

    // Constructeur
    public Player(String name) {
        this.name = name;
        //this.hand = new ArrayList<>();      
        this.wonBorders = new ArrayList<>(); 
    }

    // Methodes
    public void addCardToHand(Card card) {
        hand.add(card);
    }
    
    public void removeCardFromHand(Card card) {
        hand.remove(card);
    }

    public void addWonBorder(Border border) {
        wonBorders.add(border);
    }
    
    public List<Borne> getWonBorders() {
        return wonBorders;
    }
    
    // Setters et getters
    public String setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public String getHand() {
        return hand;
    }

}
