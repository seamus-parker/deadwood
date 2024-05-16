public class Set extends Room{
    
    public Role[] roles;

    public Scene card;

    public int shots;

    public int shotCounters;


    public void removeShotCounter() {
        this.shotCounters--;
    }

    public void replaceShotCounters() {
        this.shotCounters = this.shots;
    }

    // calculate and distribute bonuses to players working on the card
     public void calculateBonuses(int budget){
        int[] bonusResults = new int[6];
        for (int i = 0; i < 6; i++){
            if (i<budget){
                int result = dieRoll();
                bonusResults[i] = result;
            }else{
                bonusResults[i]=0;
            }
        }        
        Player[] playerArray = card.getSortedPlayers();
        int playersOnCard = playerArray.length;
        Arrays.sort(bonusResults);
        if (playersOnCard = 3){
            playerArray[2].addDollars(bonusResults[5]);
            playerArray[2].addDollars(bonusResults[2]);
            playerArray[1].addDollars(bonusResults[4]);
            playerArray[1].addDollars(bonusResults[1]);
            playerArray[0].addDollars(bonusResults[3]);
            playerArray[0].addDollars(bonusResults[0]);
        }else if (playersOnCard = 2){
            playerArray[1].addDollars(bonusResults[5]);
            playerArray[1].addDollars(bonusResults[2]);
            playerArray[1].addDollars(bonusResults[4]);
            playerArray[0].addDollars(bonusResults[1]);
            playerArray[0].addDollars(bonusResults[3]);
            playerArray[0].addDollars(bonusResults[5]);
        }else if (playersOnCard = 1){
            playerArray[0].addDollars(bonusResults[0]);
            playerArray[0].addDollars(bonusResults[1]);
            playerArray[0].addDollars(bonusResults[2]);
            playerArray[0].addDollars(bonusResults[3]);
            playerArray[0].addDollars(bonusResults[4]);
            playerArray[0].addDollars(bonusResults[5]);
        }
    }

    public Set(String myName, String[] myNeighbors, Role[] myRoles,
               int numShots, int myX, int myY, int myH, int myW) {
        super.setName(myName);
        super.setNeighbors(myNeighbors);
        this.roles = myRoles;
        this.shots = numShots;
        this.replaceShotCounters();
        this.x = myX;
        this.y = myY;
        this.h = myH;
        this.w = myW;
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
