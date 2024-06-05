import java.util.ArrayList;
import java.util.Arrays;
public class Set extends Room{
    
    public Role[] roles;

    public Scene card;

    public int shots;

    public int shotCounters;

    public boolean wrapped = false;
  
    public ArrayList<Player> extras = new ArrayList<Player>();

    public Player[] playersWithRoles;

    private int[][] shotCoords;

    public Scene getCard(){
        return this.card;
    }
    public void newCard(Scene card){
        this.card = card;
    }


    // reset status of players who were working on scene
    public void setWrapUp(){
        Player[] playerList = card.getSortedPlayers();
	    for (int i = 0; i < playerList.length; i++){
	        playerList[i].resetPracticeChips();
	        playerList[i].resetRole();
	    }
        wrapped = true;
        Player[] myExtras = this.getExtras();
        for (int i = 0; i < myExtras.length; i++){
	        myExtras[i].resetPracticeChips();
	        myExtras[i].resetRole();
	    }

        this.extras = new ArrayList<Player>();
	}

    public void addExtra(Player p) {
        this.extras.add(p);
    }
    public void resetWrapped(){
        this.wrapped = false;
    }

    public Player[] getExtras() {
        Player[] arr = new Player[this.extras.size()];
        for (int i = 0; i < this.extras.size(); i++) {
            arr[i] = this.extras.get(i);
        }
        return arr;
    }

    public void removeShotCounter() {
        this.shotCounters--;
    }

    public void replaceShotCounters() {
        this.shotCounters = this.shots;
    }

    public int getShotCounters() {
        return this.shotCounters;
    }

    public int getShots() {
        return this.shots;
    }

    public boolean isWrapped() {
        return this.wrapped;
    }

    // calculate and distribute bonuses to players working on the card
     public void calculateBonuses(){
        int budget = this.card.getBudget();
        int[] bonusResults = new int[6];
        for (int i = 0; i < 6; i++){
            if (i<budget){
                int result = (int) (Math.random() * 6) + 1;
                bonusResults[i] = result;
            }else{
                bonusResults[i]=0;
            }
        }        
        Player[] playerArray = card.getSortedPlayers();
        int playersOnCard = playerArray.length;
        Arrays.sort(bonusResults);
        Player[] playersOffCard = this.getExtras();
        if (playersOnCard > 0){
            for (int i = 0; i < playersOffCard.length; i++){
                int bonusAmount = playersOffCard[i].getCurrentRole().getLevel();
                playersOffCard[i].addDollars(bonusAmount);
            }
        }

        if (playersOnCard == 3){
            playerArray[2].addDollars(bonusResults[5]);
            playerArray[2].addDollars(bonusResults[2]);
            playerArray[1].addDollars(bonusResults[4]);
            playerArray[1].addDollars(bonusResults[1]);
            playerArray[0].addDollars(bonusResults[3]);
            playerArray[0].addDollars(bonusResults[0]);
            
        }else if (playersOnCard == 2){
            playerArray[1].addDollars(bonusResults[5]);
            playerArray[1].addDollars(bonusResults[2]);
            playerArray[1].addDollars(bonusResults[4]);
            playerArray[0].addDollars(bonusResults[1]);
            playerArray[0].addDollars(bonusResults[3]);
            playerArray[0].addDollars(bonusResults[5]);
        }else if (playersOnCard == 1){
            playerArray[0].addDollars(bonusResults[0]);
            playerArray[0].addDollars(bonusResults[1]);
            playerArray[0].addDollars(bonusResults[2]);
            playerArray[0].addDollars(bonusResults[3]);
            playerArray[0].addDollars(bonusResults[4]);
            playerArray[0].addDollars(bonusResults[5]);
        }
        setWrapUp();
    }

    public Set(String myName, String[] myNeighbors, Role[] myRoles, int numShots,
               int myX, int myY, int myH, int myW, int[][] takeCoords) {
        super.setName(myName);
        super.setNeighbors(myNeighbors);
        this.roles = myRoles;
        this.shots = numShots;
        this.replaceShotCounters();
        this.x = myX;
        this.y = myY;
        this.h = myH;
        this.w = myW;
        this.shotCoords = takeCoords;
    }
    public Role[] getRoles(){
        return this.roles;
    }

    public int[][] getShotCoords() {
        return this.shotCoords;
    }

    public void print() {
        System.out.println("name: " + super.getName());
        System.out.println("neighbors: ");
        String[] neighborNames = this.getNeighbors();
        for (int i = 0; i < neighborNames.length; i++) {
            System.out.println(neighborNames[i]);
        }
        System.out.println("roles: ");
        for (int j = 0; j < this.roles.length; j++) {
            this.roles[j].print();
        }
        System.out.println("shots: " + Integer.toString(this.shots));
    }

}
