// Example Code for parsing XML file
// Dr. Moushumi Sharmin
// CSCI 345

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class XMLParser{

   
        // building a document from the XML file
        // returns a Document object after loading the book.xml file.
        public Document getDocFromFile(String filename)
        throws ParserConfigurationException{
        {
            
                  
           DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
           DocumentBuilder db = dbf.newDocumentBuilder();
           Document doc = null;
           
           try{
               doc = db.parse(filename);
           } catch (Exception ex){
               System.out.println("XML parse failure");
               ex.printStackTrace();
           }
           return doc;
        } // exception handling
        
        }  
        
    
    
                // reads data from XML file and prints data
        public Room[] readRoomData(String filename) throws ParserConfigurationException{
        
            Document d = getDocFromFile(filename);
            Element root = d.getDocumentElement();
            
            NodeList sets = root.getElementsByTagName("set");
            Room[] gameBoard = new Room[sets.getLength()+2];
            String roomName = "";
            String[] roomNeighbors = new String[0];
            int roomTakes = 0;
            Role[] roomParts = new Role[0];
            int x = 0;
            int y = 0;
            int h = 0;
            int w = 0;

            for (int i=0; i<sets.getLength();i++){
                                
                //reads data from the nodes
                Node set = sets.item(i);
                roomName = set.getAttributes().getNamedItem("name").getNodeValue();
                
                //reads data
                roomNeighbors = getNeighbors(set);
                                             
                NodeList children = set.getChildNodes();
                
                for (int j=0; j< children.getLength(); j++){
                    
                    Node sub = children.item(j);
                
                  
                    if("takes".equals(sub.getNodeName())){
                        NodeList takes = sub.getChildNodes();
                        Integer numTakes = (takes.getLength()-1) / 2;
                        roomTakes = numTakes;
                    }
                    else if("parts".equals(sub.getNodeName())){
                        roomParts = getParts(sub, false);
                    }
                    else if("area".equals(sub.getNodeName())){
                        x = Integer.valueOf(sub.getAttributes().getNamedItem("x").getNodeValue());
                        y = Integer.valueOf(sub.getAttributes().getNamedItem("y").getNodeValue());
                        h = Integer.valueOf(sub.getAttributes().getNamedItem("h").getNodeValue());
                        w = Integer.valueOf(sub.getAttributes().getNamedItem("w").getNodeValue());
                    }
                            
                } //for childnodes
                gameBoard[i] = new Set(roomName, roomNeighbors, roomParts, roomTakes, x, y, h, w);
                
            }//for book nodes

          
            Node trailer = root.getElementsByTagName("trailer").item(0);
            roomName = "trailer";
            roomNeighbors = getNeighbors(trailer);
            int[] area = getArea(trailer);
            x = area[0];
            y = area[1];
            h = area[2];
            w = area[3];
            gameBoard[10] = new Room(roomName, roomNeighbors, x, y, h, w);
    
            Node office = root.getElementsByTagName("office").item(0);
            roomName = "office";
            roomNeighbors = getNeighbors(office);
            area = getArea(trailer);
            x = area[0];
            y = area[1];
            h = area[2];
            w = area[3];
            gameBoard[11] = new Room(roomName, roomNeighbors, x, y, h, w);

            return gameBoard;

        }

        int[] getArea(Node room) {
            NodeList myChildren = room.getChildNodes();
            int[] vals = new int[4];
            for (int i = 0; i < myChildren.getLength(); i++) {
                Node sub = myChildren.item(i);
                if ("area".equals(sub.getNodeName())) {
                    vals[0] = Integer.valueOf(sub.getAttributes().getNamedItem("x").getNodeValue());
                    vals[1] = Integer.valueOf(sub.getAttributes().getNamedItem("y").getNodeValue());
                    vals[2] = Integer.valueOf(sub.getAttributes().getNamedItem("h").getNodeValue());
                    vals[3] = Integer.valueOf(sub.getAttributes().getNamedItem("w").getNodeValue());
                }
            }
            return vals;
        }

        String[] getNeighbors(Node room) {
            NodeList myChildren = room.getChildNodes();
            String[] neighborNames = new String[0];
                for (int j=0; j< myChildren.getLength(); j++){
                    
                  Node sub = myChildren.item(j);
                
                  if("neighbors".equals(sub.getNodeName())){
                     NodeList neighbors = sub.getChildNodes();
                     int neighborIndex = 0;
                     neighborNames = new String[(neighbors.getLength()-1)/2];
                     for (int k = 0; k < neighbors.getLength(); k++) {
                        Node neighbor = neighbors.item(k);
                        
                        if ("neighbor".equals(neighbor.getNodeName())) {
                            neighborNames[neighborIndex] = neighbor.getAttributes().getNamedItem("name").getNodeValue();
                           neighborIndex++;
                        }
                     }
                  }
                }
            return neighborNames;
        }

        Role[] getParts(Node node, boolean onCard) {

            NodeList parts = node.getChildNodes();
            int partIndex = 0;
            String partName = "";
            int partLevel = 0;
            String partLine ="";
            int x = 0;
            int y = 0;
            int h = 0;
            int w = 0;
            Role[] roomParts = new Role[0];
            int numParts = (parts.getLength()-1)/2;
            if (onCard) {
                numParts--;
            }

            roomParts = new Role[numParts];
            for (int k = 0; k < parts.getLength(); k++) {
                Node part = parts.item(k);

                if ("part".equals(part.getNodeName())) {
                    partName = part.getAttributes().getNamedItem("name").getNodeValue();
                    partLevel = Integer.valueOf(part.getAttributes().getNamedItem("level").getNodeValue());
                    NodeList partInfo = part.getChildNodes();
                   for (int m = 0; m < partInfo.getLength(); m++) {
                       Node subpart = partInfo.item(m);
                       if ("area".equals(subpart.getNodeName())) {
                        x = Integer.valueOf(subpart.getAttributes().getNamedItem("x").getNodeValue());
                        y = Integer.valueOf(subpart.getAttributes().getNamedItem("y").getNodeValue());
                        h = Integer.valueOf(subpart.getAttributes().getNamedItem("h").getNodeValue());
                        w = Integer.valueOf(subpart.getAttributes().getNamedItem("w").getNodeValue());

                    }
                       if ("line".equals(subpart.getNodeName())) {
                           partLine = subpart.getTextContent();
                       }
                   }
                   roomParts[partIndex] = new Role(partLevel, partName, partLine, onCard, x, y, h, w);
                   partIndex++;
                }
                
            }
            return roomParts;
        }
        
        public Scene[] readSceneData(String filename) throws ParserConfigurationException{
        
            Document d = getDocFromFile(filename);
            Element root = d.getDocumentElement();

            NodeList cards = root.getElementsByTagName("card");

            Scene[] deck = new Scene[cards.getLength()];

            String name = "";
            int budget = 0;
            String description = "";
            Role[] sceneParts = new Role[0];
            String img = "";
            int sceneNum = 0;

            for (int i = 0; i < cards.getLength(); i++) {
                
                Node card = cards.item(i);
                name = card.getAttributes().getNamedItem("name").getNodeValue();
                budget = Integer.valueOf(card.getAttributes().getNamedItem("budget").getNodeValue());
                img = card.getAttributes().getNamedItem("img").getNodeValue();
                sceneParts = getParts(card, true);
                NodeList sceneChildren = card.getChildNodes();
                for (int j = 0; j < sceneChildren.getLength(); j++) {
                    Node subNode = sceneChildren.item(j);
                    if ("scene".equals(subNode.getNodeName())) {
                        sceneNum = Integer.valueOf(subNode.getAttributes().getNamedItem("number").getNodeValue());
                        description = subNode.getTextContent();
                    }
                }
                deck[i] = new Scene(name, budget, description, sceneParts, img, sceneNum);
            }

            return deck;
        }

        public int[][] getUpgradeData(String filename) throws ParserConfigurationException{
        
            Document d = getDocFromFile(filename);
            Element root = d.getDocumentElement();
            Node office = root.getElementsByTagName("office").item(0);
            NodeList children = office.getChildNodes();
            Node upgradeNode = children.item(5);
            children = upgradeNode.getChildNodes();
            int credits = 0;
            int level = 0;
            int amt = 0;
            int[][] upgrades = new int[2][5];
            for (int i = 0; i < children.getLength(); i++) {
                Node sub = children.item(i);
                if ("upgrade".equals(sub.getNodeName())) {
                    if ("credit".equals(sub.getAttributes().getNamedItem("currency").getNodeValue())) {
                        credits = 1;
                    }
                    else {
                        credits = 0;
                    }
                    level = Integer.valueOf(sub.getAttributes().getNamedItem("level").getNodeValue());
                    amt = Integer.valueOf(sub.getAttributes().getNamedItem("amt").getNodeValue());
                    upgrades[credits][level-2] = amt;
                }
            }
            return upgrades;
        }

}//class