/*

   Deadwood GUI helper file
   Author: Moushumi Sharmin
   This file shows how to create a simple GUI using Java Swing and Awt Library
   Classes Used: JFrame, JLabel, JButton, JLayeredPane

*/

import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Image.*;

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
  JButton[] actionButtons = {bMove, bTakeRole, bAct, bRehearse, bUpgrade, bEndTurn, bEndGame};
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
  JButton[] movementButtons = {bTrailers, bMainSteet, bSaloon,bGeneralStore,bTrainStation,bJail,bCastingOffice,bRanch,bSecretHideout,bChurch,bBank,bHotel};
  JLabel[] playerJLabels = new JLabel[8];


  // JLayered Pane
  JLayeredPane bPane;

  //String array for dice
  String[] diceStrings = {"b","r","g","o","p","c","v","y","w"};
  
  // Constructor
  
  public BoardLayersListener(int numOfPlayers) {
      
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
       bPane.add(boardlabel, new Integer(0));
      
       // Set the size of the GUI
       setSize(icon.getIconWidth()+200,icon.getIconHeight());
       
       // Add a scene card to this room
       cardlabel = new JLabel();
       ImageIcon cIcon =  new ImageIcon("img/cards/cards/01.png");
       cardlabel.setIcon(cIcon); 
       cardlabel.setBounds(969,28,205,115);
       cardlabel.setOpaque(true);
      
       // Add the card to the lower layer
       bPane.add(cardlabel, new Integer(1));
       
      

    
       // Add a dice to represent a player. 
       // Role for Crusty the prospector. The x and y co-ordiantes are taken from Board.xml file
       int yCord = 270;
       for(int i = 0;i < numOfPlayers;i++){
         playerJLabels[i] = new JLabel();
         ImageIcon pIcon = new ImageIcon("img/dice/dice/"+diceStrings[i]+"1"+".png");
         Image scaledPlImage = pIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
         ImageIcon smallpIcon = new ImageIcon(scaledPlImage);
         playerJLabels[i].setIcon(smallpIcon);
         //playerlabel.setBounds(114,227,pIcon.getIconWidth(),pIcon.getIconHeight());  
         playerJLabels[i].setBounds(990,yCord,20,20);
         yCord += 20;
         playerJLabels[i].setVisible(true);
         bPane.add(playerJLabels[i],new Integer(3));
         
       }
      
       // Create the Menu for action buttons
       mLabel = new JLabel("MENU");
       mLabel.setBounds(icon.getIconWidth()+40,0,100,20);
       bPane.add(mLabel,new Integer(2));

       //add movement buttons to the board
       for (JButton b : movementButtons) {
         b.setBackground(Color.white);
         b.addMouseListener(new boardMouseListener());
         b.setFont(new Font("Arial", Font.PLAIN,20));
         b.setVisible(false);
         b.setEnabled(false);
       }
       //Add all location buttons to the board, must be done individually since all have unique locations
       bMainSteet.setBounds(icon.getIconWidth()-220,150,200,40);
       bPane.add(bMainSteet,new Integer(2));

       bTrailers.setBounds(icon.getIconWidth()-165,280,120,27);
       bPane.add(bTrailers,new Integer(2));

       bSaloon.setBounds(icon.getIconWidth()-520,405,100,30);
       bPane.add(bSaloon,new Integer(2));

       bBank.setBounds(icon.getIconWidth()-520,597,100,35);
       bPane.add(bBank,new Integer(2));

       bJail.setBounds(345,150,75,30);
       bPane.add(bJail,new Integer(2));

       bGeneralStore.setBounds(375,405,200,30);
       bPane.add(bGeneralStore,new Integer(2));

       bTrainStation.setBounds(30,190,200,30);
       bPane.add(bTrainStation,new Integer(2));

       bCastingOffice.setBounds(22,470,190,35);
       bPane.add(bCastingOffice,new Integer(2));

       bRanch.setBounds(280,600,100,40);
       bPane.add(bRanch,new Integer(2));

       bSecretHideout.setBounds(20,850,210,40);
       bPane.add(bSecretHideout,new Integer(2));

       bChurch.setBounds(icon.getIconWidth()-525,855,100,35);
       bPane.add(bChurch,new Integer(2));

       bHotel.setBounds(icon.getIconWidth()-190,862,100,30);
       bPane.add(bHotel,new Integer(2));
      

      


   

       // Create Action buttons

       int yLoc = 30;

       for (JButton b : actionButtons) {
         b.setBackground(Color.white);
         b.setBounds(icon.getIconWidth()+10,yLoc,100, 20);
         b.addMouseListener(new boardMouseListener());
         b.setVisible(false);
         bPane.add(b, new Integer(2));
         yLoc+=30;
       }

      // test actionMenu
        ArrayList<String> a = new ArrayList<>();
        a.add("work");
        a.add("move");
        actionMenu(a);
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
  public void scalePlayerDown(Player player){
   String color = String.valueOf(player.getId());
   String rank = String.valueOf(player.getRank());

  }
  public void scalePlayerUp(Player player, int x, int y){
   playerlabel = new JLabel();
   ImageIcon pIcon = new ImageIcon("img/dice/dice/"+diceStrings[player.getId()]+String.valueOf(player.getRank())+".png");
   playerlabel.setIcon(pIcon);  
   playerlabel.setBounds(x,y,46,46);
   bPane.add(playerlabel,new Integer(3));
  }
  //playerlabel = new JLabel();
  //ImageIcon pIcon = new ImageIcon("img/dice/dice/r2.png");
  //Image scaledPlImage = pIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
  //ImageIcon smallpIcon = new ImageIcon(scaledPlImage);
  //playerlabel.setIcon(smallpIcon);
  //playerlabel.setBounds(114,227,pIcon.getIconWidth(),pIcon.getIconHeight());  
  //playerlabel.setBounds(114,227,20,20);
  //playerlabel.setVisible(false);
  //bPane.add(playerlabel,new Integer(3));








  // TODO: implement functions

  // display player info (Player p)

  // display scene card (Room location, int sceneNum)

  // display card back (Room location)

  // display action buttons (ArrayList<String> possibleActions)
  public void actionMenu(ArrayList<String> possibleActions) {
      for (JButton b : actionButtons) {
         b.setVisible(true);
         b.setEnabled(true);
      }
      bEndGame.setEnabled(true);
      bEndTurn.setEnabled(true);
      for (String s : possibleActions) {
         if (s.equals("move")) {
            bMove.setEnabled(true);
         }
         else if (s.equals("work")) {
            bTakeRole.setEnabled(true);
         }
         else if (s.equals("rehearse")) {
            bRehearse.setEnabled(true);
         }
         else if (s.equals("act")) {
            bAct.setEnabled(true);
         }
         else if (s.equals("upgrade")) {
            bUpgrade.setEnabled(true);
         }
      }
  }
  public void disableMovement(){
   for (int i = 0; i < movementButtons.length;i++){
      movementButtons[i].setVisible(false);
      movementButtons[i].setEnabled(false);
   }
  }

  // display possible moves

  // display possible roles

  // move player dice (Player player, Room location)

  // change player rank (Player player, int rank)

  // remove shot counters (Room location)

  // replace shot counters (Room location)

  // add player to role (Role, Room location, boolean onCard)
  
  // This class implements Mouse Events
  
  class boardMouseListener implements MouseListener{
  
      // Code for the different button clicks
      public void mouseClicked(MouseEvent e) {
         
         if (e.getSource()== bAct){
            playerlabel.setVisible(true);
            System.out.println("Acting is Selected\n");
         }
         else if (e.getSource()== bRehearse){
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


  public static void main(String[] args) {
  
    BoardLayersListener board = new BoardLayersListener(8);
    board.setVisible(true);
    
    // Take input from the user about number of players
    JOptionPane.showInputDialog(board, "How many players?"); 
    ArrayList<String> testArray = new ArrayList<String>();
  }
}