package Cluedo;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Every CharacterPiece, Weapon and Room has a Card related to it.
 * @author newtondavi2
 *
 */
public class Card {

	private String type;
	private String name;

	/**
	 * A Card has a person type and name.
	 * The type will be either a string of "Weapon", "Room" or "CharacterPiece".
	 * The name will be the direct name of the card (eg "CandleStick")
	 * @param type
	 * @param name
	 */
	public Card(String type, String name){
		this.type = type;
		this.name = name;
	}

	/**
	 * Returns the name of the Card
	 * @return
	 */
	public String getName(){
		return name;
	}

	/**
	 * Returns the card type
	 * @return
	 */
	public String getType(){
		return type;
	}

	/**
	 * Return the image of this card
	 * @return
	 */
	public Image getImage() {
		try{
			switch(name){
			case "Candlestick":
				return ImageIO.read(new File("src/card_images/Candlestick_Card.png"));

			case "Dagger":
				return ImageIO.read(new File("src/card_images/Knife_Card.png"));

			case "LeadPipe":
				return ImageIO.read(new File("src/card_images/Leadpipe_Card.png"));

			case "Revolver":
				return ImageIO.read(new File("src/card_images/Revolver_Card.png"));

			case "Rope":
				return ImageIO.read(new File("src/card_images/Rope_Card.png"));

			case "Spanner":
				return ImageIO.read(new File("src/card_images/Spanner_Card.png"));

			case "Colonel Mustard":
				return ImageIO.read(new File("src/card_images/Colonel_Mustard_Card.png"));

			case "Miss Scarlett":
				return ImageIO.read(new File("src/card_images/Miss_Scarlet_Card.png"));

			case "Mrs Peacock":
				return ImageIO.read(new File("src/card_images/Peacock_Character_Card.png"));

			case "Mrs White":
				return ImageIO.read(new File("src/card_images/Mrs_White_Card.png"));

			case "Professor Plum":
				return ImageIO.read(new File("src/card_images/Plum_Character_Card.png"));

			case "The Reverend Green":
				return ImageIO.read(new File("src/card_images/Green_Character_Card.png"));

			case "Ballroom":
				return ImageIO.read(new File("src/card_images/Ballroom_Card.png"));

			case "Billiard":
				return ImageIO.read(new File("src/card_images/Billiard_Room_Card.png"));

			case "Conservatory":
				return ImageIO.read(new File("src/card_images/Conservatory_Card.png"));

			case "Dining":
				return ImageIO.read(new File("src/card_images/Dining_Room_Card.png"));

			case "Hall":
				return ImageIO.read(new File("src/card_images/Hall_Card.png"));

			case "Kitchen":
				return ImageIO.read(new File("src/card_images/Kitchen_Card.png"));

			case "Library":
				return ImageIO.read(new File("src/card_images/Library_Card.png"));

			case "Lounge":
				return ImageIO.read(new File("src/card_images/Lounge_Card.png"));

			case "Study":
				return ImageIO.read(new File("src/card_images/Study_Card.png"));

			}

		} catch(Exception e){	e.printStackTrace(); }

		return null;

	}
}
