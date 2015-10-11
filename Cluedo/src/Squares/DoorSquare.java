package Squares;

import java.util.ArrayList;

import Cluedo.Piece;
import Cluedo.Player;

/**
 * **
 * A DoorSquare represents a BlankSquare on the board which allows entry into a room.
 * Once a player lands on a DoorSquare they can then move to a RoomSquare.
 * The main task of a DoorSquare is to set a players onDoor boolean to True to allow for the above movement.
 * @author newtondavi2
 *
 */
public class DoorSquare implements Square{

	private boolean empty = true;
	private ArrayList<Piece> container = new ArrayList<Piece>();

	public DoorSquare(){
		print();
	}

	/**
	 * Prints the character D to the console. If a character is on the Door it will instead print out the characters initials.
	 */
	public void print(){

	}

	public void setEmpty() {
		this.empty = true;
	}

	public void setFull() {
		this.empty = false;
	}

	public void containerAdd(Piece p) {
		this.container.add(p);
		this.empty = false;
		p.setOnDoor();
	}

	public boolean containsPlayer(Piece p) {
		return container.contains(p);
	}

	public void containerRemove(Piece player) {
		this.container.remove(player);
		this.empty = true;
	}

	public boolean isEmpty() {
		return empty;
	}

	public String getRoomType() {
		return "Not a room";
	}

	public String whoStartsHere() {
		return null;
	}
}