import java.util.ArrayList;
import java.util.Arrays;

public class Scene
{
  String name;
  int budget;
  String description;
  Role[] roles;
  String img;
  int sceneNum;
  ArrayList<Player> playersOnCard = new ArrayList<Player>();

  public void getRoles(String playerSetLocation){
    //use players location to find the scene card on thier set
    //use xml file to find the roles listed on the appopriate scene card
  }
  public int getBudget(String playerSetLocation){
    //use players location to find the scene card on thier set
    //use xml file to return the budget
    int sceneBudget = 0;
    return sceneBudget;
  }

  public Player[] getSortedPlayers() {
    ArrayList<Player> unsorted = this.playersOnCard;
    Player[] sorted = new Player[unsorted.size()];
    Integer[] ranks = new Integer[unsorted.size()];
    for (int i = 0; i < unsorted.size(); i++) {
      ranks[i] = unsorted.get(i).getCurrentRole().getLevel();
    }
    Arrays.sort(ranks);
    for (int i = 0; i < ranks.length; i++) {
      for (int j = 0; j < sorted.length; j++) {
        if (unsorted.get(j).getCurrentRole().getLevel() == ranks[i]) {
          sorted[i] = unsorted.get(j);
        }
      }
    }
    return sorted;
  }

  public ArrayList<Player> getPlayersOnCard() {
    return this.playersOnCard;
  }

  public void addPlayer(Player p) {
    playersOnCard.add(p);
  }

  public Scene(String myName, int myBudget, String myDescription, 
               Role[] myRoles, String myImg, int myNum) {
    this.name = myName;
    this.budget = myBudget;
    this.description = myDescription;
    this.roles = myRoles;
    this.img = myImg;
    this.sceneNum = myNum;
  }

  public void print() {
    System.out.println("name: " + this.name);
    System.out.println("budget: " + Integer.toString(this.budget));
    System.out.println("description: " + this.description);
    System.out.println("roles: ");
    System.out.println(this.roles.length);
    for (int i = 0; i < this.roles.length; i++) {
      this.roles[i].print();
    }
  }
}
