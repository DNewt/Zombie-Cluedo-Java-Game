package Squares;
import Cluedo.Piece;
import Cluedo.Player;

/**
 * The Square interface gives method headers for all methods used across the different square implementations
 * @author newtondavi2
 *
 */
public interface Square {

	/**
	 * Sets the Squares empty boolean to be true. This means the Squares ArrayList container can hold a player.
	 */
	public void setEmpty();

	/**
	 * Sets the Squares empty boolean to be false. This means the Squares ArrayList container cannot hold a player.
	 * NullSquares and MiddleSquares will always be set to false.
	 */
	public void setFull();

	/**
	 * Adds player to the Squares ArrayList container.
	 * This also means the Squares empty boolean will need to be set to False via the setFull() method.
	 * @param p
	 */
	public void containerAdd(Piece p);

	/**
	 * Removes player from the ArrayList container.
	 * This also means the Squares empty boolean will need to be set to True via the setEmpty() method.
	 * @param player
	 */
	public void containerRemove(Piece player);

	/**
	 * Checks to see if this Square contains the Player 'p'.
	 * Returns True if it does, False otherwise.
	 *
	 * @param p
	 * @return
	 */
	public boolean containsPlayer(Piece p);

	/**
	 * Returns the value of the empty boolean.
	 * Used to check whether or not this Square already contains a Player, or just returns false if this Square is Null or Middle.
	 * @return
	 */
	public boolean isEmpty();

	/**
	 * If this square has a Room type, return it.
	 * @return
	 */
	public String getRoomType();


	/**
	 * If this square is a start square it has a specific player set to it. It will return this player.
	 * @return
	 */
	public String whoStartsHere();

	/**
	 * Prints the character - Used mostly for Console version of Zombie Cluedo
	 */
	public void print();

}