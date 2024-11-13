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

    public void startGame() {
        boolean start = true; 

        while(start) {
            // Displays the game board
            //printBoardState();

            int values[] = player1.getCardIndexFromUser();
            // Player of id 'playerID' plays

            // Detects a combination of cards

            // Check if the game is over
            start = gameOver() == 0;
        }
    }

    public void printBoardState() {
        /* Imprime l'état actuel du plateau */
        System.out.println("Frontières contrôlées par " + player1.getName() + " : ");
        System.out.println(border.bordersControlledBy(player1).toString());

        System.out.println("Frontières contrôlées par " + player2.getName() + " : ");
        System.out.println(border.bordersControlledBy(player2).toString());
    }

    public void displayBoard()
    {
        System.out.print("Player 1\n\n");
        
        String spaceBetweenNumbers = "    ";

        for(int i = 0; i < border.NUM_BORDER_CARDS; i++)
        {
            for(int j = 2; j > -1; j--)
            {
                Card_Combination currentCombination = getCombinations(0,i);
                Card cardJ = currentCombination.getCard(j);

                if (cardJ == null)
                {
                    System.out.print(" ");
                }
                else
                {
                    System.out.print(cardJ.getNumber());
                }

                System.out.print(spaceBetweenNumbers);
            }
            System.out.print("\n");
        }

        System.out.println("|_1_||_2_||_3_||_4_||_5_||_6_||_7_||_8_||_9_|");

        String spaceBetweenNumbers = "    ";

        for(int i = 0; i < border.NUM_BORDER_CARDS; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                Card_Combination currentCombination = getCombinations(0,i);
                Card cardJ = currentCombination.getCard(j);

                if (cardJ == null)
                {
                    System.out.print(" ");
                }
                else
                {
                    System.out.print(cardJ.getNumber());
                }

                System.out.print(spaceBetweenNumbers);
            }
            System.out.print("\n");
        }

        System.out.print("\nPlayer 2\n");
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