import org.w3c.dom.Document;
import javax.xml.parsers.ParserConfigurationException;

public class Deadwood {
    public static void main(String[] args) throws ParserConfigurationException{
        XMLParser parser = new XMLParser();
        Document d = parser.getDocFromFile("board.xml");
        Set[] board = parser.readSetData(d);
        for (int i = 0; i < board.length; i++) {
            board[i].print();
        }

    }
    
}