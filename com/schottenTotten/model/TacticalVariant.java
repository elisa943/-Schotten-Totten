package com.schottenTotten.model;
import com.schottenTotten.view.*;

public class TacticalVariant extends Board {
    protected TacticDeck tacticDeck;

    public TacticalVariant(boolean isAI, String name1, String name2) {
        super(isAI, name1, name2);
        tacticDeck = new TacticDeck(player1, player2);
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

        // Displays the player's tactic hand if there are tactic cards
        if (player.getTacticHand().size() == 0) {
            System.out.println();
            return;
        }

        System.out.print("Tactic cards: ");
        for (int i = 0; i < player.getTacticHand().size(); i++) {
            System.out.print(TacticCards.getTacticCardAbr(player.getTacticHand().get(i).getTacticCard()) + " ");
        }

        // Displays the buttons below the hand
        int n = player.getHand().size();
        System.out.println();
        System.out.print("              ");
        for (int i = 0; i < player.getTacticHand().size(); i++) {
            System.out.print(Integer.toString(n+i+1));

            if (n+i+1 <= 9) {
                System.out.print("  ");
            } else {
                System.out.print(" ");
            }
        }

        System.out.println();
    }

    public boolean tacticCardPlayed(Player player, int cardIndex) {
        /* Returns true if a tactic card was played */
        if (cardIndex + 1 <= player.getHand().size()) {
            return false;
        }

        // if (cardIndex + 1 >= player.getHand().size() + player.getTacticHand().size()) {
        //     return false;
        // }

        return true;
    }

    @Override
    public void startGame() {
        setTerminalSize(50, 30);
        ColoredText.clear();
        boolean start = true; 
        Player startingPlayer = player1;
        Player otherPlayer = player2;
        while(start) {
            // Displays the game board
            displayBoard(startingPlayer);
            displayHand(startingPlayer);

            // startingPlayer plays
            int values[] = startingPlayer.getCardIndexFromUser(border, startingPlayer); // (cardIndex, positionIndex)

            // Check if the player wants to play a tactic card
            Card card;
            if (tacticCardPlayed(startingPlayer, values[0])) {
                // If so, play the tactic card 
                card = (Card) startingPlayer.getTacticCardFromHand(values[0] - startingPlayer.getHand().size());
                // Adds card to the border
                border.setCombinations(card, (Math.max(0, startingPlayer.getId() - 1)), values[1]);
                // TODO : playTacticCard(startingPlayer, values[1], card);
                // Removes card from hand
                startingPlayer.removeCardFromTacticHand((TacticCard) card);
            } else {
                // If not, play the regular card
                card = startingPlayer.getCardFromHand(values[0]);
                // Adds card to the border
                border.setCombinations(card, (Math.max(0, startingPlayer.getId() - 1)), values[1]);
                // Removes card from hand
                startingPlayer.removeCardFromHand(card);
            }

            // Updates the board 
            displayBoard(startingPlayer);

            // Ask player which deck to draw from if both decks are not empty 
            int deck_picked = 0;
            if (deck.isEmpty() && !(tacticDeck.isEmpty())) {
                // draw from tactic deck
                deck_picked = 2;
            } else if (!(deck.isEmpty()) && tacticDeck.isEmpty()) {
                // draw from regular deck
                deck_picked = 1;
            } else if (!(deck.isEmpty()) && !(tacticDeck.isEmpty())) {
                // ask player which deck to draw from
                deck_picked = UserInterface.which_deck(); 
            }

            if (deck_picked == 1) {
                Card drawnCard = deck.getCard();
                if (drawnCard != null) {
                    startingPlayer.addCardToHand(drawnCard);
                }
            } else {
                TacticCard drawnCard = (TacticCard) tacticDeck.getCard(); 
                startingPlayer.addCardToTacticHand(drawnCard);
            }

            // Check if the game is over
            start = gameOver() == 0;
            startingPlayer = startingPlayer == player1 ? player2 : player1;
            otherPlayer = otherPlayer == player1 ? player2 : player1;
        }

        // Display the winner
        displayBoard(startingPlayer);
        System.out.println("Game over! " + startingPlayer.getName() + " wins!");

    }
}