package Cluedo;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 * A player is a user in the game. The player class holds information which it shares
 * with the board via Setup.
 * @author newtondavi2
 *
 */
public class Player implements Piece {

	private Hand hand = new Hand();
	private boolean onDoor = false;
	private boolean enteredRoom = false;
	private boolean eliminated = false;
	private String room = null;
	private int playerNumber;
	private int row = 0;
	private int col = 0;
	private int lastX;
	private int lastY;
	private String character;
	private String name;



	/**
	 * Each player has a unique playerNumber and character.
	 * @param playerNumber
	 * @param character
	 */
	public Player(int playerNumber, String character){

		this.playerNumber = playerNumber;
		this.character = character;

	}

	/**
	 * Returns the value of boolean onDoor.
	 * onDoor is true when the player is on a door.
	 * onDoor is false when the player is not on a door.
	 * @return
	 */
	public boolean getDoorStatus(){
		return this.onDoor;
	}

	/**
	 * Sets the players onDoor boolean to true.
	 * Sets the users enteredRoom boolean to false as it is possible they have left a room through the door.
	 * Sets the players current room to null.
	 */
	public void setOnDoor(){
		this.onDoor = true;
		this.enteredRoom = false;
		this.room = null;
	}

	/**
	 * Set the players onDoor boolean to false.
	 */
	public void setNotOnDoor(){
		this.onDoor = false;
	}

	/**
	 * Returns this players hand
	 * @return
	 */
	public Hand getHand(){
		return this.hand;
	}

	/**
	 * Returns this players number
	 * @return
	 */
	public int getNumber(){
		return this.playerNumber;
	}

	/**
	 * Checks to see if the players in the room.
	 * If the player is in a room returns true.
	 * If the player is not in a room returns false.
	 * @return
	 */
	public boolean checkIfEnteredRoom(){
		return this.enteredRoom;
	}

	/**
	 * Returns this players character.
	 * @return
	 */
	public String getPiece() {
		return this.character;
	}


	public void setXStart(){
		this.lastX = getCol();
	}
	public void setYStart(){
		this.lastY = getRow();
	}
	/**
	 * Sets this players row position to r.
	 * @param r
	 */
	public void setRow(int r){
		this.lastY = this.row;
		this.row = r;

	}

	/**
	 * Sets this players col position to c.
	 * @param c
	 */
	public void setCol(int c){;
		this.lastX = this.col;
		this.col = c;
	}

	public int getLastX(){
		return this.lastX;
	}

	public int getLastY(){
		return this.lastY;
	}


	/**
	 * Returns this plasyers current row.
	 * @return
	 */
	public int getRow(){
		return this.row;
	}

	/**
	 * Returns this players current col.
	 * @return
	 */
	public int getCol(){
		return this.col;
	}

	/**
	 * Sets this players room to roomType.
	 * Sets enteredRoom to true as player is now in a room.
	 * @param roomType
	 */
	public void setRoom(String roomType) {
		this.room = roomType;
		this.enteredRoom = true;
	}

	/**
	 * Returns this players current room.
	 * @return
	 */
	public String getRoom(){
		return this.room;
	}

	/**
	 * Set this player is eliminated so they can no longer play out a turn
	 */
	public void setEliminated() {
		eliminated = true;
	}

	/**
	 * Get the eliminated status of this player
	 * @return
	 */
	public boolean getEliminated() {
		return eliminated;
	}


	/**
	 * Returns the image of this character
	 */
	public Image getImage() {
		try{
		switch(character){
		case "Colonel Mustard":
			return ImageIO.read(new File("src/character_images/Colonel_Mustard_Character.png"));

		case "Miss Scarlett":
			return ImageIO.read(new File("src/character_images/Miss_Scarlett_Character.png"));

		case "Mrs Peacock":
			return ImageIO.read(new File("src/character_images/Mrs_Peacock_Character.png"));

		case "Mrs White":
			return ImageIO.read(new File("src/character_images/Mrs_White_Character.png"));

		case "Professor Plum":
			return ImageIO.read(new File("src/character_images/Professor_Plum_Character.png"));

		case "The Reverend Green":
			return ImageIO.read(new File("src/character_images/The_Reverend_Green_Character.png"));

		}

		}
		catch(Exception e){e.printStackTrace();

		}
		return null;




	}

	/**
	 * Returns the coloured label which corrasponds with the character.
	 * This label is displayed to show the user whos turn it is.
	 * @return
	 */
	public BufferedImage getLabel() {
		try{
		switch(character){
		case "Colonel Mustard":
			return ImageIO.read(new File("src/card_images/Yellow_Label.png"));

		case "Miss Scarlett":
			return ImageIO.read(new File("src/card_images/Red_Label.png"));

		case "Mrs Peacock":
			return ImageIO.read(new File("src/card_images/Blue_Label.png"));

		case "Mrs White":
			return ImageIO.read(new File("src/card_images/White_Label.png"));

		case "Professor Plum":
			return ImageIO.read(new File("src/card_images/Purple_Label.png"));

		case "The Reverend Green":
			return ImageIO.read(new File("src/card_images/Green_Label.png"));

		}

		}
		catch(Exception e){e.printStackTrace();

		}
		return null;

	}

	/**
	 * Returns the Player Name which was entered at the beginning of the game.
	 * @return
	 */
	public String getPName(){
		return this.name;
	}

	/**
	 * Sets the players player name to name. This is set at the beginning of the game.
	 * @param name
	 */
	public void setPName(String name){
		this.name = name;
	}

}
