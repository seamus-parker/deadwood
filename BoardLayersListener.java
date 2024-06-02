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
   // JLayered Pane
   JLayeredPane bPane;

   // Constructor

   public BoardLayersListener(Room[] rooms) {

      // Set the title of the JFrame
      super("Deadwood");
      // Set the exit option for the JFrame
      setDefaultCloseOperation(EXIT_ON_CLOSE);

      // Create the JLayeredPane to hold the display, cards, dice and buttons
      bPane = getLayeredPane();

      // Create the deadwood board
      boardlabel = new JLabel();
      ImageIcon icon = new ImageIcon("img/board.jpg");
      boardlabel.setIcon(icon);
      boardlabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

      // Add the board to the lowest layer
      bPane.add(boardlabel, new Integer(0));

      // Set the size of the GUI
      setSize(icon.getIconWidth() + 200, icon.getIconHeight());

      // Add a scene card to this room
      cardlabel = new JLabel();
      ImageIcon cIcon = new ImageIcon("img/cards/cards/01.png");
      cardlabel.setIcon(cIcon);
      cardlabel.setBounds(21, 69, 205, 115);
      cardlabel.setOpaque(true);

      // Add the card to the lower layer
      bPane.add(cardlabel, new Integer(1));

      // Add a dice to represent a player.
      // Role for Crusty the prospector. The x and y co-ordiantes are taken from
      // Board.xml file
      playerlabel = new JLabel();
      ImageIcon pIcon = new ImageIcon("img/dice/dice/r2.png");
      playerlabel.setIcon(pIcon);
      // playerlabel.setBounds(114,227,pIcon.getIconWidth(),pIcon.getIconHeight());
      playerlabel.setBounds(114, 227, 46, 46);
      playerlabel.setVisible(false);
      bPane.add(playerlabel, new Integer(3));

      // Create the Menu for action buttons
      mLabel = new JLabel("MENU");
      mLabel.setBounds(icon.getIconWidth() + 40, 0, 100, 20);
      bPane.add(mLabel, new Integer(2));

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
      bPane.add(bMainSteet, new Integer(2));

      bTrailers.setBounds(icon.getIconWidth() - 165, 280, 120, 27);
      bPane.add(bTrailers, new Integer(2));

      bSaloon.setBounds(icon.getIconWidth() - 520, 405, 100, 30);
      bPane.add(bSaloon, new Integer(2));

      bBank.setBounds(icon.getIconWidth() - 520, 597, 100, 35);
      bPane.add(bBank, new Integer(2));

      bJail.setBounds(345, 150, 75, 30);
      bPane.add(bJail, new Integer(2));

      bGeneralStore.setBounds(375, 405, 200, 30);
      bPane.add(bGeneralStore, new Integer(2));

      bTrainStation.setBounds(30, 190, 200, 30);
      bPane.add(bTrainStation, new Integer(2));

      bCastingOffice.setBounds(22, 470, 190, 35);
      bPane.add(bCastingOffice, new Integer(2));

      bRanch.setBounds(280, 600, 100, 40);
      bPane.add(bRanch, new Integer(2));

      bSecretHideout.setBounds(20, 850, 210, 40);
      bPane.add(bSecretHideout, new Integer(2));

      bChurch.setBounds(icon.getIconWidth() - 525, 855, 100, 35);
      bPane.add(bChurch, new Integer(2));

      bHotel.setBounds(icon.getIconWidth() - 190, 862, 100, 30);
      bPane.add(bHotel, new Integer(2));

      // Create Action buttons

      int yLoc = 30;

      for (JButton b : actionButtons) {
         b.setBackground(Color.white);
         b.setBounds(icon.getIconWidth() + 10, yLoc, 100, 20);
         b.addMouseListener(new boardMouseListener());
         b.setVisible(false);
         bPane.add(b, new Integer(2));
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

      // test player info
      Player testPlayer = new Player("Seamus", 0, rooms[0]);
      testPlayer.addCredits(3);
      testPlayer.addDollars(12);
      testPlayer.upgradeRank(4);
      testPlayer.setLocation(rooms[3]);
      playerInfo(testPlayer);
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

   // display possible moves

   // display possible roles

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

   // add player to role (Role, Room location, boolean onCard)

   // This class implements Mouse Events

   class boardMouseListener implements MouseListener {

      // Code for the different button clicks
      public void mouseClicked(MouseEvent e) {

         if (e.getSource() == bAct) {
            playerlabel.setVisible(true);
            System.out.println("Acting is Selected\n");
         } else if (e.getSource() == bRehearse) {
            System.out.println("Rehearse is Selected\n");
         } else if (e.getSource() == bMove) {
            // enable appropiate movement buttons
            String[] movementOptions = new String[2];
            // movementoptions is a placeholder, should call get possible moves
            // on the active player and copy the returned String[] to movementOptions
            // loop checks wich locations are availible to be moved too and enables the
            // appropriate buttons.
            for (int i = 0; i < movementOptions.length; i++) {
               if (movementOptions[0] == "Trailers") {
                  bTrailers.setVisible(true);
                  bTrailers.setEnabled(true);
               } else if (movementOptions[i] == "Casting Office") {
                  bCastingOffice.setVisible(true);
                  bCastingOffice.setEnabled(true);
               } else if (movementOptions[i] == "Main Street") {
                  bMainSteet.setVisible(true);
                  bMainSteet.setEnabled(true);
               } else if (movementOptions[i] == "Saloon") {
                  bSaloon.setVisible(true);
                  bSaloon.setEnabled(true);
               } else if (movementOptions[i] == "Ranch") {
                  bRanch.setVisible(true);
                  bRanch.setEnabled(true);
               } else if (movementOptions[i] == "Secret Hideout") {
                  bSecretHideout.setVisible(true);
                  bSecretHideout.setEnabled(true);
               } else if (movementOptions[i] == "Bank") {
                  bBank.setVisible(true);
                  bBank.setEnabled(true);
               } else if (movementOptions[i] == "Church") {
                  bChurch.setVisible(true);
                  bChurch.setEnabled(true);
               } else if (movementOptions[i] == "Hotel") {
                  bHotel.setVisible(true);
                  bHotel.setEnabled(true);
               } else if (movementOptions[i] == "Train Station") {
                  bTrainStation.setVisible(true);
                  bTrainStation.setEnabled(true);
               } else if (movementOptions[i] == "Jail") {
                  bJail.setVisible(true);
                  bJail.setEnabled(true);
               } else if (movementOptions[i] == "General Store") {
                  bGeneralStore.setVisible(true);
                  bGeneralStore.setEnabled(true);
               }

            }

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
      boolean gameEnded = false;
      BoardLayersListener board = new BoardLayersListener(rooms);
      board.setVisible(true);

      // Take input from the user about number of players
      JOptionPane.showInputDialog(board, "How many players?");
      ArrayList<String> testArray = new ArrayList<String>();
   }
}