import java.util.ArrayList;

public class Border {

    public static final int NUM_BORDER_CARDS = 9;
    private int[] border;
    private ArrayList<ArrayList<Card_Combination>> combinations;

    public Border() {
        border = new int[NUM_BORDER_CARDS];
        for (int i = 0; i < NUM_BORDER_CARDS; i++) {
            border[i] = 0;
        }
    }

    public int getBorder(int index) {
        return border[index];
    }

    public void setBorder(int value, int index) {
        border[index] = value;
    }

    public int getBorderSize(int value) {
        int count = 0;
        for (int i = 0; i < NUM_BORDER_CARDS; i++) {
            if (border[i] == value) {
                count++;
            }
        }
        return count;
    }

    public getCombinations

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