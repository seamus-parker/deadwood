public class Role {
    
    int level;
    String name = "none";
    String line;
    boolean onCard;
    int x;
    int y; 
    int h;
    int w;
    boolean taken = false;

    public Role() {}

    public Role(int myLevel, String myName, String myLine, boolean isOnCard,
                int myX, int myY, int myH, int myW) {
        this.level = myLevel;
        this.name = myName;
        this.line = myLine;
        this.onCard = isOnCard;
        this.x = myX;
        this.y = myY;
        this.h = myH;
        this.w = myW;
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getLine() {
        return this.line;
    }
    
    public boolean isOnCard() {
        return this.onCard;
    }

    public boolean isTaken() {
        return this.taken;
    }

    public void setTaken(boolean b) {
        this.taken = b;
    }

    public void print() {
        System.out.println("name: " + this.name);
        System.out.println("level: " + Integer.toString(this.level));
        System.out.println("line: " + this.line);
        System.out.println("on card?: " + this.onCard);
    }

}
