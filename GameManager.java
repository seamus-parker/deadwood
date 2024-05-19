public class GameManager {

    //end of turns
    //end of day
    //game setup
    public void endTurn(){
        this.player.resetActedOrReaheased();
        this.Player.resetMove();
    }
    public void endDay(){
        //remove players still on a role
        this.Player.acceptRole(null);
        //move players to tailers
        this.Player.setLocation("trailers");
    }
    public void GameSetup(int numberOfPlayers){
        this.player.setLocation('trailers');
        if (numberOfPlayers > 6){
            this.player.upgradeRank(2);
        }else if (numberOfPlayers = 6){
            this.player.addCredits(4);
        }else if (numberOfPlayers = 5){
            this.player.addCredits(2);
        }else if (numberOfPlayers < 4){
            //reduce the number of days by one
        }
    }

    public boolean validateActionInput(Player p, String action) {
        if (action == "move") {
            return p.canMove();
        }
        else if (action == "work") {
            return p.canWork();
        }
        else if (action == "act") {
            return p.canAct();
        }
        else if (action == "rehearse") {
            return p.canRehearse();
        }
        else if (action == "upgrade") {
            return p.canUpgrade();
        }
        else if (action == "invalid") {
            return false;
        }
        else {
            return true;
        }
    }

    public void execute(Player p, String action) {
        
    }
}
