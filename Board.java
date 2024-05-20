import java.util.Random;

public class Board {

    private Player[] players;

    private Room[] sets;

    private Scene[] deck;

    private int[][] upgrades;

    private int days;

    private Player activePlayer;

    private int deckIndex = 0;

    void resetSceneCards(){
        for (int i = 0; i < sets.length; i++){
            if(sets[i] != getRoomByName("trailers") && sets[i] != getRoomByName("office")){
                for (int j = 0; j < sets[i].getRoles().length; j++) {
                    sets[i].getRoles()[j].setTaken(false);
                }
                sets[i].newCard(deck[deckIndex]);
                sets[i].resetWrapped();
                deckIndex++;
            }
        }
    }

    void shuffle() {
        Random rnd = new Random();
        for (int i = this.deck.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Scene a = this.deck[index];
            this.deck[index] = this.deck[i];
            this.deck[i] = a;
        }
    }

    public Room getRoomByName(String name) {
        for (int i = 0; i < sets.length; i++) {
            if (sets[i].getName().equals(name)) {
                return sets[i];
            }
        }
        return new Room();
    }

    public int activeScenes() {
        int n = 0;
        for (int i = 0; i < this.sets.length; i++) {
            if (!this.sets[i].isWrapped()) {
                n++;
            }
        }
        return n;
    }

    public Player getActivePlayer() {
        return this.activePlayer;
    }

    public void setActivePlayer(int num) {
        this.activePlayer = this.players[num];
    }

    public void nextPlayer() {
        if (this.activePlayer.getId() == this.players.length-1) {
            this.setActivePlayer(0);
        }
        else {
            this.setActivePlayer(this.activePlayer.getId()+1);
        }
    }

   
    // Move players back to trailers, deal new scenes & replace shot counters
    public void endDay(){
        for (int i=0; i < players.length; i++ ){
            this.players[i].acceptRole(null);
            this.players[i].move(getRoomByName("trailer"));
            this.players[i].resetPracticeChips();
            this.players[i].resetActedOrReaheased();
            this.players[i].resetMove();
        }
    }

    public boolean validateActionInput(Player p, String action) {
        if (action == "move") {
            return p.canMove();
        }
        else if (action == "work") {
            return p.canWork();
        }
        else if (action == "act") {
            return p.canAct();
        }
        else if (action == "rehearse") {
            return p.canRehearse();
        }
        else if (action == "upgrade") {
            return p.canUpgrade();
        }
        else if (action == "invalid") {
            return false;
        }
        else {
            return true;
        }
    }

    public int getDays() {
        return this.days;
    }

    public int[][] getUpgrades() {
        return this.upgrades;
    }

    // Determine the winner of the game and return their name
    public Player calculateWinner() {
        int score = 0;
        int maxScore = 0;
        int index = 0;
        for (int i = 0; i < players.length; i++) {
            score = players[i].getScore();
            if (score > maxScore) {
                maxScore = score;
                index = i;
            }
        }
        return players[index];
    }

    public Board(String[] playerNames, int numPlayers, Room[] sets,
                 Scene[] deck, int[][] upgrades) {

        this.sets = sets;
        this.deck = deck;
        this.upgrades = upgrades;
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
            myPlayers[i] = new Player(playerNames[i], i, this.getRoomByName("trailers"));
            myPlayers[i].addCredits(credits);
            myPlayers[i].upgradeRank(rank);
        }

        this.players = myPlayers;

        shuffle();
    }

    public Player[] getPlayers() {
        return this.players;
    }
}