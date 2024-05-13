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
        public void readBookData(Document d){
        
            Element root = d.getDocumentElement();
            
            NodeList books = root.getElementsByTagName("book");
            
            for (int i=0; i<books.getLength();i++){
                
                System.out.println("Printing information for book "+(i+1));
                
                //reads data from the nodes
                Node book = books.item(i);
                String bookCategory = book.getAttributes().getNamedItem("category").getNodeValue();
                System.out.println("Category = "+bookCategory);
                
                //reads data
                                             
                NodeList children = book.getChildNodes();
                
                for (int j=0; j< children.getLength(); j++){
                    
                  Node sub = children.item(j);
                
                  if("title".equals(sub.getNodeName())){
                     String bookLanguage = sub.getAttributes().getNamedItem("lang").getNodeValue();
                     System.out.println("Language = "+bookLanguage);
                     String title = sub.getTextContent();
                     System.out.println("Title = "+title);
                     
                  }
                  
                  else if("author".equals(sub.getNodeName())){
                     String authorName = sub.getTextContent();
                     System.out.println(" Author = "+authorName);
                     
                  }
                  else if("year".equals(sub.getNodeName())){
                     String yearVal = sub.getTextContent();
                     System.out.println(" Publication Year = "+yearVal);
                     
                  }
                  else if("price".equals(sub.getNodeName())){
                     String priceVal = sub.getTextContent();
                     System.out.println(" Price = "+priceVal);
                     
                  }
                                 
                
                } //for childnodes
                
                System.out.println("\n");
                
            }//for book nodes
        
        }// method
    
                // reads data from XML file and prints data
        public Set[] readSetData(Document d){
        
            Element root = d.getDocumentElement();
            
            NodeList sets = root.getElementsByTagName("set");
            Set[] gameBoard = new Set[sets.getLength()];
            String roomName = "";
            String[] roomNeighbors = new String[0];
            int roomTakes = 0;
            Role[] roomParts = new Role[0];

            for (int i=0; i<sets.getLength();i++){
                                
                //reads data from the nodes
                Node set = sets.item(i);
                roomName = set.getAttributes().getNamedItem("name").getNodeValue();
                
                //reads data
                                             
                NodeList children = set.getChildNodes();
                
                for (int j=0; j< children.getLength(); j++){
                    
                  Node sub = children.item(j);
                
                  if("neighbors".equals(sub.getNodeName())){
                     NodeList neighbors = sub.getChildNodes();
                     int neighborIndex = 0;
                     roomNeighbors = new String[(neighbors.getLength()-1)/2];
                     for (int k = 0; k < neighbors.getLength(); k++) {
                        Node neighbor = neighbors.item(k);
                        
                        if ("neighbor".equals(neighbor.getNodeName())) {
                            roomNeighbors[neighborIndex] = neighbor.getAttributes().getNamedItem("name").getNodeValue();
                           neighborIndex++;
                        }
                     }
                  }
                  
                  else if("takes".equals(sub.getNodeName())){
                     NodeList takes = sub.getChildNodes();
                     Integer numTakes = (takes.getLength()-1) / 2;
                     roomTakes = numTakes;
                  }
                  else if("parts".equals(sub.getNodeName())){
                     NodeList parts = sub.getChildNodes();
                     int partIndex = 0;
                     String partName = "";
                     int partLevel = 0;
                     String partLine ="";
                     roomParts = new Role[(parts.getLength()-1)/2];
                     for (int k = 0; k < parts.getLength(); k++) {
                         Node part = parts.item(k);

                         if ("part".equals(part.getNodeName())) {
                             partName = part.getAttributes().getNamedItem("name").getNodeValue();
                             partLevel = Integer.valueOf(part.getAttributes().getNamedItem("level").getNodeValue());
                             NodeList partInfo = part.getChildNodes();
                            for (int m = 0; m < partInfo.getLength(); m++) {
                                Node subpart = partInfo.item(m);
                                if ("line".equals(subpart.getNodeName())) {
                                    partLine = subpart.getTextContent();
                                }
                            }
                            roomParts[partIndex] = new Role(partLevel, partName, partLine, false);
                            partIndex++;
                         }
                         
                     }
                  }
                  // else if("price".equals(sub.getNodeName())){
                  //    String priceVal = sub.getTextContent();
                  //    System.out.println(" Price = "+priceVal);
                     
                  // }
                                 
                
                } //for childnodes
                gameBoard[i] = new Set(roomName, roomNeighbors, roomParts, roomTakes);
                
            }//for book nodes
        
            return gameBoard;

        }// method
        
    



}//class