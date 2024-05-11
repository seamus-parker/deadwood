public class Player
{

  private String name;
  private int dollars = 0;
  private int credits = 0;
  private int rank = 1;
  private Role role;
  private Room location;
  
  public setLocation (Room newLocation){
    location = newLocation;
  }
  public String getName(){
    return name;
  }
  //need a class for current player role
  //need a class for current player rooms
  
  public void getPossibleActions(String playerSetLocation){
    //call controller to display available actions
    //(actions include movement,upgrading,practicing,acting,taking a role)
  }
  public Set[] getPossibleMoves(){
    return this.location.getNeighbors();
  }
  public String getPossibleRoles(String Location){
    //check set and scene card of active player for open roles they qualify for
    //allow player to select one of the roles or decline
    
    return sceneRoles + setRoles;
  }
  public void act(int practiceChips, String currentRole){
    //roll die and add practiceChips
    int budget = 0; //access xml file and assign budget of the current scene card
    int dieRoll = 0; //roll die, assign result to dieRoll
    int result = dieRoll + practiceChips;
    if (budget <= result) {
        this.location.removeShotCounter();
      //call function to remove a scene counter from current set
      if (this.role.isOnCard()){ //if current role is on card
        addCredits(2);
      }else{
        addDollars(2);
      }
    }else{
        if (!this.role.isOnCard()){
            addDollars(1);
        }
    }
    //compare result to budget
    //if successful determine payout for role
    //remove counter
    //if last counter is removed, call on bonuses
    //if unsuccessful display failure message
  }
  public int getAffordableUpgrades(int playerCredits, int playerDollars){
    //determine availible ranks for upgrade to Player
    //return integer of highest rank affordable
    int highestUpgrade = 0;
    return highestUpgrade;
  }
  public void rehearsal(){
    //player gets +1 to thier practice chips
    practiceChips++;
  }
  public void addCredits(int amount){
    credits += amount;
  }
  public void addDollars(int amount){
    dollars += amount;
  }
  public void resetPracticeChips(){
    practice chips = 0;
  }
}

  public Player(){}
  
}
