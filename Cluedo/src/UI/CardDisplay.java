package UI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Cluedo.Card;
import Cluedo.Setup;

/**
 * The card display maneges the appearance of all hover over images and accusation/suggestion phases.
 * Due to this it also displays a ScrollPane on activation of an accusation or suggestion in which
 * the user can pick the cards they require to make their person suggestion/accusation.
 * @author newtondavi2
 *
 */
public class CardDisplay extends JPanel {

	public boolean displayRefute = false;
	public static String weaponChoice = null;
	public static String characterChoice = null;
	public static String roomChoice = null;
	public static boolean suggestionMade = false;
	public static boolean suggestionEnd = false;
	public static boolean accusationEnd = false;
	BufferedImage backgroundTexture;

	JPanel labelChoices = new JPanel();
	JScrollPane pane = new JScrollPane();

	public static boolean drawPicture = false; //keeps track of pictures drawing

	//displays the background text - The House where the zombies are actually moving around
	public CardDisplay(){
		try {
			backgroundTexture = ImageIO.read(new File("src/card_images/test.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * When a suggestion is made a fresh deck is created and split into 3
	 * phaes. Weapon, characters and rooms.
	 * This method calls the method and distributes the ArrayLists which hold each
	 * "phase" so that the player can choose the cards from each card type (Room, Weapon, Player)
	 * The Suggestion only uses the Weapon and Player arrays as the room is taken from the player position
	 */
	public void suggestionMade(){
		ArrayList<Card> wepCards = new ArrayList<Card>();
		ArrayList<Card> charCards = new ArrayList<Card>();
		ArrayList<Card> roomCards = new ArrayList<Card>();
		Card[] deck = new Card[21];

		deck[0] = new Card("Weapon", "Candlestick");
		deck[1] = new Card("Weapon", "Dagger");
		deck[2] = new Card("Weapon", "LeadPipe");
		deck[3] = new Card("Weapon", "Revolver");
		deck[4] = new Card("Weapon", "Rope");
		deck[5] = new Card("Weapon", "Spanner");
		deck[6] = new Card("Character", "Colonel Mustard");
		deck[7] = new Card("Character", "Miss Scarlett");
		deck[8] = new Card("Character", "Mrs Peacock");
		deck[9] = new Card("Character", "Mrs White");
		deck[10] = new Card("Character", "Professor Plum");
		deck[11] = new Card("Character", "The Reverend Green");
		deck[12] = new Card("Room", "Ballroom");
		deck[13] = new Card("Room", "Billiard");
		deck[14] = new Card("Room", "Conservatory");
		deck[15] = new Card("Room", "Dining");
		deck[16] = new Card("Room", "Hall");
		deck[17] = new Card("Room", "Kitchen");
		deck[18] = new Card("Room", "Library");
		deck[19] = new Card("Room", "Lounge");
		deck[20] = new Card("Room", "Study");

		for(int i = 0; i < 6; i++){
			wepCards.add(deck[i]);
		}
		for(int i = 6; i < 12; i++){
			charCards.add(deck[i]);
		}

		this.suggestionMade = true; //Sets the suggestion phase to being on
		pane.setVisible(true);
		this.revalidate();

		returnWeaponSuspect(wepCards, charCards);

	}

	/**
	 * When a accusation is made a fresh deck is created and split into 3
	 * phaes. Weapon, characters and rooms.
	 * This method calls the method and distributes the ArrayLists which hold each
	 * "phase" so that the player can choose the cards from each card type (Room, Weapon, Player)
	 */
	public void AccusationMade(){
		ArrayList<Card> wepCards = new ArrayList<Card>();
		ArrayList<Card> charCards = new ArrayList<Card>();
		ArrayList<Card> roomCards = new ArrayList<Card>();
		Card[] deck = new Card[21];

		deck[0] = new Card("Weapon", "Candlestick");
		deck[1] = new Card("Weapon", "Dagger");
		deck[2] = new Card("Weapon", "LeadPipe");
		deck[3] = new Card("Weapon", "Revolver");
		deck[4] = new Card("Weapon", "Rope");
		deck[5] = new Card("Weapon", "Spanner");
		deck[6] = new Card("Character", "Colonel Mustard");
		deck[7] = new Card("Character", "Miss Scarlett");
		deck[8] = new Card("Character", "Mrs Peacock");
		deck[9] = new Card("Character", "Mrs White");
		deck[10] = new Card("Character", "Professor Plum");
		deck[11] = new Card("Character", "The Reverend Green");
		deck[12] = new Card("Room", "Ballroom");
		deck[13] = new Card("Room", "Billiard");
		deck[14] = new Card("Room", "Conservatory");
		deck[15] = new Card("Room", "Dining");
		deck[16] = new Card("Room", "Hall");
		deck[17] = new Card("Room", "Kitchen");
		deck[18] = new Card("Room", "Library");
		deck[19] = new Card("Room", "Lounge");
		deck[20] = new Card("Room", "Study");

		for(int i = 0; i < 6; i++){
			wepCards.add(deck[i]);
		}
		for(int i = 6; i < 12; i++){
			charCards.add(deck[i]);
		}
		for(int i = 12; i < 21; i++){
			roomCards.add(deck[i]);
		}
		pane.setVisible(true);
		this.revalidate();

		returnWeaponSuspect(wepCards, charCards);
		returnRoomSuspect(roomCards); //calls both parts as an accusation allows the player to pick a room  also
	}

	/**
	 * Displays the JScrollPane which holds a grid of labels which have an attached mouse listener so the user can choose his card
	 * This method displays all the weapon choices
	 * @param wep
	 * @param chars
	 */
	public void returnWeaponSuspect(ArrayList<Card> wep, ArrayList<Card> chars){

		labelChoices.setLayout(new GridLayout(6,1));
		labelChoices.setPreferredSize(new Dimension(120, 1200));
		pane.setPreferredSize(new Dimension(400, 480));

		int sizeOfButtons = wep.size();

		for(int i = 0; i < wep.size(); i++) {
			JLabel button = new JLabel();
			button.setText(wep.get(i).getName());
			//buttonPanel.add(button, constraint);

			Image img = null;

			img = grabImage(wep.get(i).getName());

			Image dimg = img.getScaledInstance(380, 200,
					Image.SCALE_SMOOTH);
			button.setIcon(new ImageIcon(dimg));

			button.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					weaponChoice = button.getText();
				}
			});

			labelChoices.add(button);

		}

		pane.setViewportView(labelChoices);
		this.add(pane);
		pane.updateUI();
		this.revalidate();

		while(weaponChoice == null){ // sleeps until the user has picked a weapon card
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		labelChoices.removeAll();
		this.revalidate();
		returnPlayerSuspect(chars);
	}

	/**
	 * Displays the JScrollPane which holds a grid of labels which have an attached mouse listener so the user can choose his card
	 * This method displays all the character cards for hte user to select
	 * @param chars
	 */
	public void returnPlayerSuspect(ArrayList<Card> chars){
		labelChoices.setLayout(new GridLayout(6,1));
		labelChoices.setPreferredSize(new Dimension(120, 1200));
		pane.setPreferredSize(new Dimension(400, 480));
		this.revalidate();


		for(int i = 0; i < chars.size(); i++) {
			JLabel button = new JLabel();
			button.setText(chars.get(i).getName());
			System.out.println(chars.get(i).getName());

			Image img = null;

			img = grabImage(chars.get(i).getName());

			Image dimg = img.getScaledInstance(380, 200,
					Image.SCALE_SMOOTH);
			button.setIcon(new ImageIcon(dimg));

			button.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					characterChoice = button.getText();
				}
			});

			labelChoices.add(button);

		}

