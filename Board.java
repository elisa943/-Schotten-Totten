public class Board {
    private Player player1;
    private Player player2;
    private Deck deck;
    private Board board;
    private Border border;

    public Board(Player player1, Player player2, Deck deck, Board board, Border border) {
        this.player1 = player1;
        this.player2 = player2;
        this.deck = deck;
        this.board = board;
        this.border = border;
    }
}