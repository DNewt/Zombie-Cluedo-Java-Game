package Cluedo;

import UI.CardDisplay;

/**
 * An Accusation is an effort by a player to win the game.
 * The Accusation is created by a player and compares itself to the Envelope which
 * is holds the solution for the game. If a player is right in all three guesses they win the game,
 * if not they lose and are eliminated from the game.
 * @author newtondavi2
 *
 */
public class Accusation {

	private String weapon;
	private String room;
	private String piece;
	private Player player;
	private boolean testAccusation = false;

	/**
	 * An Accusation compares a players choice of room, weapon and character piece against the Envelope.
	 * If all three match, then the player wins the game and this is displayed on the console.
	 * If they do not all match, the player is eliminated and this is displayed on the console.
	 * @param player
	 * @param room
	 * @param weapon
	 * @param piece
	 */
	public Accusation(Player player, String room, String weapon, String piece){
		this.room = room;
		this.weapon = weapon;
		this.piece = piece;
		this.player = player;
		this.checkIfWin();
	}

	/**
	 * Compares the Accusation to the Envelope.
	 * If the room, weapon and piece matches the Envelopes fields then the player wins the game.
	 * If they do not all match then the player is eliminated
	 */
	public void checkIfWin(){

		if(this.weapon.equals(Envelope.getWeaponSolution()) &&
				this.piece.equals(Envelope.getCharacterSolution()) &&
				this.room.equals(Envelope.getRoomSolution())){

			Setup.gameOver = true;
			Setup.gameFrame.getWinAcc(this.player.getPName());

			this.setTestAccusation();
			resetStaticVariables();

		} else {
			Setup.board.getBoard()[player.getRow()][player.getCol()].setEmpty();
			Setup.gameFrame.getEliminated(this.player.getPName());
			this.player.setEliminated();
			Setup.board.drawBoard();
			Setup.elimCount++;
			this.setTestAccusationFalse();
			resetStaticVariables();
		}
	}

	/**
	 * Resets all the suggestion and accusation static variables to null
	 */
	public void resetStaticVariables(){
		CardDisplay.characterChoice = null;
		CardDisplay.weaponChoice = null;
		CardDisplay.roomChoice = null;
		CardDisplay.suggestionMade = false;
		CardDisplay.suggestionEnd = false;
		CardDisplay.accusationEnd = false;
	}

	/**
	 * Used specifically for the purpose of testing whether or not the accusation has been set to true.
	 * This means the Accusation has won the game.
	 */
	public void setTestAccusation(){
		this.testAccusation = true;
	}

	/**
	 * Used specifically for the purpose of testing whether or not the accusation has been set to false.
	 * This means the Accusation has been eliminated from the game.
	 */
	public void setTestAccusationFalse(){
		this.testAccusation = false;
	}

	/**
	 * Returns the value in testAccusation.
	 * @return
	 */
	public boolean getTestAccusation(){
		return this.testAccusation;
	}
}