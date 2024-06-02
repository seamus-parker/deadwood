public class Room {

    private String name;

    private String[] neighbors;

    protected int x;
    protected int y;
    protected int h;
    protected int w;

    public int getXCorodinates(Room emptySet){
        return emptySet.x;
    }
    public int getYCorodinates(Room emptySet){
        return emptySet.y;
    }
    public int getHCorodinates(Room emptySet){
        return emptySet.h;
    }
    public int getWCorodinates(Room emptySet){
        return emptySet.w;
    }

    String[] getNeighbors() {
        return this.neighbors;
    }
    public void newCard(Scene card){
    }
    public void resetWrapped(){}

    public void setNeighbors(String[] myNeighbors) {
        this.neighbors = myNeighbors;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String myName) {
        this.name = myName;
    }

    public void removeShotCounter() {}

    public int getShotCounters() {
        return 0;
    }

    public boolean isWrapped() { return true; }

    public Scene getCard() {
        return new Scene();
    }

    public void addExtra(Player p) {}

    public int getBudget() {
        return 0;
    }

    public void calculateBonuses() {}

    public void replaceShotCounters() {}

    public Room() {}

    public Room(String myName, String[] myNeighbors,
                int myX, int myY, int myH, int myW) {
        this.name = myName;
        this.neighbors = myNeighbors;
        this.x = myX;
        this.y = myY;
        this.h = myH;
        this.w = myW;
    }

    public Role[] getRoles() {
        return new Role[0];
    }

    public int[][] getShotCoords() {
        return new int[0][0];
    }

    public void print() {
        System.out.println("name: " + this.name);
        System.out.println("neighbors: ");
        String[] neighborNames = this.getNeighbors();
        for (int i = 0; i < neighborNames.length; i++) {
            System.out.println(neighborNames[i]);
        }
    }

}
