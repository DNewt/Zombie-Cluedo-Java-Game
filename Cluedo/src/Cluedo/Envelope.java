package Cluedo;

/**
 * The Envelope class holds the solution in its fields.
 * If the Accusations fields are equal to that of the Envelopes the game is over.
 * @author newtondavi2
 *
 */
public class Envelope {

	private static Card weaponSolution;
	private static Card roomSolution;
	private static Card characterSolution;


	/**
	 * Return the weapon cards type
	 * @return
	 */
	public static String wepGetType(){
		return weaponSolution.getType();
	}

	/**
	 * Return the room cards type
	 * @return
	 */
	public static String roomGetType(){
		return roomSolution.getType();
	}

	/**
	 * return the character cards type
	 * @return
	 */
	public static String charGetType(){
		return characterSolution.getType();
	}

	/**
	 * Returns the Weapon cards name
	 * @return
	 */
	public static String getWeaponSolution(){
		return weaponSolution.getName();
	}

	/**
	 * Returns the Character cards name
	 * @return
	 */
	public static String getCharacterSolution(){
		return characterSolution.getName();
	}

	/**
	 * Return the room cards name
	 * @return
	 */
	public static String getRoomSolution(){
		return roomSolution.getName();
	}


	/**
	 * Set the weapon card solution to char
	 * @param Wep
	 */
	static void setWeaponSolution(Card Wep){
		weaponSolution = Wep;
	}

	/**
	 * Set the character card solution to char
	 * @param Char
	 */
	static void setCharacterSolution(Card Char){
		characterSolution = Char;
	}

	/**
	 * Set the room card solution to room
	 * @param Room
	 */
	static void setRoomSolution(Card Room){
		roomSolution = Room;
	}

}
