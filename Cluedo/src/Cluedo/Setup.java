package Cluedo;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import UI.BoardFrame;
import UI.CharacterSelectionFrame;


/**
 * The set up class sets up everything that is required for the game.
 * It acts as a middle man as it communicates with all classes and allows them
 * to receive data and information from each other.
 * @author newtondavi2
 *
 */
public class Setup {

	public static ArrayList<Player> players = new ArrayList<Player>(); //Holds the players playing the current Cluedo game.
	public static ArrayList<WeaponToken> weapons = new ArrayList<WeaponToken>(); //Holds the WeaponTokens in the current Cluedo game.
	public static ArrayList<String> roomNames = new ArrayList<String>(); //Holds all room names as strings
	public static Board board = new Board(); //Creates the board for the current game of Cluedo
	public static Deck deck = new Deck(); //Creates the deck for the current game of Cluedo
	public static Dice dice = new Dice();  //Creates the dice for the current game of Cluedo
	public static Scanner scan = new Scanner(System.in);; //Sets up the input stream so the players can pass in commands.
	public static boolean gameOver = false; //Checks to see if a correct accusation has been made
	public static int elimCount = 0; // Counts the amount of players eliminated
	public static BoardFrame gameFrame; // Holds the board and all the panels required to play the game.
	public static int wait = 0; // Used to 'stop' the thread while the player makes decisions

	/**
	 * The setup constructor calls the method setUpGame .
	 */
	public Setup(boolean test){
		if(!test){
			setUpGame();
		}
	}

	/**
	 * Sets up a game of Cluedo then begins it
	 * Sets up the players by calling the setupPlayers() method.
	 * Places the players on the board by calling the boards placePlayersStart() method
	 * Creates the hands for each individual player by calling the createHand method
	 * Begins the Turn loop by calling beingTurnLoop()
	 */
	public void setUpGame(){

		setupPlayers();
		createWeaponTokens();
		board.placePlayersStart(players.size());
		board.drawBoard();
		deck.createHand();
		gameFrame = new BoardFrame();
		beginTurnLoop();

	}

	/**
	 * Creates the WeaponTokens for each weapon and places them on the board in random starting positions
	 */
	private void createWeaponTokens() {

		// Adds all the rooms to roomNames as Strings
		roomNames.add("Ballroom");
		roomNames.add("Conservatory");
		roomNames.add("Kitchen");
		roomNames.add("Dining Room");
		roomNames.add("Lounge");
		roomNames.add("Hall");
		roomNames.add("Study");
		roomNames.add("Library");
		roomNames.add("Billiard");

		WeaponToken dagger = new WeaponToken("Dagger");
		weapons.add(dagger);
		WeaponToken candleStick = new WeaponToken("Candlestick");
		weapons.add(candleStick);
		WeaponToken leadPipe = new WeaponToken("LeadPipe");
		weapons.add(leadPipe);
		WeaponToken rope = new WeaponToken("Rope");
		weapons.add(rope);
		WeaponToken revolver = new WeaponToken("Revolver");
		weapons.add(revolver);
		WeaponToken spanner = new WeaponToken("Spanner");
		weapons.add(spanner);

		Random rand = new Random(); // Random number generated to get random room to put weapon in

		for(int i = 0; i < weapons.size(); i++){
			int weaponPlacement = rand.nextInt(roomNames.size()-1);
			weapons.get(i).setRoom(roomNames.get(weaponPlacement));
			board.placeWeaponsInFreeSquare(weapons.get(i), roomNames.get(weaponPlacement));
			board.getBoard()[weapons.get(i).getRow()][weapons.get(i).getCol()].containerAdd(weapons.get(i));
			roomNames.remove(weaponPlacement);
		}

	}

	/**
	 * Begins the recursive creation of a turn until the gameOver boolean has been set to true
	 */
	public void beginTurnLoop(){

		while(!gameOver){
			for(int playerIndex = 0; playerIndex < players.size(); playerIndex++){
				if(players.size() == elimCount){
					gameOver = true;
				}
				if(!gameOver){
					if(!players.get(playerIndex).getEliminated()){
						gameFrame.setHandPanel(players.get(playerIndex)); //Changes the handPanel to display the current players hand
						gameFrame.setLabelPanel(players.get(playerIndex)); //Changes the userPanel to display the current players label
						Turn turn = new Turn(players.get(playerIndex)); //creates a new turn for player at playerIndex in ArrayList players.
					}
				}
			}
		}

		if(gameOver){
			gameFrame.gameOverPopUp(); //Displays the message that the game is over.
		}
	}

	/**
	 * Presents commands to the user to gather information on how many players will be playing this game.
	 * It then takes user input so each individual player can select their character by using the correct command.
	 */
	public void setupPlayers(){

		CharacterSelectionFrame select = new CharacterSelectionFrame();

		while(wait != 1){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}