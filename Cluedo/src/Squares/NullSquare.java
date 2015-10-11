package Squares;
import Cluedo.Piece;
import Cluedo.Player;

/**
 * A NullSquare is an Square which is constantly not empty. It represents the Walls in Cluedo.
 * @author newtondavi2
 *
 */
public class NullSquare implements Square{

	private boolean empty = false;

	public NullSquare(){

	}

	/**
	 * Prints the walk Ascii character to the console
	 */
	public void print() {

	}

	public void setEmpty(){}

	public void setFull(){}

	public void containerAdd(Piece p){}

	public void containerRemove(Piece p){}

	public void setRoom(Player p) {}

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