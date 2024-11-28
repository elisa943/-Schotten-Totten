import java.util.Scanner;

class UserInterface {
    public static void displayBanner() {
        System.out.println( "\t/~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\\"); 
        System.out.println( "\t|                               |");
        System.out.print("\t|");
        ColoredText.printColored("  Welcome to Schotten-Totten!  ", ColoredText.getColoredString(Color.RED));
        System.out.println("|");
        System.out.println( "\t|                               |");
        System.out.println("\t\\~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/");
    }

    public static void displayMainMenu() {
        ColoredText.clear();
        displayBanner();
        
        System.out.println();
        System.out.println("Choose an option: ");
        System.out.print("[1] ");
        ColoredText.printlnColored("Start Game", ColoredText.getColoredString(Color.RED));

        System.out.print("[2] ");
        ColoredText.printlnColored("Exit", ColoredText.getColoredString(Color.RED));

        System.out.println();

        System.out.print("Enter your choice (1 or 2): ");
    }

    public static void displayStartScreen() {
        ColoredText.clear();
        UserInterface.displayBanner();
        System.out.println(); 

        System.out.println("Choose your game mode: ");
        System.out.print("[1] ");
        ColoredText.printColored("Classic Mode", ColoredText.getColoredString(Color.RED));
        System.out.println(" | The traditional gameplay.");
        System.out.print("[2] ");
        ColoredText.printColored("Tactical Variant", ColoredText.getColoredString(Color.RED));
        System.out.println(" | Play with Tactical Cards!");

        System.out.println(); 

        System.out.print("Enter your choice (1 or 2): ");
    }

    public static int pick_menu(Scanner scanner) {
        displayMainMenu();

        int choice = -1;

        while (choice != 1 && choice != 2) {
            choice = scanner.nextInt();
            if (choice != 1 && choice != 2) {
                ColoredText.clear();
                displayMainMenu();
            }
        }

        return choice; 
    }

    public static int pick_variant(Scanner scanner) {
        displayStartScreen();

        int choice = -1;
        while (choice != 1 && choice != 2) {
            choice = scanner.nextInt();
            if (choice != 1 && choice != 2) {
                ColoredText.clear();
                displayStartScreen();
            }
        }
        System.out.println("You chose: " + choice);
        return choice;
    }

}