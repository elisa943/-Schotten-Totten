public class ColoredText {

    public static final String RESET = "\u001B[0m";

    private String text;
    private String color;

    public ColoredText(String text, String color) {
        this.text = text;
        this.color = color;
    }

    public void println() {
        System.out.println(color + text + RESET);
    }

    public static void printColored(String text, String color) {
        System.out.print(color + text + RESET);
    }

    public static void printlnColored(String text, String color) {
        System.out.println(color + text + RESET);
    }

    public static void clear() {
        // ANSI escape code to clear the screen
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String getColoredString(Color color) {
        switch (color) {
            case GREEN:
                return "\u001B[32m";
            case ORANGE:
                return "\u001B[33m";
            case RED:
                return "\u001B[31m";
            case PURPLE:
                return "\u001B[35m";
            case YELLOW:
                return "\u001B[33m";
            case BLUE:
                return "\u001B[34m";
            default:
                return "";
        }
    }

    public static void coloredCard(Card card) {
        if (card != null) {
            if (card.getNumber() != -1) {
                printColored(Integer.toString(card.getNumber()), getColoredString(card.getColor()));
            }
            
        } 
    }

    // Main function 
    public static void main(String[] args) {
        ColoredText coloredText = new ColoredText("Hello, World!", "\u001B[32m");
        coloredText.println();
        printlnColored("Hello, World!", "\u001B[33m");

        printColored("Hello, ", "\u001B[31m");
    }

}