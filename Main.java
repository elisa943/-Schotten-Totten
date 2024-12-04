import java.util.Scanner;

public class Main {
    public static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        int choice = -1; 
        int variant = -1;
        int ai = -1;

        while (choice == -1 && variant == -1 && ai == -1) {
            choice = UserInterface.pick_menu(scanner);

            if (choice == 1) {
                variant = UserInterface.pick_variant(scanner);

                if (variant == 1) {
                    ai = UserInterface.pick_ai(scanner);
                }
            }
        }
        if (variant == 1) {
            // Start the game
            System.out.println("Starting the game...");

            Board board = new Board(ai == 1);
            board.startGame();
        } else if (variant == 2) {
            TacticalVariant board = new TacticalVariant(ai == 1);
            board.startGame();
        }

        // Close the scanner
        scanner.close();
    }
    

}