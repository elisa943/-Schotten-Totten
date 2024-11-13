public class Board {
    private Player player1;
    private Player player2;
    private Deck deck;
    private Border border;

    public Board() {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        deck = new Deck(player1, player2);
        border = new Border();
    }

    public Board(Player player1, Player player2, Deck deck, Border border) {
        this.player1 = player1;
        this.player2 = player2;
        this.deck = deck;
        this.border = border;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Deck getDeck() {
        return deck;
    }

    public Border getBorder() {
        return border;
    }

    // Other methods
    public void startGame() {
        int start = 1; 
        int turn = 0;
        while(start) 
        {
            // Player of id 'playerID' plays

            // Check if the game is over

        }
    }

    // Imprime l'état actuel du plateau
    public void printBoardState() {
        System.out.println("Frontières contrôlées par " + player1.getName() + " : ");
        border.BordersControlledBy(player1);

        System.out.print("Frontières contrôlées par " + player2.getName() + " : ");
        border.BordersControlledBy(player2);
    }


    public int gameOver() {
        /* If the game is won, returns player id */

        if (border.gameWon(player1)) {
            return player1.getId();
        } else if (border.gameWon(player2)) {
            return player2.getId();
        } else return 0;
    }
}