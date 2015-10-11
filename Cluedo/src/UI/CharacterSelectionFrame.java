package UI;

import java.util.ArrayList;
import java.util.Arrays;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Cluedo.Piece;
import Cluedo.Player;
import Cluedo.Setup;

/**
 * The CharacterSelection class is a frame which displays the required panels to display the character choice the user can make
 * Also allows the player to input their own personal name
 * @author newtondavi2
 *
 */
public class CharacterSelectionFrame extends JFrame {

	private JTextField text; //text field for user to enter name
	private JPanel radioButtons; //radio buttons to select choice
	private int playerCount; //amount of players needed to select characters
	private ButtonGroup group; //Button group to hold buttons
	private JButton confirm = new JButton("Confirm");
	private int count = 0;
	private JLabel nameTitle = new JLabel("Player "+(count + 1) + "'s Name:"); //Used to get the current players Name (Displayed as Label)
	private JRadioButton fillIn = new JRadioButton();
	private JRadioButton buttonToRemove; // button to be removed
	Color col = new Color(35,35,35); // colour of frame
	String characterPiece = "";
	private JLabel characters = new JLabel();

	BufferedImage chart; // chart = character
	Font font = new Font("Courier", Font.BOLD,22); // sets the font which is used in this pane
	public static ArrayList<Player> players = new ArrayList<Player>();

	/**
	 * Sets the Character Selection screen to display the characters when the radio buttons are clicked.
	 * Allows the user to input there personal name
	 */
	public CharacterSelectionFrame(){
		super("Select your ZOMBIE!");

		getContentPane().setBackground(col); // sets the background to a darkish black
		this.setLayout(null); // no layout required
		this.setSize(800,600);
		this.setPreferredSize(getSize());
		this.setResizable(false);
		this.confirm.setBounds((getWidth()/2)-60, getHeight()-65, 100, 20); //sets the bounds of the confirm buttom to be in the middle

		this.nameTitle.setBounds(10, 10, 250, 20); //sets the bounds of the name title to be near the top left corner

		nameTitle.setFont(font);
		nameTitle.setForeground(Color.WHITE);

		this.characters.setBounds(350,10,300,460); //sets where the character is displayed and its size
		this.playerCount = playerCount();

		radioButtons = new JPanel(); //radio buttons used for the character selection
		getContentPane().add(characters);
		setupRadioButtons();
		selectCharacter();
		text= new JTextField();
		text.setBounds(10, 40, 150, 25);;
		getContentPane().add(nameTitle);
		getContentPane().add(text);

		this.setVisible(true);
		super.pack();
	}

	/**
	 * Sets up the action listener for the confirm button, the user is able to select their character
	 * and then push confirm. ON confirm the players PlayerName is set to the text entered into TextField text.
	 * and their character piece is set to the chosen zombie.
	 */
	private void selectCharacter() {
		confirm.addActionListener(
				new ActionListener(){

					public void actionPerformed(ActionEvent e){
						if(fillIn.isSelected()){
							JOptionPane.showMessageDialog(CharacterSelectionFrame.this, "Pick your Zombie before pushing confirm!");
						}
						else if(text.getText().length() <= 0 || text.getText().length() >= 13){
							JOptionPane.showMessageDialog(CharacterSelectionFrame.this, "You have entered either to little or to many characters");
						}
						else {
							Setup.players.add(new Player(count, characterPiece));
							Setup.players.get(count).setPName(text.getText());
							text.setText(""); //resets text to be blank
							buttonToRemove.setEnabled(false);
							fillIn.setSelected(true);
							count++;
							nameTitle.setText("Player " + (count + 1) + "'s Name:");
						}
						if(count == playerCount){
							Setup.wait = 1;
							dispose();
						}
					}
				}
				);
		getContentPane().add(confirm);
	}

	/**
	 * Sets up the radio buttons for the individual character chocies.
	 */
	private void setupRadioButtons() {

		ArrayList<String> pieces = new ArrayList<String>();

		pieces.add("Colonel Mustard");
		pieces.add("Miss Scarlett");
		pieces.add("Professor Plum");
		pieces.add("The Reverend Green");
		pieces.add("Mrs White");
		pieces.add("Mrs Peacock");

		validate();
		radioButtons.setLayout(new GridLayout(3,3));
		radioButtons.setOpaque(false);
		radioButtons.setBounds(0, 485, 800, 45);
		group = new ButtonGroup();

		for(String p : pieces){
			final String piece = p;
			final JRadioButton choice = new JRadioButton(p, false);

			choice.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e){

							characters.setIcon(null);

							characterPiece = piece;
							buttonToRemove = choice;

							try{
								switch(piece){
								case "Colonel Mustard":
									chart = ImageIO.read(new File("src/character_images/Colonel_Mustard_Character.png"));
									ImageIcon cm = new ImageIcon(chart);
									characters.setIcon(cm);
									break;

								case "Miss Scarlett":
									chart = ImageIO.read(new File("src/character_images/Miss_Scarlett_Character.png"));
									ImageIcon sc = new ImageIcon(chart);
									characters.setIcon(sc);
									break;

								case "Mrs Peacock":
									chart = ImageIO.read(new File("src/character_images/Mrs_Peacock_Character.png"));
									ImageIcon mp = new ImageIcon(chart);
									characters.setIcon(mp);
									break;

								case "Mrs White":
									chart = ImageIO.read(new File("src/character_images/Mrs_White_Character.png"));
									ImageIcon mw = new ImageIcon(chart);
									characters.setIcon(mw);
									break;

								case "Professor Plum":
									chart = ImageIO.read(new File("src/character_images/Professor_Plum_Character.png"));
									ImageIcon pp = new ImageIcon(chart);
									characters.setIcon(pp);
									break;

								case "The Reverend Green":
									chart = ImageIO.read(new File("src/character_images/The_Reverend_Green_Character.png"));
									ImageIcon rg = new ImageIcon(chart);
									characters.setIcon(rg);
									break;
								}
							}
							catch(Exception l){l.printStackTrace();
							}
						}
					}

					);
			group.add(choice);
			radioButtons.add(choice);
		}
		fillIn.setVisible(false);
		group.add(fillIn);
		radioButtons.add(fillIn);
		getContentPane().add(radioButtons);
	}
	/**
	 * Returns the player Count by asking the user to enter the amount of characters
	 * @return
	 */
	public int playerCount(){
		int playerCount = 0;
		String count = JOptionPane.showInputDialog("Welcome to ZOMBIE CLUEDO! Please select the amount of players (3-6)");
		try{
			playerCount = Integer.parseInt(count);
		} catch(Exception e){
			JOptionPane.showMessageDialog(this, "Enter either '3' '4' '5' or '6'!");
			return playerCount();
		}
		if(playerCount < 1 || playerCount > 6){
			JOptionPane.showMessageDialog(this, "Enter either '3' '4' '5' or '6'!");
			return playerCount();
		}
		return playerCount;
	}
}
