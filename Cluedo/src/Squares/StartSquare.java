package Squares;

import java.util.ArrayList;

import Cluedo.Piece;
import Cluedo.Player;

/**
 * A StartSquare is a position on the board in which the players can begin the game from.
 * It is represented by the initials "St".
 * @author newtondavi2
 *
 */
public class StartSquare implements Square {

	private boolean empty = true;
	private ArrayList<Piece> container = new ArrayList<Piece>();
	private String whoStartsHere;

	public StartSquare(String whoStartsHere){
		this.whoStartsHere = whoStartsHere;
	}

	/**
	 * Prints the initials "St" to the board if this StartSquare is empty.
	 * If it holds a player, it will instead print the players character initials from container.get(0).getCharacter()
	 */
	public void print(){

	}
	public String whoStartsHere(){
		return this.whoStartsHere;
	}

	public void setFull() {
		this.empty = false;
	}

	public void setEmpty(){
		this.empty = true;
	}

	public boolean isEmpty(){
		return empty;
	}

	public void containerAdd(Piece p) {
		this.container.add(p);
		this.setFull();
	}

	public void containerRemove(Piece p) {
		container.remove(p);
		this.setEmpty();
	}

	public boolean containsPlayer(Piece p) {
		return container.contains(p);
	}

	public String getRoomType() {
		return "Not a room";
	}
}