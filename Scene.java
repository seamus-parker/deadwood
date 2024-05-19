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

  public Role[] getRoles() {
    return this.roles;
  }
  public int getBudget() {
    return this.budget;
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

  public Scene() {}

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
