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

        // GameManager gm = new GameManager();
        

    }
    
}