/*

   Deadwood GUI helper file
   Author: Moushumi Sharmin
   This file shows how to create a simple GUI using Java Swing and Awt Library
   Classes Used: JFrame, JLabel, JButton, JLayeredPane

*/

import java.awt.*;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Image.*;
import java.util.Random;

public class BoardLayersListener extends JFrame {

   // JLabels
   JLabel boardlabel;
   JLabel cardlabel;
   JLabel playerlabel;
   JLabel mLabel;

   // Action buttons
   JButton bAct = new JButton("ACT");
   JButton bMove = new JButton("MOVE");
   JButton bTakeRole = new JButton("WORK");
   JButton bRehearse = new JButton("REHEARSE");
   JButton bUpgrade = new JButton("UPGRADE");
   JButton bEndTurn = new JButton("END TURN");
   JButton bEndGame = new JButton("END GAME");
   JButton bCancel = new JButton("CANCEL");
   JButton[] actionButtons = { bMove, bTakeRole, bAct, bRehearse, bUpgrade, bEndTurn, bEndGame };

   // movement buttons
   JButton bTrailers = new JButton("Trailers");
   JButton bMainSteet = new JButton("Main Street");
   JButton bSaloon = new JButton("Saloon");
   JButton bGeneralStore = new JButton("General Store");
   JButton bTrainStation = new JButton("Train Station");
   JButton bJail = new JButton("Jail");
   JButton bCastingOffice = new JButton("Casting Office");
   JButton bRanch = new JButton("Ranch");
   JButton bSecretHideout = new JButton("Secret Hideout");
   JButton bChurch = new JButton("Church");
   JButton bBank = new JButton("Bank");
   JButton bHotel = new JButton("Hotel");
   JButton[] movementButtons = { bTrailers, bMainSteet, bSaloon, bGeneralStore, bTrainStation, bJail, bCastingOffice,
         bRanch, bSecretHideout, bChurch, bBank, bHotel };

   // role buttons
   JButton[] roleButtons = {new JButton(), new JButton(), new JButton(), new JButton(), 
                            new JButton(), new JButton(), new JButton()};

   // shot counters
   JLabel[] mainStreetShots = {new JLabel(),new JLabel(),new JLabel()};
   JLabel[] saloonShots = {new JLabel(),new JLabel()};
   JLabel[] bankShots = {new JLabel()};
   JLabel[] churchShots = {new JLabel(),new JLabel()};
   JLabel[] hotelShots = {new JLabel(),new JLabel(),new JLabel()};
   JLabel[] ranchShots = {new JLabel(),new JLabel()};
   JLabel[] secretHideoutShots = {new JLabel(),new JLabel(),new JLabel()};
   JLabel[] generalStoreShots = {new JLabel(),new JLabel()};
   JLabel[] jailShots = {new JLabel()};
   JLabel[] trainStationShots = {new JLabel(),new JLabel(),new JLabel()};
   JLabel[][] shots = {trainStationShots,secretHideoutShots,churchShots,
                       hotelShots,mainStreetShots,jailShots,generalStoreShots,
                       ranchShots,bankShots, saloonShots};

   // Player info labels
   JLabel currentPlayerLabel = new JLabel("Current player:");
   JLabel playerNameLabel = new JLabel();
   JLabel playerMoneyLabel = new JLabel();
   JLabel playerCreditsLabel = new JLabel();
   JLabel playerLocationLabel = new JLabel();
   JLabel playerRankLabel = new JLabel();

   // Player dice labels
   JLabel[] playerJLabels = new JLabel[8];

  // JLayered Pane
  JLayeredPane bPane;

  //String array for dice
  String[] diceStrings = {"b","r","g","o","p","c","v","y","w"};
  
  // Constructor
  
