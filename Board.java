import java.util.ArrayList;
import java.io.IOException;

public class Board {
    private Player player1;
    private Player player2;
    private Deck deck;
    private Border border;

    public Board() {
        player1 = new Player("Player 1", 1);
        player2 = new Player("Player 2", 2);
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

    // private void setTerminalSize(int width, int height) {
    //     try {
    //         if (System.getProperty("os.name").toLowerCase().contains("win")) {
    //             // Commande pour Windows
    //             new ProcessBuilder("cmd", "/c", "mode con: cols=" + width + " lines=" + height).inheritIO().start().waitFor();
    //         } else {
    //             // Commande pour Unix (Linux et macOS)
    //             String[] cmd = {"resize -s " + height + " " + width};
    //             Runtime.getRuntime().exec(cmd);
    //         }
    //     } catch (IOException | InterruptedException e) {
    //         e.printStackTrace();
    //     }
    // }

    public void startGame() {
        // setTerminalSize(80, 27);
        ColoredText.clear();
        boolean start = true; 
        Player startingPlayer = player1;
        Player otherPlayer = player2;
        while(start) {
            // Displays the game board
            displayBoard();
            displayHand(startingPlayer);

            // startingPlayer plays
            int values[] = startingPlayer.getCardIndexFromUser(); // (cardIndex, positionIndex)
            
            // Adds card to the border
            border.setCombinations(startingPlayer.getCardFromHand(values[0]), startingPlayer.getId() - 1, values[1]);

            // Removes card from hand
            startingPlayer.removeCardFromHand(startingPlayer.getCardFromHand(values[0]));

            // Draws card from deck
            startingPlayer.addCardToHand(deck.getCard());

            // Checks if the new combination is complete
            Card_Combination cardCombination = border.getCombinations(startingPlayer.getId() - 1, values[1]);
            if (cardCombination.getCardSize() == 3) {
                Combination combination = cardCombination.getCombination();
                
                // Checks if other player has a full combination
                if (border.getCombinations(otherPlayer.getId() - 1, values[1]).getCardSize() == 3) {

                }
                // Else checks if other player might have a better combination
                else if (Card_Combination.betterCombination(cardCombination, border.getCombinations(otherPlayer.getId() - 1, values[1]))) {
                    
                }
                // Else the player wins the border
                else {
                    border.setBorder(startingPlayer.getId(), values[1]);
                }
            }

            // Check if the game is over
            start = gameOver() == 0;
            startingPlayer = startingPlayer == player1 ? player2 : player1;
            otherPlayer = otherPlayer == player1 ? player2 : player1;
        }
    }

    public void printBoardState() {
        /* Imprime l'état actuel du plateau */
        System.out.println("Frontières contrôlées par " + player1.getName() + " : ");
        System.out.println(border.bordersControlledBy(player1).toString());

        System.out.println("Frontières contrôlées par " + player2.getName() + " : ");
        System.out.println(border.bordersControlledBy(player2).toString());
    }

    public void displayBoard() {
        ColoredText.clear();
        String spaceBetweenNumbers = "    "; 

        System.out.printf("Player 1 : %s\n\n", player1.getName());
 
        for(int j = 2; j > -1; j--) {

            System.out.print("  "); 

            for(int i = 0; i < border.NUM_BORDER_CARDS; i++) 
            {
                Card cardJ = border.getCombinations(0,i).getCard(j); 

                if (cardJ == null)
                {
                    System.out.print(" ");
                }
                else
                {
                    ColoredText.coloredCard(cardJ); 
                }

                System.out.print(spaceBetweenNumbers);
            }
            
            System.out.print("\n");

        }

        // Printing the border in the middle 
        // Player 1 controlled borders
        for (int i = 0; i < border.NUM_BORDER_CARDS; i++)
        {
            if (border.getBorder(i) == 1)
            {
                ColoredText.printColored("|_%d_|", i + 1, ColoredText.getColoredString(Color.RED));
            }
            else
            {
                System.out.print("  ");
            }
        }

        System.out.print("\n");

        // Not controlled border
        for (int i = 0; i < border.NUM_BORDER_CARDS; i++)
        {
            if (border.getBorder(i) == 0)
            {
                System.out.printf("|_%d_|", i + 1);
            }
            else
            {
                System.out.print("  "); 
            }
        }
        
        System.out.print("\n");

        // Player 2 controlled borders
        for (int i = 0; i < border.NUM_BORDER_CARDS; i++)
        {
            if (border.getBorder(i) == 2)
            {
                ColoredText.printColored("|_%d_|", i + 1, ColoredText.getColoredString(Color.GREEN));
            }
            else
            {
                System.out.print("  ");
            }
        }

        System.out.print("\n");

        for(int j = 0; j < 3; j++) {
                
            System.out.print("  "); 

            for(int i = 0; i < border.NUM_BORDER_CARDS; i++) 
            {

                Card cardJ = border.getCombinations(1,i).getCard(j);

                if (cardJ == null) {
                    System.out.print(" ");
                }
                else {
                    ColoredText.coloredCard(cardJ); 
                }
                
                System.out.print(spaceBetweenNumbers);

            }
            
            System.out.print("\n");

        }

        System.out.printf("\nPlayer 2 : %s\n", player2.getName());
    }

    public static void displayHand(Player player) {
        ArrayList<Card> hand = player.getHand(); 

        System.out.print("Hand : ");
        for (int i = 0; i < hand.size(); i++) {
            ColoredText.coloredCard(hand.get(i));
            System.out.print(" ");
        }
        System.out.println();
        System.out.print("       ");
        for (int i = 0; i < hand.size(); i++) {
            System.out.print(Integer.toString(i+1) + " ");
        }
        System.out.println();
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