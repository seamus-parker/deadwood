public class Room {

    private String name;

    private Room[] neighbors;

    private String[] neighborNames;

    Room[] getNeighbors() {
        return this.neighbors;
    }

    public void setNeighbors(Room[] myNeighbors) {
        this.neighbors = myNeighbors;
    }

    public void setNeighborNames(String[] myNeighbors) {
        this.neighborNames = myNeighbors;
    }

    public String[] getNeighborNames() {
        return this.neighborNames;
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

    public Room() {}

    public Room(String myName, Room[] myNeighbors) {
        this.name = myName;
        this.neighbors = myNeighbors;
    }

}
