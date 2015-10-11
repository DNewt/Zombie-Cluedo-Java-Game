package Squares;

import Cluedo.Piece;
import Cluedo.Player;

/**
 *
 * A MiddleSquare repsents the centre squares in the board.
 * These Squares are just like NullSquares, they are never empty.
 * @author newtondavi2
 *
 */
public class MiddleSquare implements Square {

	private boolean empty = false;

	public MiddleSquare(){

	}

	/**
	 * Prints M to the console
	 */
	public void print() {

	}

	public void setEmpty(){}

	public void setFull(){}

	public void containerAdd(Piece p){}

	public void containerRemove(Piece playwe){}

	public void setRoom(Piece p) {}

	public boolean isEmpty() {
		return false;
	}

	public boolean containsPlayer(Piece p){
		return false;
	}

	public String getRoomType() {
		return "Not a room";
	}

	public String whoStartsHere() {
		return null;
	}


}