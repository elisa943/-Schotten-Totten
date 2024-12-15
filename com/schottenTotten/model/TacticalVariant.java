package com.schottenTotten.model;
import com.schottenTotten.view.*;
import com.schottenTotten.controller.Card_Combination;

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

    private void configureTacticCards(Card_Combination combination, Player player) {
        for (int i = 0; i < combination.getCardSize(); i++) {
            Card card = combination.getCard(i);
            if (card instanceof TacticCard) {
                TacticCard tacticCard = (TacticCard) card;

                // Check if the card is dynamic
                if (tacticCard.isDynamicValue() || tacticCard.isDynamicColor()) {
                    System.out.println(player.getName() + ", configure your " + TacticCards.getTacticCardName(tacticCard.getTacticCard()) + ":");

                    player.selectValueAndColorForCard(tacticCard);

                    System.out.println("Card configured: Value = " + tacticCard.getNumber() + ", Color = " + tacticCard.getColor());
                }
            }
        }
    }
    @Override
    public void startGame() {
        setTerminalSize(50, 30);
        ColoredText.clear();
        boolean start = true; 
        Player startingPlayer = player1;
        Player otherPlayer = player2;

        while (start) {
            displayBoard(startingPlayer);
            displayHand(startingPlayer);

            int values[] = startingPlayer.getCardIndexFromUser(border, startingPlayer); 

            // Check if the player has a combination of 3 cards
            Card card;
            if (tacticCardPlayed(startingPlayer, values[0])) {
                TacticCard tacticCard = startingPlayer.getTacticCardFromHand(values[0] - startingPlayer.getHand().size());

                if (tacticCard.getTacticCard() == TacticCards.JOKER && startingPlayer.hasJokerInPlay(border)) {
                    System.out.println("You already have a Joker in play! You cannot place another one.");
                    try {
                        Thread.sleep(2000); // Pause
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); 
                    }
                    continue;
                }
                border.setCombinations(tacticCard, Math.max(0, startingPlayer.getId() - 1), values[1]);
                startingPlayer.removeCardFromTacticHand(tacticCard);
            } else {
                card = startingPlayer.getCardFromHand(values[0]);
                border.setCombinations(card, Math.max(0, startingPlayer.getId() - 1), values[1]);
                startingPlayer.removeCardFromHand(card);
            }

            // Check if the player has a combination of 3 cards
            Card_Combination playerCombination = border.getCombinations(Math.max(0, startingPlayer.getId() - 1), values[1]);
            if (playerCombination.getCardSize() == 3) {
                configureTacticCards(playerCombination, startingPlayer);
            }

            // Ask the player to pick a card from the deck
            int deck_picked = 0;
            if (deck.isEmpty() && !tacticDeck.isEmpty()) {
                deck_picked = 2; // Pioche tactique
            } else if (!deck.isEmpty() && tacticDeck.isEmpty()) {
                deck_picked = 1; // Pioche normale
            } else if (!deck.isEmpty() && !tacticDeck.isEmpty()) {
                deck_picked = UserInterface.which_deck(); 
            }

            if (deck_picked == 1) {
                Card drawnCard = deck.getCard();
                if (drawnCard != null) {
                    startingPlayer.addCardToHand(drawnCard);
                }
            } else if (deck_picked == 2) {
                TacticCard drawnCard = (TacticCard) tacticDeck.getCard(); 
                startingPlayer.addCardToTacticHand(drawnCard);
            }

            // Checks if the game is over
            start = gameOver() == 0;
            startingPlayer = startingPlayer == player1 ? player2 : player1;
        }

        // Display the final board
        displayBoard(startingPlayer);

        // Display the winner
        if (gameOver() == player1.getId()) {
            System.out.println(player1.getName() + " wins !");
        } else {
            System.out.println(player2.getName() + " wins !");
        }
    }

}