  public BoardLayersListener(int numOfPlayers, Room[] rooms) {
      
       // Set the title of the JFrame
       super("Deadwood");
       // Set the exit option for the JFrame
       setDefaultCloseOperation(EXIT_ON_CLOSE);
      
       // Create the JLayeredPane to hold the display, cards, dice and buttons
       bPane = getLayeredPane();
    
       // Create the deadwood board
       boardlabel = new JLabel();
       ImageIcon icon =  new ImageIcon("img/board.jpg");
       boardlabel.setIcon(icon); 
       boardlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
      
       // Add the board to the lowest layer
       bPane.add(boardlabel, Integer.valueOf(0));
      
       // Set the size of the GUI
       setSize(icon.getIconWidth()+300,icon.getIconHeight()+75);
       
       // test code for adding scene card locations
       cardlabel = new JLabel();
       ImageIcon cIcon =  new ImageIcon("img/CardBack-small.jpg");
       cardlabel.setIcon(cIcon); 
       cardlabel.setBounds(969,28,205,115);
       cardlabel.setOpaque(true);
      
       // Add the card to the lower layer
       bPane.add(cardlabel, Integer.valueOf(1));
       
      

    
       // Add a die for each player. 
       //yCord = 270 +=20, xCord = 990 for Trailers
       //yCord = 190, xCord = 1030 +=20 for Main Street
       //yCord = 270 +=20, xCord = 940 for Saloon
       //yCord = 280 +=20, xCord = 210 for General Store
       //yCord = 270 +=20, xCord = 10 for Train Station
       //yCord = 210, xCord = 390 +=20 for Jail
       //yCord = 505 +=20, xCord = 10 for Casting office
       //yCord = 525 +=20, xCord = 540 for Ranch
       //yCord = 470 +=20, xCord = 970 for Bank
       int yCord = 470;
       int xCord = 970;
       for(int i = 0;i < 8;i++){
         playerJLabels[i] = new JLabel();
         ImageIcon pIcon = new ImageIcon("img/dice/dice/"+diceStrings[i]+"1"+".png");
         Image scaledPlImage = pIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
         ImageIcon smallpIcon = new ImageIcon(scaledPlImage);
         playerJLabels[i].setIcon(smallpIcon);
         playerJLabels[i].setBounds(xCord,yCord,20,20);
         yCord += 20;
         playerJLabels[i].setVisible(true);
         bPane.add(playerJLabels[i], Integer.valueOf(3));
         
       }
      
       // Create the Menu for action buttons
       mLabel = new JLabel("MENU");
       mLabel.setBounds(icon.getIconWidth()+40,0,100,20);
       bPane.add(mLabel, Integer.valueOf(2));

      // add movement buttons to the board
      for (JButton b : movementButtons) {
         b.setBackground(Color.white);
         b.addMouseListener(new boardMouseListener());
         b.setFont(new Font("Arial", Font.PLAIN, 20));
         b.setVisible(true);
         b.setEnabled(false);
      }
      // Add all location buttons to the board, must be done individually since all
      // have unique locations
      bMainSteet.setBounds(icon.getIconWidth() - 220, 150, 200, 40);
      bPane.add(bMainSteet, Integer.valueOf(2));

      bTrailers.setBounds(icon.getIconWidth() - 165, 280, 120, 27);
      bPane.add(bTrailers, Integer.valueOf(2));

      bSaloon.setBounds(icon.getIconWidth() - 520, 405, 100, 30);
      bPane.add(bSaloon, Integer.valueOf(2));

      bBank.setBounds(icon.getIconWidth() - 520, 597, 100, 35);
      bPane.add(bBank, Integer.valueOf(2));

      bJail.setBounds(345, 150, 75, 30);
      bPane.add(bJail, Integer.valueOf(2));

      bGeneralStore.setBounds(375, 405, 200, 30);
      bPane.add(bGeneralStore, Integer.valueOf(2));

      bTrainStation.setBounds(30, 190, 200, 30);
      bPane.add(bTrainStation, Integer.valueOf(2));

      bCastingOffice.setBounds(22, 470, 190, 35);
      bPane.add(bCastingOffice, Integer.valueOf(2));

      bRanch.setBounds(280, 600, 100, 40);
      bPane.add(bRanch, Integer.valueOf(2));

      bSecretHideout.setBounds(20, 850, 210, 40);
      bPane.add(bSecretHideout, Integer.valueOf(2));

      bChurch.setBounds(icon.getIconWidth() - 525, 855, 100, 35);
      bPane.add(bChurch, Integer.valueOf(2));

      bHotel.setBounds(icon.getIconWidth() - 190, 862, 100, 30);
      bPane.add(bHotel, Integer.valueOf(2));
      
      // Create Action buttons

      int yLoc = 30;
      for (JButton b : actionButtons) {
         b.setBackground(Color.white);
         b.setBounds(icon.getIconWidth() + 10, yLoc, 100, 20);
         b.addMouseListener(new boardMouseListener());
         b.setVisible(false);
         bPane.add(b, Integer.valueOf(2));
         yLoc += 30;
      }
      bCancel.setBackground(Color.white);
      bCancel.setBounds(0, 0, 100, 20);
      bCancel.addMouseListener(new boardMouseListener());
      bCancel.setVisible(false);
      bPane.add(bCancel, Integer.valueOf(2));

      yLoc = 30;
      for (JButton b : roleButtons) {
         b.setBackground(Color.white);
         b.setBounds(icon.getIconWidth() + 10, yLoc, 225, 20);
         b.addMouseListener(new boardMouseListener());
         b.setVisible(false);
         bPane.add(b, Integer.valueOf(2));
         yLoc += 30;      
      }

      // Create player info display
      currentPlayerLabel.setBounds(icon.getIconWidth() + 10, 240, 100, 20);
      playerNameLabel.setBounds(icon.getIconWidth() + 105, 240, 100, 20);
      playerMoneyLabel.setBounds(icon.getIconWidth() + 10, 260, 100, 20);
      playerCreditsLabel.setBounds(icon.getIconWidth() + 10, 280, 100, 20);
      playerLocationLabel.setBounds(icon.getIconWidth() + 10, 300, 200, 20);
      playerRankLabel.setBounds(icon.getIconWidth() + 10, 320, 100, 20);
      bPane.add(currentPlayerLabel, Integer.valueOf(2));
      bPane.add(playerNameLabel, Integer.valueOf(2));
      bPane.add(playerMoneyLabel, Integer.valueOf(2));
      bPane.add(playerCreditsLabel, Integer.valueOf(2));
      bPane.add(playerLocationLabel, Integer.valueOf(2));
      bPane.add(playerRankLabel, Integer.valueOf(2));
      
      // Create shot counters
      int curRoom = 0;
      int shotXLoc = 0;
      int shotYLoc = 0;
      ImageIcon shotIcon = new ImageIcon("img/shot.png");

      for (Room r : rooms) {
         
         int[][] coords = r.getShotCoords();
         if (coords.length > 0) {
            for (int i = 0; i < coords[0].length; i++) {
               shotXLoc = coords[0][i];
               shotYLoc = coords[1][i];
               shots[curRoom][i].setIcon(shotIcon);
               shots[curRoom][i].setBounds(shotXLoc, shotYLoc, 47, 47);
               bPane.add(shots[curRoom][i], Integer.valueOf(3));
            }
            curRoom++;
         }
      }

      // test actionMenu
      ArrayList<String> a = new ArrayList<>();
      a.add("work");
      a.add("move");
      actionMenu(a);
      clearActionMenu();
      // test player info
      Player testPlayer = new Player("Seamus", 0, rooms[0]);
      testPlayer.addCredits(3);
      testPlayer.addDollars(12);
      testPlayer.upgradeRank(4);
      testPlayer.setLocation(rooms[1]);
      playerInfo(testPlayer);
      roleMenu(testPlayer);
      assignRole(testPlayer, rooms[0].getCard().getRoles()[0]);
   }  
   
