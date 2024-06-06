import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import java.util.*;

public class Deadwood {
    static BoardLayersListener board;
    static Board b;
    public static void main(String[] args) throws ParserConfigurationException{
      XMLParser parser = new XMLParser();
      board = new BoardLayersListener();
      int numPlayers = Integer.valueOf(JOptionPane.showInputDialog(board, "How many players?"));
      while (numPlayers < 2 || numPlayers > 8) {
         numPlayers = Integer.valueOf(JOptionPane.showInputDialog(board, "Invalid number selected. How many players?"));
      }
      String[] names = new String[numPlayers];
      for (int i = 0; i < numPlayers; i++) {
         names[i] = JOptionPane.showInputDialog(board, String.format("Player %d name: ", i+1));
      }
      Room[] rooms = parser.readRoomData("board.xml");
      Scene[] scenes = parser.readSceneData("cards.xml");
      int[][] upgrades = parser.getUpgradeData("board.xml");
      b = new Board(names, numPlayers, rooms, scenes, upgrades);
      Random rnd = new Random();
      b.setActivePlayer(rnd.nextInt(numPlayers));
      b.resetSceneCards();
      board = new BoardLayersListener(numPlayers, rooms, b);
      board.setNumDays(b.getDays());
      board.setVisible(true);
      board.beginPlayerTurn(b.getActivePlayer());
   }
}