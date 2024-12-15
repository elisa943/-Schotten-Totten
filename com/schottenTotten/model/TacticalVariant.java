package com.schottenTotten.model;
import com.schottenTotten.view.*;
import com.schottenTotten.controller.Card_Combination;
import com.schottenTotten.ai.AIPlayer;


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

            // AI or Human plays
            int[] values;
            if (startingPlayer instanceof AIPlayer) {
                // AI selects card and border
                values = ((AIPlayer) startingPlayer).getCardIndexFromAI(border, startingPlayer);
            } else {
                // Human selects card and border
                values = startingPlayer.getCardIndexFromUser(border, startingPlayer);
            }

            Card card;
            if (values[0] >= startingPlayer.getHand().size()) {
                // Tactic card played
                TacticCard tacticCard = startingPlayer.getTacticCardFromHand(values[0] - startingPlayer.getHand().size());

                // Ensure Joker rule is respected
                if (tacticCard.getTacticCard() == TacticCards.JOKER && startingPlayer.hasJokerInPlay(border)) {
                    if (startingPlayer instanceof AIPlayer) {
                        continue; // AI skips turn for invalid Joker
                    } else {
                        System.out.println("You already have a Joker in play! You cannot place another one.");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        continue;
                    }
                }

                border.setCombinations(tacticCard, Math.max(0, startingPlayer.getId() - 1), values[1]);
                startingPlayer.removeCardFromTacticHand(tacticCard);
            } else {
                // Normal card played
                card = startingPlayer.getCardFromHand(values[0]);
                border.setCombinations(card, Math.max(0, startingPlayer.getId() - 1), values[1]);
                startingPlayer.removeCardFromHand(card);
            }

            // Check if a combination is complete
            Card_Combination playerCombination = border.getCombinations(Math.max(0, startingPlayer.getId() - 1), values[1]);
            if (playerCombination.getCardSize() == 3) {
                if (startingPlayer instanceof AIPlayer) {
                    // AI configures cards
                    for (int i = 0; i < playerCombination.getCardSize(); i++) {
                        if (playerCombination.getCard(i) instanceof TacticCard) {
                            TacticCard tacticCard = (TacticCard) playerCombination.getCard(i);
                            ((AIPlayer) startingPlayer).configureTacticCard(tacticCard);
                        }
                    }
                } else {
                    // Human configures cards
                    configureTacticCards(playerCombination, startingPlayer);
                }
            }

            // Pick a card from the deck
            int deckPicked = 0;
            if (deck.isEmpty() && !tacticDeck.isEmpty()) {
                deckPicked = 2; // Only tactic deck available
            } else if (!deck.isEmpty() && tacticDeck.isEmpty()) {
                deckPicked = 1; // Only normal deck available
            } else if (!deck.isEmpty() && !tacticDeck.isEmpty()) {
                if (startingPlayer instanceof AIPlayer) {
                    deckPicked = ((AIPlayer) startingPlayer).pickDeck();
                } else {
                    deckPicked = UserInterface.which_deck();
                }
            }

            if (deckPicked == 1) {
                Card drawnCard = deck.getCard();
                if (drawnCard != null) {
                    startingPlayer.addCardToHand(drawnCard);
                }
            } else if (deckPicked == 2) {
                TacticCard drawnCard = (TacticCard) tacticDeck.getCard();
                if (drawnCard != null) {
                    startingPlayer.addCardToTacticHand(drawnCard);
                }
            }

            // Check if the game is over
            start = gameOver() == 0;
            startingPlayer = startingPlayer == player1 ? player2 : player1;
        }

        displayBoard(startingPlayer);

        if (gameOver() == player1.getId()) {
            System.out.println(player1.getName() + " wins !");
        } else {
            System.out.println(player2.getName() + " wins !");
        }
    }

}