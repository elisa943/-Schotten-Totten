import java.util.ArrayList;

public class Border {

    public static final int NUM_BORDER_CARDS = 9;
    private int[] border;
    private Card_Combination[][] combinations;

    public Border() {
        border = new int[NUM_BORDER_CARDS];
        combinations = new Card_Combination[2][NUM_BORDER_CARDS];
        for (int i = 0; i < NUM_BORDER_CARDS; i++) {
            border[i] = 0;
        }
        for (int i = 0; i < NUM_BORDER_CARDS; i++){
            combinations[0][i] = new Card_Combination();
            combinations[1][i] = new Card_Combination();
        }
    }

    public int getBorder(int index) {
        return border[index];
    }

    public void setBorder(int value, int index) {
        border[index] = value;
    }

    public void setCombinations(Card card, int i, int j) {
        combinations[i][j].addCard(card);
    }

    // public void setCombinations(Card card) {
    //     // Vérifier que la taille de la combinaison est < 3
    //     this.combinations.addCard(card);
    // }

    public int getBorderSize(int value) {
        int count = 0;
        for (int i = 0; i < NUM_BORDER_CARDS; i++) {
            if (border[i] == value) {
                count++;
            }
        }
        return count;
    }

    public boolean isBorderIndexFull(int index, Player player) {
        int playerID = player.getId();
        if (playerID > 0) {
            return (combinations[playerID - 1][index].getCardSize() >= 3);
        }
        return (combinations[0][index].getCardSize() >= 3);
    }

    public Card_Combination getCombinations(int i, int j)
    {
        return combinations[i][j];
    }

    public ArrayList<Integer> bordersControlledBy(Player player) {

        ArrayList<Integer> controlledBorders = new ArrayList<>(); 

        for (int i = 0; i < NUM_BORDER_CARDS; i++) {
            if (border[i] == player.getId()) 
            {
                controlledBorders.add(i);
            }
        }

        return controlledBorders;
    }

    public boolean gameWon(Player player) {
        /* Returns true if the game is won by the player */

        // 5 wall cards
        int playerID = player.getId();
        if (getBorderSize(playerID) >= 5) {
            return true;
        }

        // 3 adjacent cards
        for (int i = 0; i < NUM_BORDER_CARDS - 2; i++) {
            if (border[i] == playerID && border[i + 1] == playerID && border[i + 2] == playerID) {
                return true;
            }
        }

        return false; 
    }
}