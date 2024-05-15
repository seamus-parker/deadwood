public class Scene
{
  String name;
  int budget;
  String description;
  Role[] roles;
  String img;
  int sceneNum;

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