   public void addSceneBacks(Room[] allRooms){
      for(int i=0; i<allRooms.length; i++){
         if(allRooms[i].getShots()>0){
            int x = allRooms[i].getXCorodinates();
            int y = allRooms[i].getYCorodinates();
            JLabel cardBack = new JLabel();
            ImageIcon cIcon =  new ImageIcon("img/CardBack-small.jpg");
            cardBack.setIcon(cIcon); 
            cardBack.setBounds(x,y,205,115);
            cardBack.setOpaque(true);
            // Add the scene card to the lower level
            bPane.add(cardlabel, new Integer(1));
         }

      }


   }
   public void flipSceneCard(Room room){
      //get the card associated with the room and place it above the card back
      cardlabel = new JLabel();
      ImageIcon cIcon =  new ImageIcon("img/cards/cards/"+room.getCard().getImString());
      cardlabel.setIcon(cIcon); 
      cardlabel.setBounds(room.getXCorodinates(),room.getYCorodinates(),205,115);
      cardlabel.setOpaque(true);
     
      // Add the scene card to the upper level
      bPane.add(cardlabel, new Integer(3));
   }


  public void enableMovement(String[] movementOptions){
   //enable appropiate movement buttons
   //recives a String[] of the availible locations ot move to
   //loop checks wich locations are availible to be moved too and enables the 
   //appropriate buttons.
   for (int i =0; i< movementOptions.length;i++){
      if (movementOptions[i] == "Trailers"){
         bTrailers.setVisible(true);
         bTrailers.setEnabled(true);
      }else if (movementOptions[i]=="Casting Office"){
         bCastingOffice.setVisible(true);
         bCastingOffice.setEnabled(true);
      }else if (movementOptions[i]=="Main Street"){
         bMainSteet.setVisible(true);
         bMainSteet.setEnabled(true);
      }else if (movementOptions[i]=="Saloon"){
         bSaloon.setVisible(true);
         bSaloon.setEnabled(true);
      }else if (movementOptions[i]=="Ranch"){
         bRanch.setVisible(true);
         bRanch.setEnabled(true);
      }else if (movementOptions[i]=="Secret Hideout"){
         bSecretHideout.setVisible(true);
         bSecretHideout.setEnabled(true);
      }else if (movementOptions[i]=="Bank"){
         bBank.setVisible(true);
         bBank.setEnabled(true);
      }else if (movementOptions[i]=="Church"){
         bChurch.setVisible(true);
         bChurch.setEnabled(true);
      }else if (movementOptions[i]=="Hotel"){
         bHotel.setVisible(true);
         bHotel.setEnabled(true);
      }else if (movementOptions[i]=="Train Station"){
         bTrainStation.setVisible(true);
         bTrainStation.setEnabled(true);
      }else if (movementOptions[i]=="Jail"){
         bJail.setVisible(true);
         bJail.setEnabled(true);
      }else if (movementOptions[i]=="General Store"){
         bGeneralStore.setVisible(true);
         bGeneralStore.setEnabled(true);
      }
   }
  }
  public void scalePlayerDown(Player player, int x, int y){
   playerJLabels[player.getId()].setVisible(false);
   String color = diceStrings[player.getId()];
   String rank = String.valueOf(player.getRank());
   ImageIcon pIcon = new ImageIcon("img/dice/dice/"+color+rank+".png");
   Image scaledPlImage = pIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
   ImageIcon smallpIcon = new ImageIcon(scaledPlImage);
   playerJLabels[player.getId()].setIcon(smallpIcon);
   playerJLabels[player.getId()].setBounds(x,y,20,20);
   playerJLabels[player.getId()].setVisible(true);
   bPane.add(playerJLabels[player.getId()],Integer.valueOf(3));



  }
  public void scalePlayerUp(Player player, int x, int y){
   playerlabel = playerJLabels[player.getId()];
   ImageIcon pIcon = new ImageIcon("img/dice/dice/"+diceStrings[player.getId()]+String.valueOf(player.getRank())+".png");
   playerlabel.setIcon(pIcon);  
   playerlabel.setBounds(x,y,46,46);
   bPane.add(playerlabel,Integer.valueOf(3));
  }
  








