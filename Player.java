import java.util.ArrayList;

public class Player
{
  private String name;
  private int dollars = 0;
  private int credits = 0;
  private int rank = 1;
  private Role currentRole;
  private Room location;
  private int practiceChips = 0;
  private boolean hasMoved = false;
  private boolean hasActedOrRehearsed = false;
  private boolean isWorking = false;
  private int idNumber = 0;
  //player attributes

  //player constuctor
  public Player(String name, int idNumber, Room startingRoom){
    this.name = name;
    this.dollars = 0;
    this.credits = 0;
    this.rank = 1;
    this.currentRole = null;
    this.location = startingRoom;
    this.idNumber = idNumber;
    this.practiceChips = 0;
    this.hasMoved = false;
    this.hasActedOrRehearsed = false;
    this.isWorking = false;
  }

  //player setters
  public void playerActedOrReheased(){
    hasActedOrRehearsed = true;
  }
  public void resetActedOrReaheased(){
    hasActedOrRehearsed = false;
  }
  public void playerMoved(){
    hasMoved = true;
  }
  public void resetMove(){
    hasMoved = false;
  }
  public void setName(String Name){
    name = Name;
  }
  public void addCredits(int amount){
    credits += amount;
  }
  public void setLocation(Room newLocation){
    location = newLocation;
  }
  public void addDollars(int amount){
    dollars += amount;
  }
  public void resetPracticeChips(){
    practiceChips = 0;
  }
  public void removeCredits(int amount){
    credits -= amount;
  }
  public void removeDollars(int amount){
    dollars -= amount;
  }
  public void upgradeRank(int newRank){
    rank = newRank;
  }
  public void acceptRole(Role newRole){
    currentRole = newRole;
    if (newRole.isOnCard()) {
      this.location.getCard().addPlayer(this);
    }
    else {
      this.location.addExtra(this);
    }
  }
  public void resetRole(){
    currentRole = null;
  }
  public void rehearsal(){
    //player gets +1 to thier practice chips
    hasActedOrRehearsed = true;
    practiceChips++;
  }
  

  //player getters
  public boolean getHasMoved(){
    return hasMoved;
  }
  public Room getLocation(){
    return location;
  }
  public int getPracticeChips(){
    return practiceChips;
  }
  public int getRank(){
    return rank;
  }
  public int getScore(){
    return dollars + credits + (rank*5);
  }
  public Role getCurrentRole(){
    return currentRole;
  }
  public String getName(){
    return name;
  }
  public int getId() {
    return this.idNumber;
  }

  public ArrayList<String> getPossibleActions() {
    ArrayList<String> actions = new ArrayList<String>();
    if (!this.isWorking) {
      if (!this.hasMoved) {
        actions.add("move");
      }
      if (this.getPossibleRoles().size() > 0) {
        actions.add("take role");
      }
    }
    else if (!hasActedOrRehearsed) {
      actions.add("act");
      if (this.practiceChips < this.currentRole.getLevel()) {
        actions.add("rehearse");
      }
    }
    return actions;
  }

  public boolean canMove() {
    if (this.isWorking || this.hasMoved) {
      return false;
    }
    else {
      return true;
    }
  }

  public boolean canWork() {
    if (this.isWorking || this.getPossibleRoles().size() == 0) {
      return false;
    }
    else {
      return true;
    }
  }

  public boolean canAct() {
    if (this.isWorking && !this.hasActedOrRehearsed) {
      return true;
    }
    else {
      return false;
    }
  }

  public boolean canRehearse() {
    if (this.isWorking && !this.hasActedOrRehearsed
    && this.practiceChips < this.currentRole.getLevel()) {
      return true;
    }
    else {
      return false;
    }
  }

  public boolean canUpgrade() {
    if (this.location.getName() == "office") {
      return true;
    }
    else {
      return false;
    }
  }

  public String[] getPossibleMoves(){
    return this.location.getNeighbors();
  }
  public ArrayList<Role> getPossibleRoles(){
 
    Role[] onCardRoles = this.location.getCard().getRoles();
    Role[] offCardRoles = this.location.getRoles();
    ArrayList<Role> avaliableRoles = new ArrayList<Role>();
    for (int i = 0; i < onCardRoles.length;i++){
      if (onCardRoles[i].getLevel() <= this.rank){
        avaliableRoles.add(onCardRoles[i]);
      }
    }
    for (int i = 0; i < offCardRoles.length;i++){
      if (offCardRoles[i].getLevel() <= this.rank){
        avaliableRoles.add(offCardRoles[i]);
      }
    }
    return avaliableRoles;
  }
  public int getAffordableUpgrades(){
    //determine availible ranks for upgrade to Player
    int highestRank = 0;
    if ((credits <= 25) || (dollars <= 40)){
      return highestRank + 6;
    }else if ((credits <= 20) || (dollars <= 28)){
      return highestRank + 5;
    }else if ((credits <= 15) || (dollars <= 18)){
      return highestRank + 4;
    }else if ((credits <= 10) || (dollars <= 10)){
      return highestRank + 3;
    }else if ((credits <= 5) || (dollars <= 4)){
      return highestRank + 2;
    }else{
      return highestRank;
    }
  }
  public int getDollars(){
    return dollars;
  }
  public int getCredits(){
    return credits;
  }
  //player getters



  public boolean act() {
    
    int budget = this.location.getCard().getBudget(); //access xml file and assign budget of the current scene card
    int dieRoll = (int) (Math.random() * 6) + 1;
    playerActedOrReheased();
    int result = dieRoll + this.practiceChips;
    if (budget <= result) {
      this.location.removeShotCounter();
      if (this.currentRole.isOnCard()){
        addCredits(2);
      }else{
        addDollars(2);
      }
      if (this.location.getShotCounters() == 0){
        this.location.calculateBonuses(this.location.getBudget());
      }
      return true;
    }else{
        if (!this.currentRole.isOnCard()){
            addDollars(1);
        }
        return false;
    }
  }
  
  
  public boolean enoughDollars(int amount){
    if (amount <= dollars){
      return true;
    }else{
      return false;
    }
  }
  public boolean enoughCredits(int amount){
    if (amount <= credits){
      return true;
    }else{
      return false;
    }
  }

  public void move (Room location) {
    this.setLocation(location);
    this.hasMoved = true;
  }
}