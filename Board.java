
public class Board {

    private Player[] players;

    private Room[] sets;

    // Set up the board, initialize player locations, money/credits, rank, etc.
    public void beginGame(int numPlayers) {}

    // Move players back to trailers, deal new scenes & replace shot counters
    public void endDay(){}

    // Determine the winner of the game and return their name
    public String calculateWinner(String[] names, int[] ranks){ return ""; }

    public Board(){}

}
