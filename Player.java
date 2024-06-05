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
    this.currentRole = new Role();
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
    this.isWorking = true;
    this.hasActedOrRehearsed = true;
    currentRole = newRole;
    newRole.setTaken(true);
    if (newRole.isOnCard()) {
      this.location.getCard().addPlayer(this);
    }
    else {
      this.location.addExtra(this);
    }
  }
  public void resetRole(){
    currentRole = new Role();
  }
  public void rehearsal(){
    //player gets +1 to thier practice chips
    hasActedOrRehearsed = true;
    practiceChips++;
  }
  public void endTurn(){
    this.resetActedOrReaheased();
    this.resetMove();
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

  public ArrayList<int[]> getPossibleUpgrades(int[][] upgrades) {
    ArrayList<int[]> possible = new ArrayList<int[]>();
    for (int i = 0; i < 5; i++) {
      if (this.canAffordUpgrade(i+2, upgrades, 0)) {
         possible.add(new int[] {i+2, upgrades[0][i], 0});
      }
      if (this.canAffordUpgrade(i+2, upgrades, 1)) {
         possible.add(new int[] {i+2, upgrades[1][i], 1});
      }
   }
   return possible;
  }

  public ArrayList<String> getPossibleActions() {
    ArrayList<String> actions = new ArrayList<String>();
    if (this.canMove()) actions.add("move");
    if (this.canWork()) actions.add("work");
    if (this.canAct()) actions.add("act");
    if (this.canRehearse()) actions.add("rehearse");
    if (this.canUpgrade()) actions.add("upgrade");
    return actions;
  }

  public boolean canMove() {
    if (this.isWorking || this.hasMoved || this.hasActedOrRehearsed) {
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
    && this.practiceChips < this.location.getCard().getBudget()) {
      return true;
    }
    else {
      return false;
    }
  }

  public boolean canUpgrade() {
    int minCredits = 0;
    int minDollars = 0;
    boolean canAfford = false;
    switch (this.rank) {
      case 1:
        minCredits = 5;
        minDollars = 4;
        break;
      case 2:
        minCredits = 10;
        minDollars = 10;
        break;
      case 3:
        minCredits = 15;
        minDollars = 18;
        break;
      case 4:
        minCredits = 20;
        minDollars = 28;
        break;
      case 5:
        minCredits = 25;
        minDollars = 40;
        break;
      case 6:
        return false;
    }
    if (this.dollars >= minDollars || this.credits >= minCredits) {
      canAfford = true;
    }
    if (this.location.getName() == "office" && canAfford) {
      return true;
    }
    else {
      return false;
    }
  }

  public String[] getPossibleMoves(){
    return this.location.getNeighbors();
  }

  // get possible roles a player can take on the current set
  public ArrayList<Role> getPossibleRoles(){
    if (this.location.isWrapped()) return new ArrayList<Role>();
    Role[] onCardRoles = this.location.getCard().getRoles();
    Role[] offCardRoles = this.location.getRoles();
    ArrayList<Role> avaliableRoles = new ArrayList<Role>();
    for (int i = 0; i < onCardRoles.length;i++){
      if (onCardRoles[i].getLevel() <= this.rank && !onCardRoles[i].isTaken()){
        avaliableRoles.add(onCardRoles[i]);
      }
    }
    for (int i = 0; i < offCardRoles.length;i++){
      if (offCardRoles[i].getLevel() <= this.rank && !offCardRoles[i].isTaken()){
        avaliableRoles.add(offCardRoles[i]);
      }
    }
    return avaliableRoles;
  }

  public int getDollars(){
    return dollars;
  }
  public int getCredits(){
    return credits;
  }
  public boolean getIsWorking() {
    return this.isWorking;
  }

  public boolean act() {
    
    int budget = this.location.getCard().getBudget(); 
    int dieRoll = (int) (Math.random() * 6) + 1;
    playerActedOrReheased();
    int result = dieRoll + this.practiceChips;
    if (budget <= result) {
      this.location.removeShotCounter();
      if (this.currentRole.isOnCard()){
        addCredits(2);
      }else{
        addDollars(1);
        addCredits(1);
      }
      if (this.location.getShotCounters() == 0){
        this.location.calculateBonuses();
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
    if (amount <= this.dollars){
      return true;
    }else{
      return false;
    }
  }
  public boolean enoughCredits(int amount){
    if (amount <= this.credits){
      return true;
    }else{
      return false;
    }
  }

  public void move (Room location) {
    this.setLocation(location);
    this.hasMoved = true;
  }
  public boolean affordUpgrade(int[][] upgrades){
    if (this.rank == 6){
      return false;
    }else if (enoughCredits(upgrades[1][this.rank - 1])
                               || enoughDollars(upgrades[0][this.rank -1])){
      return true;
    }else{
      return false;
    }
  }
  public boolean canAffordUpgrade(int desiredRank, int[][] upgrades, int typeCurrency){
    if (desiredRank >= 7 || desiredRank <= this.rank){
      return false;
    }
    int price = upgrades[typeCurrency][desiredRank-2];
    if (typeCurrency == 0){
      return enoughDollars(price);

    }else if (typeCurrency ==1){
      return enoughCredits(price);
    }else{
      return false;
    }
  }

  public void setWorking(boolean b) {
    this.isWorking = b;
  }
}