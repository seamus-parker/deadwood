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
                                String move = v.getMoveSelection(p.getPossibleMoves());
                                p.move(b.getRoomByName(move));
                            }
                        }
                    }
                    
                // next player's turn
            }

        }

    }
    
}