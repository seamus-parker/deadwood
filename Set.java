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
    public void calculateBonuses(){}

    public Set(String myName, Room[] myNeighbors, Role[] myRoles, int numShots) {
        super.setName(myName);
        super.setNeighbors(myNeighbors);
        this.roles = myRoles;
        this.shots = numShots;
        this.replaceShotCounters();
    }
    public Set(String myName, String[] myNeighbors, Role[] myRoles, int numShots) {
        super.setName(myName);
        super.setNeighborNames(myNeighbors);
        this.roles = myRoles;
        this.shots = numShots;
        this.replaceShotCounters();
    }

    public void print() {
        System.out.println("name: " + super.getName());
        System.out.println("neighbors: ");
        String[] neighborNames = this.getNeighborNames();
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
