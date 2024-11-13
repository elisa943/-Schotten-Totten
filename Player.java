public class Player 
{
    // Attributs
    int playerId;
    String name; 
    private List<Card> hand;

    // Constructeur
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();      
        this.wonBorders = new ArrayList<>(); 
    }

    // Methodes
    public void addCardToHand(Card card) {
        hand.add(card);
    }
    
    public void removeCardFromHand(Card card) {
        hand.remove(card);
    }
    
    // Setters et getters
    public String setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String setHand(String hand) {
        this.hand = hand;
    }

    public String getHand() {
        return hand;
    }

}
