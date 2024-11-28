public class TacticalVariant extends Board {
    protected TacticDeck tacticDeck;

    public TacticalVariant() {
        super();
        tacticDeck = new TacticDeck();
    }

    public TacticalVariant(Player player1, Player player2, Deck deck, Border border, TacticDeck tacticDeck) {
        super(player1, player2, deck, border);
        this.tacticDeck = tacticDeck;
    }

    public TacticDeck getTacticDeck() {
        return tacticDeck;
    }

    public static void displayHand(Player player) {
        // Displays the player's hand
        Board.displayHand(player);

        // Displays the player's tactic hand
        System.out.println("Tactic cards: ");
        for (int i = 0; i < player.getTacticHand().size(); i++) {
            System.out.print(TacticCards.getTacticCardName(player.getTacticHand().get(i).getTacticCard()) + " ");
        }

        // Displays the buttons below the hand
        int n = player.getHand().size();
        System.out.println();
        System.out.print("              ");
        for (int i = 0; i < player.getTacticHand().size(); i++) {
            System.out.print(Integer.toString(n+i));
            for (int j = 0; j < TacticCards.getTacticCardName(player.getTacticHand().get(i).getTacticCard()).length(); j++) {
                System.out.print(" ");
            }
        }

        System.out.println();
    }

    @Override
    public void startGame() {
        ColoredText.clear();
        boolean start = true; 
        Player startingPlayer = player1;
        Player otherPlayer = player2;
        while(start) {
            // Displays the game board
            displayBoard(startingPlayer);
            displayHand(startingPlayer);

            // startingPlayer plays
            int values[] = startingPlayer.getCardIndexFromUser(); // (cardIndex, positionIndex)
            
            // Check if the player wants to play a tactic card
            // TODO 

            // Adds card to the border
        }
    }
}