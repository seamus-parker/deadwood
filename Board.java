import java.util.Random;

public class Board {

    private Player[] players;

    private Room[] sets;

    private Scene[] deck;

    private int[][] upgrades;

    private int days;

    void shuffle() {
        Random rnd = new Random();
        for (int i = this.deck.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Scene a = this.deck[index];
            this.deck[index] = this.deck[i];
            this.deck[i] = a;
        }
    }

    // Set up the board, initialize player locations, money/credits, rank, etc.
    public void beginGame(int numPlayers) {}

    // Move players back to trailers, deal new scenes & replace shot counters
    public void endDay(){}

    // Determine the winner of the game and return their name
    public String calculateWinner(String[] names, int[] ranks){ return ""; }

    public Board(String[] playerNames, int numPlayers, Room[] sets,
                 Scene[] deck, int[][] upgrades) {
        Player[] myPlayers = new Player[numPlayers];
        this.days = 4;
        int credits = 0;
        int rank = 1;
        if (numPlayers < 4) {
            this.days = 3;
        }
        if (numPlayers == 5) {
            credits = 2;
        }
        if (numPlayers == 6) {
            credits = 4;
        }
        if (numPlayers > 6) {
            rank = 2;
        }
        for (int i = 0; i < numPlayers; i++) {
            // construct players
            //myPlayers[i] = new Player()
        }
        this.players = myPlayers;
        this.sets = sets;
        this.deck = deck;
        this.upgrades = upgrades;
        shuffle();
    }

}