   // TODO: implement functions

   // display player info (Player p)

   public void playerInfo(Player p) {
      playerNameLabel.setText(p.getName());
      playerMoneyLabel.setText("Dollars: " + Integer.toString(p.getDollars()));
      playerCreditsLabel.setText("Credits: " + Integer.toString(p.getCredits()));
      playerLocationLabel.setText("Location: " + p.getLocation().getName());
      playerRankLabel.setText("Rank: " + p.getRank());
   }

   // display scene card (Room location, int sceneNum)

   // display card back (Room location)

   // display action buttons (ArrayList<String> possibleActions)
   public void actionMenu(ArrayList<String> possibleActions) {
      for (JButton b : actionButtons) {
         b.setVisible(true);
         b.setEnabled(false);
      }
      bEndGame.setEnabled(true);
      bEndTurn.setEnabled(true);
      for (String s : possibleActions) {
         if (s.equals("move")) {
            bMove.setEnabled(true);
         } else if (s.equals("work")) {
            bTakeRole.setEnabled(true);
         } else if (s.equals("rehearse")) {
            bRehearse.setEnabled(true);
         } else if (s.equals("act")) {
            bAct.setEnabled(true);
         } else if (s.equals("upgrade")) {
            bUpgrade.setEnabled(true);
         }
      }
  }
  public void clearActionMenu() {
     for (JButton b : actionButtons) {
        b.setVisible(false);
     }
  }
  public void disableMovementButtons(){
   for (int i = 0; i < movementButtons.length;i++){
      movementButtons[i].setVisible(false);
      movementButtons[i].setEnabled(false);
   }
  }

