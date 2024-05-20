import java.util.ArrayList;
import java.util.Scanner;

public class View 
{

    Scanner sc;

    public String[] getPlayerNames(int numPlayers) {
        String[] names = new String[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            System.out.format("Player %d, enter your name: ", i + 1);
            names[i] = sc.nextLine();
        }
        return names;
    }

    // Print the name and info of the current active player
    public void activePlayer(String name, int money, int credits, int rank, String location) {
        System.out.println("The current active player is: " + name);
        System.out.format("This player has %d dollars, %d credits, and a rank of %d\n", money, credits, rank);
        System.out.println("Their current location is: " + location);
    }

    // Display location of all players and indicate the active player
    public void playerLocations(String[] names, String[] locations, int activePlayer) {
        System.out.println("Displaying current location of all players...");
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + ": " + locations[i]);
        }
        System.out.println("Current active player: " + names[activePlayer]);
    }

    // Display all possible actions a player could take
    public void possibleActions(ArrayList<String> actions) {

        System.out.print("Possible actions: ");
        for (int i = 0; i < actions.size() - 1; i++) {
            System.out.print(actions.get(i) + ", ");
        }
        System.out.println(actions.get(actions.size() - 1));
        
    }

    public String actionInput() {
        System.out.format("What action would you like to take?\n");
        return parseAction(sc.nextLine());
    }

    public String parseAction(String a) {
        if (a.contains("move")) {
            return "move";
        }
        else if (a.contains("work")) {
            return "work";
        }
        else if (a.contains("act")) {
            return "act";
        }
        else if (a.contains("rehearse")) {
            return "rehearse";
        }
        else if (a.contains("upgrade")) {
            return "upgrade";
        }
        else if (a.contains("end turn")) {
            return "end";
        }
        else if (a.contains("end game")) {
            return "endgame";
        }
        else if (a.contains("locations")) {
            return "locations";
        }
        else return "invalid";
    }

    // Display possible move locations for a player
    public void possibleMoves(String[] locations) {
        System.out.print("Possible moves: ");
        for (int i = 0; i < locations.length - 1; i++) {
            System.out.print(locations[i] + ", ");
        }
        System.out.println(locations[locations.length - 1]);
    }

    public String getMoveSelection(String[] locs) {
        possibleMoves(locs);
        System.out.println("Where would you like to move?");
        String move = sc.nextLine();
        while (!validateMove(locs, move)) {
            System.out.println("Invalid move selection. Please try again.");
            move = sc.nextLine();
        }
        return move;
    }

    public boolean validateMove(String[] locs, String input) {
        for (int i = 0; i < locs.length; i++) {
            if (input.contains(locs[i])) {
                return true;
            }
        }
        return false;
    }

    // Display confirmation that a player has moved
    public void playerMoved(String name, String start, String end) {
        System.out.println(name + " has moved from " + start + " to " + end);
    }

    // Display available roles for a player on the current set
    public void possibleRoles(String[] roles){
        System.out.print("Possible roles: ");
        for (int i = 0; i < roles.length - 1; i++) {
            System.out.print(roles[i] + ", ");
        }
        System.out.println(roles[roles.length - 1]);
    }

    public Role getRoleSelection(ArrayList<Role> roles) {
        String[] roleNames = new String[roles.size()];
        for (int i = 0; i < roles.size(); i++) {
            roleNames[i] = roles.get(i).getName();
        }
        possibleRoles(roleNames);
        System.out.println("Which role will you take?");
        String role = sc.nextLine();
        while (validateRole(roleNames, role) == -1) {
            System.out.println("Invalid role selection. Please try again.");
            role = sc.nextLine();
        }
        return roles.get(validateRole(roleNames, role));
    }
    
    public int validateRole(String[] names, String input) {
        for (int i = 0; i < names.length; i++) {
            if (input.contains(names[i])) {
                return i;
            }
        }
        return -1;
    }

    // Display confirmation that a player has taken a certain role
    public void playerTookRole(String player, String role, String set) {
        System.out.println(player + " is now playing " + role + " at " + set);
    }

    // Display possible upgrades available to the player
    public void upgradeTable(int[][] upgrades) {
        System.out.println("Upgrade costs: ");
        System.out.println("Rank   |   Dollars   |   Credits");
        for (int i = 0; i < upgrades[0].length; i++) {
            System.out.println(Integer.toString(i) + "          " + 
            Integer.toString(upgrades[0][i]) + "          " + 
            Integer.toString(upgrades[1][i]));
        }
    }

    // Display confirmation that a player has upgraded their rank
    public void playerUpgraded(String player, int rank) {
        System.out.format(player + " now has a rank of %d\n", rank);
    }

    // Display confirmation that a player has rehearsed and current number of practice chips
    public void playerRehearsed(String player, int practiceChips) {
        System.out.format(player + " now has %d practice chips\n", practiceChips);
    }

    // Display success or failure state of player acting
    public void playerActed(boolean success) {
        if (success) {
            System.out.println("Successfully acted!");
        }
        else {
            System.out.println("Acting failed.");
        }
    }

    // Display confirmation that a player ended their turn
    public void endedTurn(String player){}

    // Declare current day to have ended, display new current day
    public void endedDay(int day){}

    // Display end of game, report winner
    public void endedGame(String winner) {
        System.out.println("Game over! " + winner + " wins!");
    }
    
    public View() {
        this.sc = new Scanner(System.in);
    }

    public int getUpgradeLevel() {
        System.out.println("Select desired upgrade level:");
        return parseLevel(sc.nextLine());
    }

    private int parseLevel(String level) {
        if (level.equals("2")) {
            return 2;
        }
        else if (level.equals("3")) {
            return 3;
        }
        else if (level.equals("4")) {
            return 4;
        }
        else if (level.equals("5")) {
            return 5;
        }
        else if (level.equals("6")) {
            return 6;
        }
        else return 7;
    }

    public int getCurrency() {
        System.out.println("Paying with dollars or credits?");
        String currency = sc.nextLine();
        while (!validateCurrency(currency)) {
            System.out.format("Invalid selection. Please input 'dollars' or 'credits'");
            currency = sc.nextLine();
        }
        if (currency.contains("dollar")) {
            return 0;
        }
        else {
            return 1;
        }
    }

    private boolean validateCurrency(String cur) {
        if (cur.contains("dollar") || cur.contains("credit")) {
            return true;
        }
        else {
            return false;
        }
    }
}