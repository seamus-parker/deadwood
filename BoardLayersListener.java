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

  // JLayered Pane
  JLayeredPane bPane;
  
  // Constructor
  
  public BoardLayersListener() {
      
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
       cardlabel.setBounds(21,69,205,115);
       cardlabel.setOpaque(true);
      
       // Add the card to the lower layer
       bPane.add(cardlabel, new Integer(1));
       
      

    
       // Add a dice to represent a player. 
       // Role for Crusty the prospector. The x and y co-ordiantes are taken from Board.xml file
       playerlabel = new JLabel();
       ImageIcon pIcon = new ImageIcon("img/dice/dice/r2.png");
       playerlabel.setIcon(pIcon);
       //playerlabel.setBounds(114,227,pIcon.getIconWidth(),pIcon.getIconHeight());  
       playerlabel.setBounds(114,227,46,46);
       playerlabel.setVisible(false);
       bPane.add(playerlabel,new Integer(3));
      
       // Create the Menu for action buttons
       mLabel = new JLabel("MENU");
       mLabel.setBounds(icon.getIconWidth()+40,0,100,20);
       bPane.add(mLabel,new Integer(2));

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
      //  ArrayList<String> a = new ArrayList<>();
      //  a.add("work");
      //  a.add("move");
      //  actionMenu(a);
  }

  // TODO: implement functions

  // display player info (Player p)

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
  
    BoardLayersListener board = new BoardLayersListener();
    board.setVisible(true);
    
    // Take input from the user about number of players
    JOptionPane.showInputDialog(board, "How many players?"); 
  }
}