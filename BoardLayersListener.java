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

   private static BoardLayersListener board;
   private static Board b;
   // JLabels
   JLabel boardlabel;
   JLabel cardlabel;
   JLabel playerlabel;
   JLabel mLabel;

   //array for tracking the front of cards so they can be removed from the board
   JLabel[] cardFronts = new JLabel[0]; 

   //Array for tracking card backs attached to each room so they can be hidden
   JLabel[] cardBacks = new JLabel[10];

   // event info display
   JLabel eventInfoDisplay = new JLabel();

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

   // upgrade buttons
   JButton[] upgradeButtons = {new JButton(), new JButton(), new JButton(), new JButton(), new JButton(), 
                               new JButton(), new JButton(), new JButton(), new JButton(), new JButton()};

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
   JLabel playerMoneyLabel = new JLabel();
   JLabel playerCreditsLabel = new JLabel();
   JLabel playerLocationLabel = new JLabel();
   JLabel playerRankLabel = new JLabel();
   JLabel playerPracticeChipsLabel = new JLabel();
   // JLabel playerDiceLabel = new JLabel();

   // Player dice labels
   JLabel[] playerJLabels = new JLabel[8];

  // JLayered Pane
  JLayeredPane bPane;

  //String array for dice
  String[] diceStrings = {"b","r","g","o","p","c","v","y","w"};
  
  // Constructor
  

  public BoardLayersListener() {}

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
      //  cardlabel.setBounds(969,28,205,115);
       cardlabel.setOpaque(true);
      
       // Add the card to the lower layer
       bPane.add(cardlabel, Integer.valueOf(1));
       
       //call function place players icon in starting locations
       
       //set the card backs on all rooms
       for(int i=0; i<rooms.length; i++){
         if(rooms[i].getShots()>0){
            int x = rooms[i].getXCoordinates();
            int y = rooms[i].getYCoordinates();
            JLabel cardBack = new JLabel();
            ImageIcon cBIcon =  new ImageIcon("img/CardBack-small.jpg");
            cardBack.setIcon(cBIcon); 
            cardBack.setBounds(x,y,205,115);
            cardBack.setOpaque(true);
            // Add the scene card to the lower level
            bPane.add(cardBack, Integer.valueOf(1));
            rooms[i].setCardBackPosition(i);
            cardBacks[i] =cardBack;
         }

      }



       //set all players in their starting locations
       int yCord = 270;
       int xCord = 990;
       String rank = "1";
       if(numOfPlayers>6){
         rank = "2";
       }
       for(int i = 0;i < numOfPlayers;i++){
         playerJLabels[i] = new JLabel();
         ImageIcon pIcon = new ImageIcon("img/dice/dice/"+diceStrings[i]+rank+".png");
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
         b.setVisible(false);
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

      // addSceneBacks(sets);

      // create upgrade buttons
      int[][] upgradeCosts = {{4,10,18,28,40},{5,10,15,20,25}};
      String currency = "dollars";
      for (int i = 0; i < upgradeCosts.length; i++) {
         for (int j = 0; j < upgradeCosts[0].length; j++) {
            JButton b = upgradeButtons[(i*5)+j];
            b.setBackground(Color.white);
            b.setBounds(0, 0, 225, 20);
            b.setText("Rank " + Integer.toString(j+2) + " (" +
                      upgradeCosts[i][j] + " " + currency + ")");
            b.addMouseListener(new boardMouseListener());
            b.setVisible(false);
            bPane.add(b, Integer.valueOf(2));
         }
         currency = "credits";
      }

      // Create player info display
      currentPlayerLabel.setBounds(icon.getIconWidth() + 10, 420, 250, 20);
      playerMoneyLabel.setBounds(icon.getIconWidth() + 10, 440, 100, 20);
      playerCreditsLabel.setBounds(icon.getIconWidth() + 10, 460, 100, 20);
      playerLocationLabel.setBounds(icon.getIconWidth() + 10, 480, 200, 20);
      playerRankLabel.setBounds(icon.getIconWidth() + 10, 500, 100, 20);
      playerPracticeChipsLabel.setBounds(icon.getIconWidth() + 10, 520, 200, 20);
      // playerDiceLabel.setBounds(icon.getIconHeight() + 50, 450, 46, 46);

      bPane.add(currentPlayerLabel, Integer.valueOf(2));
      bPane.add(playerMoneyLabel, Integer.valueOf(2));
      bPane.add(playerCreditsLabel, Integer.valueOf(2));
      bPane.add(playerLocationLabel, Integer.valueOf(2));
      bPane.add(playerRankLabel, Integer.valueOf(2));
      bPane.add(playerPracticeChipsLabel, Integer.valueOf(2));
      // bPane.add(playerDiceLabel, Integer.valueOf(2));
      
      // create event info display
      eventInfoDisplay.setBounds(icon.getIconWidth()+10, 550, 200, 200);
      eventInfoDisplay.setText("<html>Welcome to DEADWOOD<br>Good Luck!");
      bPane.add(eventInfoDisplay, Integer.valueOf(2));

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
      // ArrayList<String> a = new ArrayList<>();
      // a.add("work");
      // a.add("move");
      // actionMenu(a);
      // clearActionMenu();
      // test player info

      // Player testPlayer = new Player("Seamus", 1, rooms[0]);
      // testPlayer.addCredits(3);
      // testPlayer.addDollars(12);
      // testPlayer.upgradeRank(4);
      // testPlayer.setLocation(rooms[1]);
      // playerInfo(testPlayer);
      // roleMenu(testPlayer);
      // scalePlayerDown(testPlayer, shotXLoc, shotYLoc);
      // moveDie(testPlayer,rooms[3]);
      // hideSceneBacks(sets[0]);
      
   }  
   
   //function to hide a scene Backs once scene is completed
   public void hideSceneBack(Room room){
      int pos = room.getCardBackPos();
      cardBacks[pos].setVisible(false);
   }
   public void makeSceneBacksVisible(Room[] room){
      for(int i = 0;cardBacks.length>i;i++){
         cardBacks[i].setVisible(true);
         room[i].setCardJlabelPosition(-1);
      }
   }
   public void flipSceneCard(Room room){
      //get the card associated with the room and place it above the card back
      cardlabel = new JLabel();
      ImageIcon cIcon =  new ImageIcon("img/cards/cards/"+room.getCard().getImString());
      cardlabel.setIcon(cIcon); 
      cardlabel.setBounds(room.getXCoordinates(),room.getYCoordinates(),205,115);
      cardlabel.setOpaque(true);
      cardFronts = cardFrontArrHelper(cardlabel, cardFronts, room);
      // Add the scene card to the upper level
      bPane.add(cardlabel, Integer.valueOf(2));
   }
   public JLabel[] cardFrontArrHelper(JLabel card, JLabel[] oldArray,Room room){
      JLabel[] newArray = new JLabel[oldArray.length+1];
      for (int i =0;i<oldArray.length;i++){
         newArray[i]=oldArray[i];
      }
      newArray[oldArray.length] = card;
      room.setCardJlabelPosition(oldArray.length);
      return newArray;
   }


  public void enableMovement(String[] movementOptions){
   //enable appropiate movement buttons
   //recives a String[] of the availible locations ot move to
   //loop checks wich locations are availible to be moved too and enables the 
   //appropriate buttons.
   bCancel.setBounds(1210, 30, 100, 20);
   bCancel.setVisible(true);
   for (int i =0; i< movementOptions.length;i++){
      if (movementOptions[i].equals("trailer")){
         bTrailers.setVisible(true);
         bTrailers.setEnabled(true);
      }else if (movementOptions[i].equals("office")){
         bCastingOffice.setVisible(true);
         bCastingOffice.setEnabled(true);
      }else if (movementOptions[i].equals("Main Street")){
         bMainSteet.setVisible(true);
         bMainSteet.setEnabled(true);
      }else if (movementOptions[i].equals("Saloon")){
         bSaloon.setVisible(true);
         bSaloon.setEnabled(true);
      }else if (movementOptions[i].equals("Ranch")){
         bRanch.setVisible(true);
         bRanch.setEnabled(true);
      }else if (movementOptions[i].equals("Secret Hideout")){
         bSecretHideout.setVisible(true);
         bSecretHideout.setEnabled(true);
      }else if (movementOptions[i].equals("Bank")){
         bBank.setVisible(true);
         bBank.setEnabled(true);
      }else if (movementOptions[i].equals("Church")){
         bChurch.setVisible(true);
         bChurch.setEnabled(true);
      }else if (movementOptions[i].equals("Hotel")){
         bHotel.setVisible(true);
         bHotel.setEnabled(true);
      }else if (movementOptions[i].equals("Train Station")){
         bTrainStation.setVisible(true);
         bTrainStation.setEnabled(true);
      }else if (movementOptions[i].equals("Jail")){
         bJail.setVisible(true);
         bJail.setEnabled(true);
      }else if (movementOptions[i].equals("General Store")){
         bGeneralStore.setVisible(true);
         bGeneralStore.setEnabled(true);
      }
   }
  }
  //also function for removing player die from card
  //also used for upgrading player in the view
  public void scalePlayerDown(Player player, Room room){
   int[] corodinates = getIdleCorodinates(player, room);
   int x = corodinates[1];
   int y = corodinates[0];
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
  //also serves as the takeRole function in view, player only scaled up when on a role 
  public void scalePlayerUp(Player player, int x, int y){
   playerlabel = playerJLabels[player.getId()];
   ImageIcon pIcon = new ImageIcon("img/dice/dice/"+diceStrings[player.getId()]+String.valueOf(player.getRank())+".png");
   playerlabel.setIcon(pIcon);  
   playerlabel.setBounds(x,y,46,46);
   bPane.add(playerlabel,Integer.valueOf(3));
  }
  //all players must be scaled down before calling this function
  public void resetPlayerLocation(int numOfPlayers){
   int yCord = 270;
   int xCord = 990;
   for(int i = 0;i < numOfPlayers;i++){
      playerJLabels[i].setVisible(false);
      playerJLabels[i].setBounds(xCord,yCord,20,20);
      yCord += 20;
      playerJLabels[i].setVisible(true);

   }
  }


   public void startingPlayerLocation(int numOfPlayers){
      int yCord = 270;
      int xCord = 990;
      String rank = "1";
      if(numOfPlayers>6){
         rank = "2";
      }
      for(int i = 0;i < numOfPlayers;i++){
         playerJLabels[i] = new JLabel();
         ImageIcon pIcon = new ImageIcon("img/dice/dice/"+diceStrings[i]+rank+".png");
         Image scaledPlImage = pIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
         ImageIcon smallpIcon = new ImageIcon(scaledPlImage);
         playerJLabels[i].setIcon(smallpIcon);
         playerJLabels[i].setBounds(xCord,yCord,20,20);
         yCord += 20;
         playerJLabels[i].setVisible(true);
         bPane.add(playerJLabels[i], Integer.valueOf(3));
       }
   }

   //to do
   public void moveDie(Player player, Room room){
      int[] corodinates = getIdleCorodinates(player, room);
      playerJLabels[player.getId()].setVisible(false);
      playerJLabels[player.getId()].setBounds(corodinates[1],corodinates[0],20,20);
      playerJLabels[player.getId()].setVisible(true);

   }

   public void removeSceneCard(Room room){
      int pos = room.getCardJlabelPosition();
      bPane.remove(cardFronts[pos]);
      bPane.validate();
      bPane.repaint();
   }

 
   
   public int[] getIdleCorodinates(Player player,Room location) {
      String name = location.getName();
      //array returned has form {yCord,xCord} 
      int[] idleCorodinatesTS = {270 + (20*player.getId()),10};//yCord = 270 +=20, xCord = 10 for Train Station
      int[] idleCorodinatesSH = {850,250 + (20*player.getId())};//yCord = 850, xCord = 250 +=20 for Secret Hideout
      int[] idleCorodinatesC = {700,750 + (20*player.getId())};//yCord = 700, xCord = 750 +=20 for Church
      int[] idleCorodinatesH = {480 + (20*player.getId()),1010};//yCord = 480 +=20, xCord = 1010 for Hotel
      int[] idleCorodinatesMS = {190,1030 + (20*player.getId())};//yCord = 190, xCord = 1030 +=20 for Main Street
      int[] idleCorodinatesJ = {210,390 + (20*player.getId())};//yCord = 210, xCord = 390 +=20 for Jail
      int[] idleCorodinatesGS = {280 + (20*player.getId()),210};//yCord = 280 +=20, xCord = 210 for General Store
      int[] idleCorodinatesR = {525 + (20*player.getId()),540};//yCord = 525 +=20, xCord = 540 for Ranch
      int[] idleCorodinatesB = {470 + (20*player.getId()),970};//yCord = 470 +=20, xCord = 970 for Bank
      int[] idleCorodinatesS = {270 + (20*player.getId()),940};//yCord = 270 +=20, xCord = 940 for Saloon
      int[] idleCorodinatesCO = {505 + (20*player.getId()),10};//yCord = 505 +=20, xCord = 10 for Casting office
      int[] idleCorodinatesT = {270 + (20*player.getId()),990};//yCord = 270 +=20, xCord = 990 for Trailers
      switch(name) {
         case "Train Station":
            return idleCorodinatesTS;
         case "Secret Hideout":
            return idleCorodinatesSH;
         case "Church":
            return idleCorodinatesC;
         case "Hotel":
            return idleCorodinatesH;
         case "Main Street":
            return idleCorodinatesMS;
         case "Jail":
            return idleCorodinatesJ;
         case "General Store":
            return idleCorodinatesGS;
         case "Ranch":
            return idleCorodinatesR;
         case "Bank":
            return idleCorodinatesB;
         case "Saloon":
            return idleCorodinatesS;
         case "trailer":
            return idleCorodinatesT;
         case "office":
            return idleCorodinatesCO;
      }
      return idleCorodinatesT;
   }
   
  








   // TODO: implement functions

   // display player info (Player p)

   public void playerInfo(Player p) {
      currentPlayerLabel.setText("Active player: " + p.getName());
      playerMoneyLabel.setText("Dollars: " + Integer.toString(p.getDollars()));
      playerCreditsLabel.setText("Credits: " + Integer.toString(p.getCredits()));
      playerLocationLabel.setText("Location: " + p.getLocation().getName());
      playerRankLabel.setText("Rank: " + p.getRank());
      playerPracticeChipsLabel.setText("Practice chips: " + p.getPracticeChips());
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
   bCancel.setVisible(false);
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

   // upgrade menu

   public void upgradeMenu(Player p, int[][] upgrades) {
      int yLoc = 30;
      String currency = "";
      ArrayList<int[]> possible = p.getPossibleUpgrades(upgrades);
      for (int i = 0; i < possible.size(); i++) {
         if (possible.get(i)[2] == 0) {
            currency = "dollars";
         }
         else {
            currency = "credits";
         }
         upgradeButtons[i].setLocation(1210, yLoc);
         upgradeButtons[i].setVisible(true);
         upgradeButtons[i].setText(String.format("Rank %d (%d %s)", 
                                   possible.get(i)[0], possible.get(i)[1], currency));
         yLoc += 30;
      }
      
      bCancel.setBounds(1210, yLoc, 225, 20);
      bCancel.setVisible(true);
   }

   public void clearUpgradeMenu() {
      for (int i = 0; i < upgradeButtons.length; i++) {
         upgradeButtons[i].setVisible(false);
      }
      bCancel.setVisible(false);
   }

   // move player dice (Player player, Room location)

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

   public void beginPlayerTurn(Player p) {
      playerInfo(p);
      actionMenu(p.getPossibleActions());
   }

   // This class implements Mouse Events

   // TODO: implement functions
   //getIdleCoordinates(Room r)
   //scalePlayerDown(Player p, Room r)
   //moveDie(Player p, Room r)
   //removeScene(Room r)
   //resetPlayerLocations(int numPlayers)

   // TODO: event info display
   // create display
   // playerActed
   public void playerActedED(Player player, boolean result){
      if (result == true){
         if (player.getCurrentRole().isOnCard()){
            eventInfoDisplay.setText("<html>"+player.getName()+" has acted succesfully. They recived 2 credits for thier work.");
         }else{
            eventInfoDisplay.setText("<html>"+player.getName()+" has acted succesfully. They recived 1 dollar and 1 credit.");
         }
      }else if(player.getCurrentRole().isOnCard()) {
         eventInfoDisplay.setText("<html>"+player.getName()+" has acted unsuccesfully. They recived nothing");
      }else{
         eventInfoDisplay.setText("<html>"+player.getName()+" has acted unsuccesfully. They recived 1 dollar for thier effort");
      }
   }

   // playerMoved
   public void playerMovedED(Player player){
      eventInfoDisplay.setText("<html>"+player.getName()+" has moved. "+ player.getName()+" is now located in the "+player.getLocation().getName());
   }
   // playerRehearsed
   public void playerRehearsedED(Player player){
      eventInfoDisplay.setText("<html>"+player.getName()+" has rehearsed. "+player.getName()+ " gained + 1 practice chips");
   }
   // playerUpgraded
   public void playerUpgradedED(Player player){
      eventInfoDisplay.setText("<html>"+player.getName()+" has upgraded their rank. "+player.getName()+"\'s new rank is "+player.getRank());
   }
   // playerTurnBegan
   public void playerTurnBeganED(Player player){
      eventInfoDisplay.setText("<html>"+player.getName()+" has begun thier turn. What will thier next move be?");
   }
   // gameEnded
   public void gameEndedED(Player player){
      eventInfoDisplay.setText("<html>The game has ended! "+player.getName()+" is the winner.<br>");
   }
   // playerTookRole
   public void playerTookRoleED(Player player){
      eventInfoDisplay.setText("<html>"+player.getName()+" has accepted a new role. "+player.getName()+" is now playing "+player.getCurrentRole().getName());
   }
   // sceneWrapped
   public void sceneWrappedED(Room room){
      eventInfoDisplay.setText("<html>"+room.getName() +" has finished filming.");
   }
   // dayEnded
   public void dayEndedED(){
      eventInfoDisplay.setText("<html>The day has ended. The board has been reset and new scene cards added.");
   }


   class boardMouseListener implements MouseListener{

      // Controller
      public void mouseClicked(MouseEvent e){
         Player p = b.getActivePlayer();
         Object s = e.getSource();
         if (s == bAct && bAct.isEnabled()) {
            boolean success = p.act();
            board.playerActedED(p, success);
            if (success) {
               board.removeShotCounter(p.getLocation());
            }
            if (p.getLocation().getShotCounters() == 0) {
               board.sceneWrappedED(p.getLocation());
               removeSceneCard(p.getLocation());
               hideSceneBack(p.getLocation());
               for (Player thisPlayer : b.getPlayers()) {
                  if (thisPlayer.getLocation().getName().equals(p.getLocation().getName()) && thisPlayer.getIsWorking()) {
                     board.scalePlayerDown(thisPlayer, p.getLocation());
                     thisPlayer.setWorking(false);
                  }
               }
               if (b.activeScenes() <= 1) {
                  board.dayEndedED();
                  b.endDay();
                  b.resetSceneCards();
                  for (Player thisPlayer : b.getPlayers()) {
                     board.scalePlayerDown(thisPlayer, b.getRoomByName("trailer"));
                  }
                  p.endTurn();
                  b.nextPlayer();
                  for (Room r : b.getRooms()) {
                     board.replaceShotCounters(r);
                     board.makeSceneBacksVisible(b.getRooms());
                  }
               }
            }
            board.clearActionMenu();
            board.actionMenu(p.getPossibleActions());
            board.playerInfo(p);
         } 
         else if (s == bRehearse && bRehearse.isEnabled()) {
            p.rehearsal();
            //board.actionMenu(p.getPossibleActions());
            board.playerInfo(p);
            board.playerRehearsedED(p);
         }
         else if (s == bMove && bMove.isEnabled()){
            board.clearActionMenu();
            board.enableMovement(p.getPossibleMoves());
         }
         else if (s == bTrailers) {
            p.move(b.getRoomByName("trailer"));
            board.moveDie(p, b.getRoomByName("trailer"));
            board.disableMovementButtons();
            board.actionMenu(p.getPossibleActions());
            board.playerInfo(p);
            board.playerMovedED(p);
         }
         else if (s == bMainSteet) {
            p.move(b.getRoomByName("Main Street"));
            board.moveDie(p, b.getRoomByName("Main Street"));
            board.disableMovementButtons();
            board.actionMenu(p.getPossibleActions());
            if (p.getLocation().getCardJlabelPosition() == -1) {
               flipSceneCard(p.getLocation());
            }
            board.playerInfo(p);
            board.playerMovedED(p);
         }
         else if (s == bSaloon) {
            p.move(b.getRoomByName("Saloon"));
            board.moveDie(p, b.getRoomByName("Saloon"));
            board.disableMovementButtons();
            board.actionMenu(p.getPossibleActions());
            if (p.getLocation().getCardJlabelPosition() == -1) {
               flipSceneCard(p.getLocation());
            }
            board.playerInfo(p);
            board.playerMovedED(p);
         }
         else if (s == bGeneralStore) {
            p.move(b.getRoomByName("General Store"));
            board.moveDie(p, b.getRoomByName("General Store"));
            board.disableMovementButtons();
            board.actionMenu(p.getPossibleActions());
            if (p.getLocation().getCardJlabelPosition() == -1) {
               flipSceneCard(p.getLocation());
            }
            board.playerInfo(p);
            board.playerMovedED(p);
         }
         else if (s == bTrainStation) {
            p.move(b.getRoomByName("Train Station"));
            board.moveDie(p, b.getRoomByName("Train Station"));
            board.disableMovementButtons();
            board.actionMenu(p.getPossibleActions());
            if (p.getLocation().getCardJlabelPosition() == -1) {
               flipSceneCard(p.getLocation());
            }
            board.playerInfo(p);
            board.playerMovedED(p);
         }
         else if (s == bJail) {
            p.move(b.getRoomByName("Jail"));
            board.moveDie(p, b.getRoomByName("Jail"));
            board.disableMovementButtons();
            board.actionMenu(p.getPossibleActions());
            if (p.getLocation().getCardJlabelPosition() == -1) {
               flipSceneCard(p.getLocation());
            }
            board.playerInfo(p);
            board.playerMovedED(p);
         }
         else if (s == bCastingOffice) {
            p.move(b.getRoomByName("office"));
            board.moveDie(p, b.getRoomByName("office"));
            board.disableMovementButtons();
            board.actionMenu(p.getPossibleActions());
            board.playerInfo(p);
            board.playerMovedED(p);
         }
         else if (s == bRanch) {
            p.move(b.getRoomByName("Ranch"));
            board.moveDie(p, b.getRoomByName("Ranch"));
            board.disableMovementButtons();
            board.actionMenu(p.getPossibleActions());
            if (p.getLocation().getCardJlabelPosition() == -1) {
               flipSceneCard(p.getLocation());
            }
            board.playerInfo(p);
            board.playerMovedED(p);
         }
         else if (s == bSecretHideout) {
            p.move(b.getRoomByName("Secret Hideout"));
            board.moveDie(p, b.getRoomByName("Secret Hideout"));
            board.disableMovementButtons();
            board.actionMenu(p.getPossibleActions());
            if (p.getLocation().getCardJlabelPosition() == -1) {
               flipSceneCard(p.getLocation());
            }
            board.playerInfo(p);
            board.playerMovedED(p);
         }
         else if (s == bChurch) {
            p.move(b.getRoomByName("Church"));
            board.moveDie(p, b.getRoomByName("Church"));
            board.disableMovementButtons();
            board.actionMenu(p.getPossibleActions());
            if (p.getLocation().getCardJlabelPosition() == -1) {
               flipSceneCard(p.getLocation());
            }
            board.playerInfo(p);
            board.playerMovedED(p);
         }
         else if (s == bBank) {
            p.move(b.getRoomByName("Bank"));
            board.moveDie(p, b.getRoomByName("Bank"));
            board.disableMovementButtons();
            board.actionMenu(p.getPossibleActions());
            if (p.getLocation().getCardJlabelPosition() == -1) {
               flipSceneCard(p.getLocation());
            }
            board.playerInfo(p);
            board.playerMovedED(p);
         }
         else if (s == bHotel) {
            p.move(b.getRoomByName("Hotel"));
            board.moveDie(p, b.getRoomByName("Hotel"));
            board.disableMovementButtons();
            board.actionMenu(p.getPossibleActions());
            if (p.getLocation().getCardJlabelPosition() == -1) {
               flipSceneCard(p.getLocation());
            }
            board.playerInfo(p);
            board.playerMovedED(p);
         }
         
         else if (s == bTakeRole && bTakeRole.isEnabled()) {
            board.clearActionMenu();
            board.roleMenu(p);
         }
         else if (s == bUpgrade && bUpgrade.isEnabled()) {
            board.clearActionMenu();
            board.upgradeMenu(p, b.getUpgrades());
         }
         else if (s == bEndTurn) {
            p.endTurn();
            b.nextPlayer();
            board.beginPlayerTurn(b.getActivePlayer());
            board.playerTurnBeganED(b.getActivePlayer());
         }
         else if (s == bCancel) {
            board.clearRoleMenu();
            board.disableMovementButtons();
            board.clearUpgradeMenu();
            board.actionMenu(p.getPossibleActions());
         }

         else if (s == bEndGame){
            board.clearActionMenu();
            board.clearRoleMenu();
            board.clearUpgradeMenu();
            board.disableMovementButtons();
            board.gameEndedED(b.calculateWinner().get(0));
            // board.setVisible(false);
            // board.dispose();
         }
         else if (roleButtons[0].isVisible()) {
            ArrayList<Role> possible = p.getPossibleRoles();
            for (int i = 0; i < possible.size(); i++) {
               if (s == roleButtons[i]) {
                  p.acceptRole(possible.get(i));
                  board.assignRole(p, possible.get(i));
                  board.clearRoleMenu();
                  board.actionMenu(p.getPossibleActions());
                  board.playerTookRoleED(p);
               }
            }
         }
         else if (upgradeButtons[0].isVisible()) {
            ArrayList<int[]> possible = p.getPossibleUpgrades(b.getUpgrades());
            for (int i = 0; i < possible.size(); i++) {
               if (s == upgradeButtons[i]) {
                  p.upgradeRank(possible.get(i)[0]);
                  if (possible.get(i)[2] == 0) {
                     p.removeDollars(possible.get(i)[1]);
                  }
                  else if (possible.get(i)[2] == 1) {
                     p.removeCredits(possible.get(i)[1]);
                  }
                  board.scalePlayerDown(p, b.getRoomByName("office"));
                  board.playerInfo(p);
                  board.clearUpgradeMenu();
                  board.actionMenu(p.getPossibleActions());
                  board.playerUpgradedED(p);
               }
            }
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
      // int numPlayers = 2;
      // b = new Board(new String[] {"a", "b", "c","d","e","f","g", "h"}, numPlayers,
            // rooms, scenes, upgrades);
      b = new Board(names, numPlayers, rooms, scenes, upgrades);
      Random rnd = new Random();
      Room[] sets = b.sets;
      b.setActivePlayer(rnd.nextInt(numPlayers));
      b.resetSceneCards();
      boolean gameEnded = false;

      // testing =======

      // for (Player testP : b.getPlayers()) {
      //    testP.addDollars(100);
      //    testP.addCredits(10);
      //    testP.setLocation(b.getRoomByName("office"));
      // }

      // ===============
      board = new BoardLayersListener(numPlayers, rooms);
      board.setVisible(true);
      board.beginPlayerTurn(b.getActivePlayer());


      // Take input from the user about number of players
      // ArrayList<String> testArray = new ArrayList<String>();
      // Player testPlayer = new Player("Seamus", 0, rooms[0]);
      // testPlayer.addCredits(30);
      // testPlayer.addDollars(100);
      // testPlayer.upgradeRank(0);
      // testPlayer.setLocation(rooms[0]);
      // board.upgradeMenu(testPlayer, upgrades);
   }
}