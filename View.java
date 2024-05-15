public class View 
{

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
    public void possibleActions(String[] actions) {
        System.out.print("Possible actions: ");
        for (int i = 0; i < actions.length - 1; i++) {
            System.out.print(actions[i] + ", ");
        }
        System.out.println(actions[actions.length - 1]);
    }

    // Display possible move locations for a player
    public void possibleMoves(String[] locations) {
        System.out.print("Possible moves: ");
        for (int i = 0; i < locations.length - 1; i++) {
            System.out.print(locations[i] + ", ");
        }
        System.out.println(locations[locations.length - 1]);
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
    
    // Display confirmation that a player has taken a certain role
    public void playerTookRole(String player, String role, String set) {
        System.out.println(player + " is now playing " + role + " at " + set);
    }

    // Display possible upgrades available to the player
    public void possibleUpgrades(int[] rank, int[] dollarCost, int[] creditCost) {}

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
    
}