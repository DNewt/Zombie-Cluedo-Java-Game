package Cluedo;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import UI.BoardCanvas;
import UI.CardDisplay;

/**
 * The Turn class uses a scanner to accept user input as they move through a turn.
 * It sends information through Setup which delegates it to the classes who need to be affected by the user input.
 * @author newtondavi2
 *
 */
public class Turn {

	private Player player;
	private int steps = 0; // Amount of steps a player gets in a turn
	private InvalidMove invalidMoveChecker = new InvalidMove(); //Creates the invalid move object

	public static int mouseClickPosX = 0; //Holds the players click xPosition
	public static int mouseClickPosY = 0; //Holds the players click yPosition
	public static boolean moveButton = false;
	public static boolean suggestionButton = false;
	public static boolean accusationButton = false;
	public static boolean stairButton = false;
	public static boolean endButton = false;
	private boolean diceRolled = false; //Makes the player unable to roll the dice twice
	private boolean suggestionMade = false; //Makes the player unable to make two suggestions in a turn
	private boolean invalidMove = false; //Checks whether the player is able to make a move

	/**
	 * A turn takes a player and starts running through a turn for Cluedo
	 * @param player
	 */
	public Turn(Player player){
		this.player = player;
		beginTurn();
	}

	/**
	 * A turn begins with the ability to make an Accusation.
	 * If an accusation isnt made it begins the steps of a turn.
	 * The Dice is automatically rolled then the player can move into their new position.
	 */
	public void beginTurn(){

		int count = 0;

		while(count != 1){ //Never ending loop until the player either does an accusation or an end turn move
			try {
				Thread.sleep(20); //Continuously sleeps throughout the loop until an option is picked
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(Turn.moveButton == true){ //Initiates a stepping move to step the character around the board
				if(!diceRolled){
					rollDice();
					moveCharacter();
					diceRolled = true;
					Turn.moveButton = false;
				}
				Turn.moveButton = false;
			}
			if(Turn.suggestionButton == true){ //Initiates a suggestion move
				System.out.println("SUggestion made");
				if(!suggestionMade && player.checkIfEnteredRoom()){
					makeSuggestion();
					this.suggestionMade = true;
					Turn.suggestionButton = false;
				}
				Turn.suggestionButton = false;
			}
			if(Turn.endButton == true){ //Ends the players turn
				count = 1;
				Turn.endButton = false;
			}
			if(Turn.accusationButton == true){ //Initiates an accusation move
				makeAccusation();
				Turn.accusationButton = false;
				count = 1;
			}
			if(player.checkIfEnteredRoom() && Turn.stairButton){ //Checks to see if the player can use a stair case.
				if(player.checkIfEnteredRoom()){
					if(player.getRoom().equals("Conservatory")){
						System.out.println("Taking Stairs Lounge");
						takeStairs("Lounge");
					}
					else if(player.getRoom().equals("Lounge")){
						takeStairs("Conservatory");
					}
					else if(player.getRoom().equals("Kitchen")){
						takeStairs("Kitchen");
					}
					else if(player.getRoom().equals("Study")){
						takeStairs("Study");
					}
				}
				Setup.gameFrame.canvas.repaint();
				Turn.stairButton = false;
			}
		}
		//Resets the followign variables for the next instance of Turn
		Turn.stairButton = false;
		Turn.accusationButton = false;
		Turn.endButton = false;
		Turn.suggestionButton = false;

	}

	/**
	 * Creates an Accusation by getting user input.
	 * The user enters in there choice of Weapon, Room and Character.
	 */
	public void makeAccusation(){


		Setup.gameFrame.cardDisplay.AccusationMade();

		while(CardDisplay.accusationEnd == false){
			try {
				Thread.sleep(20); //Sleeps until the player has created their accusation
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}

		Accusation acc = new Accusation(player, CardDisplay.roomChoice, CardDisplay.weaponChoice, CardDisplay.characterChoice);


   }

	/**
	 * Rolls the game dice to get the number of steps a player can move
	 */
	public void rollDice(){
		steps = Setup.dice.rollDice();
	}

	/**
	 * Allows the player to choose which direction they are moving in for each step
	 * Takes user input via a click on the baord
	 * The direction stepped in is the direction clicked inrelevant to the character - directly up = up, directly below = down, west = left, east = right
	 */
	public void  moveCharacter(){

		boolean checkIfFreeMovePossible = player.checkIfEnteredRoom(); //Checks if player is moving around a room
		int i = 0;
		Turn.mouseClickPosX = 0;
		Turn.mouseClickPosY = 0;

	    while(i != steps){
	    	try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	Setup.gameFrame.dicePanel.updateSteps((steps)-i);
	    	if(Turn.mouseClickPosX != 0 || Turn.mouseClickPosY != 0){

	    		if(Turn.mouseClickPosX/25 > player.getCol()){

	    			invalidMove = invalidMoveChecker.checkValidMoveRight(player);
	    			updatePosition("col", player.getCol()+1);
	    			i++;
	    		}

	    		else if(Turn.mouseClickPosX/25 < player.getCol()){

	    			invalidMove = invalidMoveChecker.checkValidMoveLeft(player);
	    			updatePosition("col", player.getCol()-1);
	    			i++;
	    		}

	    		else if(Turn.mouseClickPosY/24 > player.getRow()){

	    			invalidMove = invalidMoveChecker.checkValidMoveDown(player);
	    			updatePosition("row", player.getRow()+1);
	    			i++;
	    		}

	    		else if(Turn.mouseClickPosY/24 < player.getRow()){

	    			invalidMove = invalidMoveChecker.checkValidMoveUp(player);
	    			updatePosition("row", player.getRow()-1);
	    			i++;
	    		}

	    		if(invalidMove){

	    			invalidMove = false;
	    			i--;
	    		}
	    		else if(player.checkIfEnteredRoom() && !checkIfFreeMovePossible){

	    			Setup.board.placePlayerInFreeSquare(player, player.getRoom());
	    			Setup.board.placePlayersTurn(player);
	    			Setup.board.drawBoard();
	    			i = steps;
	    		}
	    		else if(player.checkIfEnteredRoom() && checkIfFreeMovePossible){

	    			i--;
	    		}

	    		Turn.mouseClickPosX = 0;
	    		Turn.mouseClickPosY = 0;
	    	}
	    }
	    Setup.gameFrame.dicePanel.updateSteps(0);
	}

	/**
	 * Sets the player to the room in which the stairs lead to
	 * @param room
	 */
	public void takeStairs(String room){
		player.setRoom(room);
		Setup.board.placePlayerInFreeSquare(player, room);
		Setup.board.placePlayersTurn(player);
		Setup.board.drawBoard();
	}

	/**
	 * Updates the players position on the board if InvalidMove is set to false.
	 * Change is a String which is either row or col depending on what needs to be set.
	 * @param change
	 * @param newPosition
	 */
	private void updatePosition(String change, int newPosition){

		if(!invalidMove && change == "row"){
			player.setRow(newPosition);
			Setup.board.placePlayersTurn(player);
			Setup.board.drawBoard();
		}

		else if(!invalidMove && change == "col"){
			player.setCol(newPosition);
			Setup.board.placePlayersTurn(player);
			Setup.board.drawBoard();
		}
		BoardCanvas.repaintNow = true;


	}





	/**
	 * Makes a suggestion object for the player.
	 * A suggestion consists of the room the player is in and their choice of weapon and character.
	 * The user gives input for their choice.
	 */
	public void makeSuggestion(){

		Setup.gameFrame.cardDisplay.suggestionMade();
		CardDisplay.roomChoice = player.getRoom();

		while(CardDisplay.suggestionEnd == false){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for(int j = 0; j < Setup.players.size(); j++){
			System.out.println(Setup.players.get(j).getPiece() + "    =   "  + CardDisplay.characterChoice);
			if(Setup.players.get(j).getPiece().equals(CardDisplay.characterChoice)){
				int ans = Setup.gameFrame.getAnswer();
				if(ans == 1){
					Setup.players.get(j).setRoom(CardDisplay.roomChoice);
					Setup.board.placePlayerInFreeSquare(Setup.players.get(j), CardDisplay.roomChoice);
					Setup.board.placePlayersTurn(Setup.players.get(j));
					WeaponToken toMove = null;
					for(int wepIndex = 0; wepIndex < Setup.weapons.size(); wepIndex++){
						if(Setup.weapons.get(wepIndex).getPiece().equals(CardDisplay.weaponChoice)){
							Setup.weapons.get(wepIndex).setRoom(CardDisplay.roomChoice);
							toMove = Setup.weapons.get(wepIndex);
						}
					}
					Setup.board.placeWeaponsInFreeSquare(toMove, CardDisplay.roomChoice);
					Setup.board.getBoard()[toMove.getRow()][toMove.getCol()].containerAdd(toMove);
					Setup.board.drawBoard();
					Setup.gameFrame.canvas.repaint();
					break;
				}
			}
		}
		Suggestion sug = new Suggestion(player, CardDisplay.roomChoice, CardDisplay.weaponChoice, CardDisplay.characterChoice);
	}
}