		pane.setViewportView(labelChoices);
		this.add(pane);
		pane.updateUI();
		this.revalidate();

		while(characterChoice == null){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

		if(suggestionMade){
			System.out.println("SUGGY");
			labelChoices.removeAll();
			pane.setVisible(false);
			this.revalidate();
			suggestionEnd = true;
		}

		labelChoices.removeAll();
		this.revalidate();

	}

	/**
	 * Only used for an Accusation
	 * Displays the JScrollPane which holds a grid of labels which have an attached mouse listener so the user can choose his card
	 * This method allows the user to choose the room card
	 * @param rooms
	 */
	public void returnRoomSuspect(ArrayList<Card> rooms){
		labelChoices.setLayout(new GridLayout(9,1));
		labelChoices.setPreferredSize(new Dimension(120, 1800));
		pane.setPreferredSize(new Dimension(400, 480));
		this.revalidate();
		int sizeOfButtons = rooms.size();

		for(int i = 0; i < rooms.size(); i++) {
			JLabel button = new JLabel();
			button.setText(rooms.get(i).getName());


			Image img = null;

			img = grabImage(rooms.get(i).getName());

			Image dimg = img.getScaledInstance(380, 200,
					Image.SCALE_SMOOTH);
			button.setIcon(new ImageIcon(dimg));

			button.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					roomChoice = button.getText();
				}
			});

			labelChoices.add(button);

		}

		pane.setViewportView(labelChoices);
		this.add(pane);
		pane.updateUI();
		this.revalidate();

		while(roomChoice == null){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

		labelChoices.removeAll();
		pane.setVisible(false);
		this.revalidate();
		CardDisplay.accusationEnd = true;

	}

	/**
	 * paints the background image of the card display
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundTexture, 0,0, 500, 500, null);
	}

	/**
	 * Sets the size of the CardDisplay to be x:500 y:500
	 */
	public Dimension getPreferredSize() {
		Dimension d = new Dimension(500, 500);
		return d;
	}

	/**
	 * Returns the image of the card to be used in the suggestion/accusation
	 * This is different than the card image purely for design purposes
	 * @param pic
	 * @return
	 */
	public Image grabImage(String pic){
		try{
			switch(pic){
			case "Colonel Mustard":
				return ImageIO.read(new File("src/card_images/Col_Mustard_Sug.png"));

			case "Miss Scarlett":
				return ImageIO.read(new File("src/card_images/Miss_Scarlet_Sug.png"));

			case "Mrs Peacock":
				return ImageIO.read(new File("src/card_images/Mrs_Peacock_Sug.png"));

			case "Mrs White":
				return ImageIO.read(new File("src/card_images/Mrs_White_Sug.png"));

			case "Professor Plum":
				return ImageIO.read(new File("src/card_images/Prof_Plum_Sug.png"));

			case "The Reverend Green":
				return ImageIO.read(new File("src/card_images/Mr_Green_Sug.png"));

			case "Dagger":
				return ImageIO.read(new File("src/card_images/Dagger_Sug.png"));

			case "Candlestick":
				return ImageIO.read(new File("src/card_images/Candlestick_Sug.png"));

			case "LeadPipe":
				return ImageIO.read(new File("src/card_images/Leadpipe_Sug.png"));

			case "Rope":
				return ImageIO.read(new File("src/card_images/Axe_Sug.png"));

			case "Revolver":
				return ImageIO.read(new File("src/card_images/Revolver_Sug.png"));

			case "Spanner":
				return ImageIO.read(new File("src/card_images/Spanner_Sug.png"));

			case "Ballroom":
				return ImageIO.read(new File("src/card_images/Ballroom_Sug.png"));

			case "Billiard":
				return ImageIO.read(new File("src/card_images/Billiard_Room_Sug.png"));

			case "Conservatory":
				return ImageIO.read(new File("src/card_images/Conservatory_Sug.png"));

			case "Dining":
				return ImageIO.read(new File("src/card_images/Dining_Room_Sug.png"));

			case "Hall":
				return ImageIO.read(new File("src/card_images/Hall_Sug.png"));

			case "Kitchen":
				return ImageIO.read(new File("src/card_images/Kitchen_Sug.png"));

			case "Library":
				return ImageIO.read(new File("src/card_images/Library_Sug.png"));

			case "Lounge":
				return ImageIO.read(new File("src/card_images/Lounge_Sug.png"));

			case "Study":
				return ImageIO.read(new File("src/card_images/Study_Sug.png"));

			}

		}
		catch(Exception e){e.printStackTrace();

		}
		return null;

	}



	/**
	 * Sets the side image to the image that is passed in
	 * @param image
	 */
	public void setSideImage(Image image) {
		this.getGraphics().drawImage(image, 100,100,300,380, null);
	}

}