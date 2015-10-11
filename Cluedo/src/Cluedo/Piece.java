package Cluedo;

import java.awt.Image;
import java.io.IOException;

import javax.swing.JLabel;

/**
 * Interface for pieces that are placed on the board.
 *
 * @author newtondavi2
 *
 */
public interface Piece {

	/**
	 * Returns the piece at this position
	 * @return
	 */
	public String getPiece();

	/**
	 * Sets the piece on the board to not being on a door.
	 * This means it keen not enter a room.
	 */
	public void setNotOnDoor();

	/**
	 * Sets the piece's room to the roomType passed in
	 * @param roomType
	 */
	public void setRoom(String roomType);

	/**
	 * Sets the piece to being on a door.
	 * This means it is able to enter a room
	 */
	public void setOnDoor();

	/**
	 * Returns the pieces row position
	 * @return
	 */
	public int getRow();

	/**
	 * Returns the pieces col position
	 * @return
	 */
	public int getCol();


	/**
	 * Return the image of this object
	 * @return
	 */
	public Image getImage();




}
