public class Player
{

  private String name;

  private int money;

  private int credits;

  private int rank;
  
  public void getPossibleActions(String playerSetLocation){
    //call controller to display available actions
    //(actions include movement,upgrading,practicing,acting,taking a role)
  }
  public void getPossibleMoves(String playerSetLocation){
    //displays options player has for movement
    //call on board
  }
  public void getPossibleRoles(String playerSetLocation){
    //check set and scene card of active player for open roles they qualify for
    //allow player to select one of the roles or decline
  }
  public void act(int practiceChips, String currentRole){
    //roll die and add practiceChips
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
    //player gets +1 to thier practice counter counter
  }

  public Player(){}
  
}