   // display possible roles

   public void roleMenu(Player p) {
      ArrayList<Role> possibleRoles = p.getPossibleRoles();
      for (int i = 0; i < possibleRoles.size(); i++) {
         Role r = possibleRoles.get(i);
         roleButtons[i].setText(r.getName());
         roleButtons[i].setVisible(true);
      }
      bCancel.setBounds(1210, (possibleRoles.size() * 30) + 30, 225, 20);
      bCancel.setVisible(true);
   }

   public void clearRoleMenu() {
      for (JButton b : roleButtons) {
         b.setVisible(false);
      }
      bCancel.setVisible(false);
   }

   // add player to role (Role, Room location, boolean onCard)

   public void assignRole(Player p, Role r) {
      int[] coords = r.getCoords();
      if (r.isOnCard()) {
         coords[0] += p.getLocation().getXCoordinates();
         coords[1] += p.getLocation().getYCoordinates();
      }
      scalePlayerUp(p, coords[0], coords[1]);
   }

   // move player dice (Player player, Room location)

   // change player rank (Player player, int rank)
   public void setPlayerRank(Player p, int rank) {
      
   }

   // remove shot counters (Room location)
   public void removeShotCounter(Room location) {
      JLabel[] roomShots = getShotCounters(location);
      for (int i = roomShots.length-1; i >= 0; i--) {
         if (roomShots[i].isVisible()) {
            roomShots[i].setVisible(false);
            return;
         }
      }
   }

   // replace shot counters (Room location)
   public void replaceShotCounters(Room location) {
      JLabel[] roomShots = getShotCounters(location);
      for (JLabel l : roomShots) {
         l.setVisible(true);
      }
   }

   public JLabel[] getShotCounters(Room location) {
      String name = location.getName();
      switch(name) {
         case "Train Station":
            return shots[0];
         case "Secret Hideout":
            return shots[1];
         case "Church":
            return shots[2];
         case "Hotel":
            return shots[3];
         case "Main Street":
            return shots[4];
         case "Jail":
            return shots[5];
         case "General Store":
            return shots[6];
         case "Ranch":
            return shots[7];
         case "Bank":
            return shots[8];
         case "Saloon":
            return shots[9];
      }
      return shots[0];
   }

   // This class implements Mouse Events

   class boardMouseListener implements MouseListener {

      // Code for the different button clicks
      public void mouseClicked(MouseEvent e) {

         if (e.getSource() == bAct) {
            playerlabel.setVisible(true);
            System.out.println("Acting is Selected\n");
         } else if (e.getSource() == bRehearse) {
            System.out.println("Rehearse is Selected\n");
         }
         else if (e.getSource()== bMove){
            

            System.out.println("Move is Selected\n");
         }
      }

      public void mousePressed(MouseEvent e) {
      }

      public void mouseReleased(MouseEvent e) {
      }

      public void mouseEntered(MouseEvent e) {
      }

      public void mouseExited(MouseEvent e) {
      }
   }

   public static void main(String[] args) throws ParserConfigurationException{

      XMLParser parser = new XMLParser();
      View v = new View();
      int numPlayers = 4;
      Room[] rooms = parser.readRoomData("board.xml");
      Scene[] scenes = parser.readSceneData("cards.xml");
      int[][] upgrades = parser.getUpgradeData("board.xml");
      Board b = new Board(v.getPlayerNames(numPlayers), numPlayers,
            rooms, scenes, upgrades);
      Random rnd = new Random();
      b.setActivePlayer(rnd.nextInt(numPlayers));
      b.resetSceneCards();
      boolean gameEnded = false;
      BoardLayersListener board = new BoardLayersListener(numPlayers, rooms);
      board.setVisible(true);

      // Take input from the user about number of players
      JOptionPane.showInputDialog(board, "How many players?");
      ArrayList<String> testArray = new ArrayList<String>();
   }
}