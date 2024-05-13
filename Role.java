public class Role {
    
    int level;
    String name;
    String line;
    boolean onCard;

    public Role(int myLevel, String myName, String myLine, boolean isOnCard) {
        this.level = myLevel;
        this.name = myName;
        this.line = myLine;
        this.onCard = isOnCard;
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

}
