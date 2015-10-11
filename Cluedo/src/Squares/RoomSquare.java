package Squares;

import java.util.ArrayList;

import Cluedo.Piece;
import Cluedo.Player;

/**
 * A RoomSquare is a Square on the board which has a unique roomType.
 * It can only be entered through via a door.
 * @param room
 * @author newtondavi2
 *
 */
public class RoomSquare implements Square {

	private boolean empty = true;
	private String roomType; //Full Room name
	private String room; //Room initials read from CleudoBoard.txt
	private ArrayList<Piece> container = new ArrayList<Piece>();

	/**
	 * A RoomSquare is a Square on the board which has a unique roomType.
	 * It can only be entered through via a door.
	 * @param room
	 */
	public RoomSquare(String room){
		this.room = room;
		print(room);

	}

	/**
	 * If the RoomSquare is empty,
	 * Prints the rooms initials to the console. It also sets the roomType of this RoomSquare to the full name of the room.
	 * If the RoomSquare is not empty, it will instead print the players character initials from container.get(0).getCharacter();
	 * @param room
	 */
	public void print(String room){

		if(this.empty){

			switch(this.room){
			case "BR":
				this.roomType = "Ballroom";
				break;
			case "DR":
				this.roomType = "Dining Room";
				break;
			case "B":
				this.roomType = "Billiard";
				break;
			case "C":
				this.roomType = "Conservatory";
				break;
			case "H":
				this.roomType = "Hall";
				break;
			case "K":
				this.roomType = "Kitchen";
				break;
			case "Li":
				this.roomType = "Library";
				break;
			case "Lo":
				this.roomType = "Lounge";
				break;
			case "S":
				this.roomType = "Study";
				break;
			}
		} else {

			switch(container.get(0).getPiece()){

			case "Colonel Mustard":
				break;
			case "Miss Scarlett":
				break;
			case "Mrs Peacock":
				break;
			case "Mrs White":
				break;
			case "Professor Plum":
				break;
			case "The Reverend Green":
				break;
			case "Dagger":
				break;
			case "Candlestick":
				break;
			case "Rope":
				break;
			case "Revolver":
				break;
			case "Spanner":
				break;
			case "LeadPipe":
				break;

			}
		}
	}

	public void print() {
		print(this.room);
	}

	public boolean containsPlayer(Piece p) {
		return container.contains(p);
	}

	public void containerAdd(Piece p){
		this.container.add(p);
		this.setRoom(p);
		this.setFull();
	}

	public void containerRemove(Piece p) {
		this.container.remove(p);
		this.setEmpty();
	}

	public String getType(){
		return this.roomType;
	}

	public void setEmpty() {
		this.empty = true;
	}

	public void setFull() {
		this.empty = false;
	}

	public boolean isEmpty(){
		return this.empty;
	}

	public void setRoom(Piece p) {
		p.setRoom(this.roomType);
	}

	public String getRoomType() {
		return getType();
	}


	public String whoStartsHere() {
		return null;
	}
}