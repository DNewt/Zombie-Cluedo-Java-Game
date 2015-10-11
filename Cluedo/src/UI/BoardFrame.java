package UI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Cluedo.Board;
import Cluedo.Player;
import Cluedo.Setup;

/**
 * The Board frame is the main frame which holds the multiple items which make up the GUI.
 * It is able to speak directly to each individual part to display images.
 * @author newtondavi2
 *
 */
public class BoardFrame extends JFrame{

	public BoardCanvas canvas; // BoardCanvas for drawing the board state
	public DicePanel dicePanel; // DicePanel for displaying the Dice
	private HandPanel handPanel; // HandPanel for displaying the players cards
	private MovePanel movePanel; // MovePanel displays buttons which contain player move choice options
	public CardDisplay cardDisplay; // CardDisplay displays the current card the cursor is over
	public UserPanel userPanel; // The userPanel displays the player whos current turn it is

	private final JPanel topFrame = new JPanel(); // The top contains the Board
	private final JPanel bottomFrame = new JPanel(); // The bottom contains the dice and table area for players hand
	private final JPanel sideFrame = new JPanel(); // The side fram holds the cardDisplay and userPanel

	private JMenuBar menu =  new JMenuBar();;// The Menu bar is contained at the top of super
	private JMenu file = new JMenu("File");

	public BoardFrame(){
		super("Zombie Cluedo"); //Sets name of game to Zombie Cluedo
		handPanel = new HandPanel();
		dicePanel = new DicePanel();
		canvas = new BoardCanvas(this);
		movePanel = new MovePanel();
		cardDisplay = new CardDisplay();
		userPanel = new UserPanel();

		super.setResizable(false); // disable resizing of the frame
		super.setVisible(true); // set the frame to be visible
		canvas.setDoubleBuffered(false); // disable double buffering of the canvas
		super.setLayout(new BorderLayout()); // assign the layout of this frame to be BorderLayout
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent we)
		    {
		        String ObjButtons[] = {"Yea!","Nah!"};
		        int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to close Zombie Cluedo!?","",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
		        if(PromptResult==JOptionPane.YES_OPTION)
		        {
		            System.exit(0);
		        }

		    }
		});

		topFrame.setLayout(new BorderLayout()); // assign layout of type BorderLayout to topFrame
		topFrame.add(canvas, BorderLayout.NORTH); // Add the canvas to topFrame in the NORTH position

		sideFrame.setLayout(new BorderLayout()); // assign layout of type BorderLayout to sideFrame
		sideFrame.add(userPanel, BorderLayout.NORTH); // Add the userPanel in the NORTH position on sideFrame
		sideFrame.add(cardDisplay, BorderLayout.CENTER); // Add the cardDisplay in the CENTER position on sideFrame

		bottomFrame.setLayout(new BorderLayout()); // assign layout of type BorderLayout to bottomFrame
		bottomFrame.add(movePanel, BorderLayout.NORTH); // Add movePanel to bottomFrame in the NORTH position
		bottomFrame.add(dicePanel, BorderLayout.WEST); // add dicePanel to bottomFrame in the WEST position
		bottomFrame.add(handPanel, BorderLayout.EAST); // add handPanel to bottomFrame in the EAST position

		setupMenuBar();

		super.add(menu, BorderLayout.NORTH); // add the JMenuBar menu to this frame in the NORTH position
		super.add(topFrame, BorderLayout.CENTER); // add the topFrame panel to this frame in the CENTER position
		super.add(sideFrame, BorderLayout.EAST); // add the sideFrame panel to this frame in the EAST position
		super.add(bottomFrame, BorderLayout.SOUTH); // add the bottomFrame panel to this frame in the SOUTH position

		super.pack(); // pack components tightly together
		this.setBackground(Color.BLACK);
		Timer timer = new Timer(canvas); //used for animation - not necessary for the current version of Zombie Cluedo so it has been slowed down
		timer.start(); //used for animation - not necessary for the current version of Zombie Cluedo so it has been slowed down
	}

	/**
	 * Set the handPanel to display the current players hand
	 * @param p
	 */
	public void setHandPanel(Player p){
		handPanel.getPlayersHand(p);
	}

	/**
	 * Sets up the about button in the JMenuBar
	 */
	public void setupMenuBar(){
		menu.add(file);
		file.getPopupMenu().setLightWeightPopupEnabled(false);
		JMenuItem about = new JMenuItem("Who made this?");
		about.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
		    	getAbout();
		    }
		});
		file.add(about);
	}

	/**
	 * Sets the current userPanel label to the current player
	 * @param player
	 */
	public void setLabelPanel(Player player) {
		try {
			userPanel.setPanel(player);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Gets the answer for the question asked at the end of making a suggestion WHICH INVOLVES THE MOVEMENT OF A PLAYER CURRENTLY IN THE GAME
	 * "Would you like to move the character and weapon to the room" (YES / NO)
	 * @return
	 */
	public int getAnswer(){

		int count = JOptionPane.YES_NO_OPTION;
		if (JOptionPane.showConfirmDialog(null, "Would you like to move the character and weapon to the room?", "",
		        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
		    count = 1;
		} else {
			count = 0;
		}
		return count;
	}

	/**
	 * Displays the message box stating who the amazing person is who build Zombie Cluedo
	 */
	public void getAbout(){
		int count = JOptionPane.OK_OPTION;
		JOptionPane.showMessageDialog(null, "Zombie Cluedo created by David Newton, newtondavi2", "",
		        JOptionPane.OK_OPTION);
	}

	/**
	 * Displays the player who refuted the suggestion and which card they used to refute this suggestion
	 * @param name
	 * @param card
	 */
	public void getRefute(String name, String card){
		int count = JOptionPane.OK_OPTION;
		JOptionPane.showMessageDialog(null, name + " refuted your suggestion with " + card, "",
		        JOptionPane.OK_OPTION);
	}


	/**
	 * Displays  the player who has been eliminated due to making an absolutely stupid accusation
	 * @param pName
	 */
	public void getEliminated(String pName){
		int count = JOptionPane.OK_OPTION;
		JOptionPane.showMessageDialog(null, pName + " is eliminated", "",
		        JOptionPane.OK_OPTION);
	}

	/**
	 * Displays the person who should technically be refered to as a god as they have won the game of Zombie Cluedo
	 * @param pName
	 */
	public void getWinAcc(String pName) {
		int count = JOptionPane.OK_OPTION;
		JOptionPane.showMessageDialog(null, pName + " Has won the game", "",
		        JOptionPane.OK_OPTION);
	}

	/**
	 * Displays the pane which states the game is over.
	 */
	public void gameOverPopUp() {
		int count = JOptionPane.OK_OPTION;
		JOptionPane.showMessageDialog(null,"THE GAME IS OVER!", "",
		        JOptionPane.OK_OPTION);

	}

}