import org.w3c.dom.Document;
import javax.xml.parsers.ParserConfigurationException;
import java.util.*;

public class Deadwood {
    public static void main(String[] args) throws ParserConfigurationException{
        XMLParser parser = new XMLParser();
        View v = new View();
        int numPlayers = Integer.valueOf(args[0]);
        Board b = new Board(v.getPlayerNames(numPlayers), numPlayers, 
        parser.readRoomData("board.xml"), parser.readSceneData("cards.xml"), parser.getUpgradeData("board.xml"));
        Random rnd = new Random();
        b.setActivePlayer(rnd.nextInt(numPlayers));
        while (b.getDays() > 0) {
            while (b.activeScenes() > 1) {
                Player p = b.getActivePlayer();
                v.activePlayer(p.getName(), p.getDollars(), p.getCredits(),
                               p.getRank(), p.getLocation().getName());
                    while (p.getPossibleActions().size() > 0) {
                        v.possibleActions(p.getPossibleActions());
                        String action = v.actionInput();
                        while (!b.validateActionInput(p, action)) {
                            System.out.format("Invalid action \'%s\' given. Please enter a valid action.\n", action);
                            action = v.actionInput();
                        }
                        if (action == "end") { break; }
                        else {
                            // execute action
                            if (action == "move") {
                                String start = p.getLocation().getName();
                                String end = v.getMoveSelection(p.getPossibleMoves());
                                p.move(b.getRoomByName(end));
                                v.playerMoved(p.getName(), start, end);
                            }
                            else if (action == "work") {
                                Role role = v.getRoleSelection(p.getPossibleRoles());
                                p.acceptRole(role);
                                v.playerTookRole(p.getName(), 
                                                 p.getCurrentRole().getName(), 
                                                 p.getLocation().getName());
                            }
                            else if (action == "act") {
                                boolean success = p.act();
                                v.playerActed(success);
                            }
                            else if (action == "rehearse") {
                                p.rehearsal();
                                v.playerRehearsed(p.getName(), p.getPracticeChips());
                            }
                            else if (action == "upgrade") {
                                v.upgradeTable(b.getUpgrades());
                                int rank = v.getUpgradeLevel();
                                int currency = v.getCurrency();
                                while (!p.canAffordUpgrade(rank, b.getUpgrades(), currency)) {
                                    System.out.println("Can\'t afford selected upgrade, please try again.");
                                    rank = v.getUpgradeLevel();
                                    currency = v.getCurrency();
                                }
                                p.upgradeRank(rank);
                                if (currency == 0) {
                                    p.removeDollars(b.getUpgrades()[0][rank - 2]);
                                }
                                else if (currency == 1) {
                                    p.removeCredits(b.getUpgrades()[1][rank - 2]);
                                }
                            }
                            else if (action == "locations") {

                            }
                            else if (action == "endgame") {
                                
                            }
                        }
                    }
                    
                // next player's turn
            }

        }

    }
    
}