package Cluedo;

import java.util.ArrayList;

import UI.CardDisplay;

/**
 * A suggestion is created by a player only when they enter a room.
 * The Suggestion allows the player to choose a Room, Weapon and Piece and see if any other players
 * have one of these cards in their hand. The Suggestion does not reveal what card gets refuted but
 * only that a player does have one of the 3 cards suggested.
 * @author newtondavi2
 *
 */
public class Suggestion {

	private String room;
	private String weapon;
	private String piece;
	private Player player;
	private boolean refuted = false;


	/**
	 * A player creates a Suggestion upon entering a room.
	 * The room is the players.room whilst the weapon is the name of a weapon and a piece the name of a piece.
	 * The Suggestion automatically checks if any player, going clockwise from the player, can refute the suggestion by
	 * comparing the suggestion with each players hand.
	 * @param player
	 * @param room
	 * @param weapon
	 * @param piece
	 */
	public Suggestion(Player player, String room, String weapon, String piece){
		this.room = room;
		this.weapon = weapon;
		this.piece = piece;
		this.player = player;
		checkIfCanRefute();
	}

	/**
	 * Checks whether any of the other players can refute the suggestion
	 * This is done in a clockwise fashion, each player has their hands compared with the suggestions and a refute is automatic.
	 */
	public void checkIfCanRefute(){
		System.out.println("Can anyone Refute this suggestion");
		for(int i = 0; i <= Setup.players.size()-1; i++){

			ArrayList<Card> handToCheck = Setup.players.get(i).getHand().returnHand();

			if(Setup.players.get(i).getNumber() != player.getNumber()){
				for(int j = 0; j < handToCheck.size(); j++){
					if(handToCheck.get(j).getName().equals(this.room)
							|| handToCheck.get(j).getName().equals(this.weapon)
							|| handToCheck.get(j).getName().equals(this.piece)) {
						if(handToCheck.get(j).getName().equals(this.room)) {
							String playern = Setup.players.get(i).getPName(); //Get the player name of the player who can refute
							Setup.gameFrame.getRefute(playern, handToCheck.get(j).getName()); //Also passes in the card that the player is refuting with
							setRefuted(true);
							i = 1000;
							resetStaticVariables();
							break;
						}
						else if(handToCheck.get(j).getName().equals(this.weapon)){
							String playern = Setup.players.get(i).getPName();
							Setup.gameFrame.getRefute(playern, handToCheck.get(j).getName());
							setRefuted(true);
							i = 1000;
							resetStaticVariables();
							break;

						}else if( handToCheck.get(j).getName().equals(this.piece)){
							String playern = Setup.players.get(i).getPName();
							Setup.gameFrame.getRefute(playern, handToCheck.get(j).getName());
							setRefuted(true);
							i = 1000;
							resetStaticVariables();
							break;

						}
					}
				}
			}
		}

		if(!isRefuted()){
			System.out.println("Suggestion was not refuted");
			CardDisplay.characterChoice = null;
			CardDisplay.weaponChoice = null;
			CardDisplay.roomChoice = null;
		}
	}

	public void resetStaticVariables(){
		CardDisplay.characterChoice = null;
		CardDisplay.weaponChoice = null;
		CardDisplay.roomChoice = null;
		CardDisplay.suggestionMade = false;
		CardDisplay.suggestionEnd = false;
		CardDisplay.accusationEnd = false;
	}



	public boolean isRefuted() {
		return refuted;
	}

	public void setRefuted(boolean refuted) {
		this.refuted = refuted;
	}
}



