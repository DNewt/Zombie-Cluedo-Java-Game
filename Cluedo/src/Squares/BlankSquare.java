package Squares;

import java.util.ArrayList;

import javax.swing.JPanel;

import Cluedo.Piece;
import Cluedo.Player;

/**
 * A BlankSquare represents a blank space on the board which a character can move to.
 * You cannot move directly from a RoomSquare to a BlankSquare, you must go through a DoorSquare.
 * @author newtondavi2
 *
 */
public class BlankSquare implements Square {

	private boolean empty = true;
	private ArrayList<Piece> container = new ArrayList<Piece>();

	public BlankSquare(){
		print();
	}

	/**
	 * Prints a . to the console.
	 * If the BlankSquare already holds a player so its empty boolean is set to false, this method will
	 * instead print out the characters initials.
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
		setFull();
		setDoor();
	}

	public void containerRemove(Piece p) {
		container.remove(p);
		setEmpty();
	}

	public void setDoor(){
		this.container.get(0).setNotOnDoor();
	}

	public boolean containsPlayer(Piece p) {
		return container.contains(p);
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