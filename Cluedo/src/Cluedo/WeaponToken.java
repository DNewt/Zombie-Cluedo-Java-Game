package Cluedo;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A weapon piece is placed on the board purely for aesthetic purposes.
 * @author newtondavi2
 *
 */
public class WeaponToken implements Piece {

	private String weaponName;
	private String room;
	private int row = 0;
	private int col = 0;

	public WeaponToken(String weapon){
		this.weaponName = weapon;
	}

	public String getPiece() {
		return this.weaponName;
	}

	public void setNotOnDoor(){}

	public void setRoom(String roomType) {
		this.room = roomType;
	}

	public void setOnDoor() {}

	/**
	 * Sets this weapons row to the int that is passed in
	 * @param row
	 */
	public void setRow(int row){
		this.row = row;
	}

	/**
	 * Sets this weapons col to the int that is passed in
	 * @param col
	 */
	public void setCol(int col){
		this.col = col;
	}

	public int getRow(){
		return this.row;
	}

	public int getCol(){
		return this.col;
	}

	/**
	 * Returns this weapons current room
	 * @return
	 */
	public String getRoom() {
		return room;
	}

	/**
	 * Returns the image of this weapon
	 */
	public Image getImage() {
		try{
		switch(weaponName){
		case "Dagger":
			return ImageIO.read(new File("src/card_images/Dagger_Weapon.png"));

		case "Candlestick":
			return ImageIO.read(new File("src/card_images/Candlestick_Weapon.png"));

		case "LeadPipe":
			return ImageIO.read(new File("src/card_images/Leadpipe_Weapon.png"));

		case "Rope":
			return ImageIO.read(new File("src/card_images/Axe_Weapon.png"));

		case "Revolver":
			return ImageIO.read(new File("src/card_images/Revolver_Weapon.png"));

		case "Spanner":
			return ImageIO.read(new File("src/card_images/Spanner_Weapon.png"));

		}

		}
		catch(Exception e){e.printStackTrace();

		}
		return null;

	}
}
