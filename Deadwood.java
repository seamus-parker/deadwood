import org.w3c.dom.Document;
import javax.xml.parsers.ParserConfigurationException;

public class Deadwood {
    public static void main(String[] args) throws ParserConfigurationException{
        XMLParser parser = new XMLParser();
        Document d = parser.getDocFromFile("board.xml");
        Room[] board = parser.readRoomData(d);
        // for (int i = 0; i < board.length; i++) {
        //     board[i].print();
        // }
        Document f = parser.getDocFromFile("cards.xml");
        Scene[] deck = parser.readSceneData(f);
        // for (int i = 0; i < deck.length; i++) {
        //     deck[i].print();
        // }

        int[][] upgrades = parser.getUpgradeData(d);
        // for (int i = 0; i < upgrades.length; i++) {
        //     for (int j = 0; j < upgrades[0].length; j++) {
        //         System.out.format("%d ", upgrades[i][j]);
        //     }
        //     System.out.println();
        // }

    }
    
}