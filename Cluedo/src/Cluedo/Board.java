package Cluedo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Squares.BlankSquare;
import Squares.DoorSquare;
import Squares.MiddleSquare;
import Squares.NullSquare;
import Squares.RoomSquare;
import Squares.Square;
import Squares.StartSquare;

/**
 * The board class sets up the board by reading in CluedoBoard.txt.
 * During gameplay it updates the board depending on the players positions.
 * @author newtondavi2
 */
public class Board {

	private Square[][] board = new Square[27][52]; // The board is a 2d Array of Square objects which each have their own type.
	private int rowIndex = 0; //Increments the rows of the board
	private int colIndex = 0; //Increments the cols of the board

	/**
	 * Board constructor calls the method which reads in the board from CluedoBoard.txt.
	 */
	public Board(){
		readBoard();
	}

	/**
	 * Returns the board
	 * @return
	 */
	public Square[][] getBoard(){
		return board;
	}

	/**
	 * Places the players on the board at the beginning for the game.
	 * This method searches through the board until it locates the SPECIFIC START SQUARE for the player
	 * Each Start Square is mapped to a SPECIFIC character
	 * @param numPlayers
	 */
	public void placePlayersStart(int numPlayers) {

		int count = numPlayers;
		while(count > 0){
			for(rowIndex = 0; rowIndex < 25; rowIndex++){
				for(colIndex = 0; colIndex < 24; colIndex++){
					if(board[rowIndex][colIndex] instanceof StartSquare){
						if(count == 0){
							break;
						}
						if(board[rowIndex][colIndex].whoStartsHere().equals(Setup.players.get(count-1).getPiece())){
							Setup.players.get(count-1).setCol(colIndex);
							Setup.players.get(count-1).setXStart();
							Setup.players.get(count-1).setRow(rowIndex);
							Setup.players.get(count-1).setYStart();
							board[rowIndex][colIndex].containerAdd(Setup.players.get(count-1));
							count--;
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * Places the player after the player has moved his/her character.
	 * It first removes the player from the board by iterating through the board
	 * and removing the player from the squares container.
	 * It then places the player at his/her new position on the board.
	 * @param player
	 */
	public void placePlayersTurn(Piece piece){

		for(rowIndex = 0; rowIndex < 25; rowIndex++){
			for(colIndex = 0; colIndex < 24; colIndex++){
				if(board[rowIndex][colIndex].containsPlayer(piece)){
					board[rowIndex][colIndex].containerRemove(piece);
					board[rowIndex][colIndex].setEmpty();
					break;
				}
			}
		}
		board[piece.getRow()][piece.getCol()].containerAdd(piece);
	}

	/**
	 * Updates the Board - Used for the Console version of Zombie Cluedo
	 * Iterates through each square and calls the squares print() method.
	 */
	public void drawBoard(){
		for(rowIndex = 0; rowIndex < 25; rowIndex++){
			System.out.println();
			for(int colIndex = 0; colIndex < 24; colIndex++){
				board[rowIndex][colIndex].print();
			}
		}
		System.out.println();
	}

	/**
	 * Iterates over each square in the specified room until it finds a free square to place a player in the room
	 * @param room
	 */
	public void placePlayerInFreeSquare(Player player, String roomType){
		for(rowIndex = 0; rowIndex < 25; rowIndex++){
			for(colIndex = 0; colIndex < 24; colIndex++){
				if(board[rowIndex][colIndex].isEmpty() && board[rowIndex][colIndex].getRoomType().equals(player.getRoom())){
					player.setRow(rowIndex);
					player.setCol(colIndex);
					player.setOnDoor();
				}
			}
		}
	}

	/**
	 * Iterates over each square in the specified room until it finds a free square to place a weapon in the room
	 * @param room
	 */
	public void placeWeaponsInFreeSquare(WeaponToken weapon, String roomType){
		for(rowIndex = 0; rowIndex < 25; rowIndex++){
			for(colIndex = 0; colIndex < 24; colIndex++){
				if(board[rowIndex][colIndex].isEmpty() && board[rowIndex][colIndex].getRoomType().equals(weapon.getRoom())){
					weapon.setRow(rowIndex);
					weapon.setCol(colIndex);
				}
			}
		}
	}

	/**
	 * Initializes the board with square objects of type dependent on the lineToCheck argument
	 * Takes a String argument lineToCheck and then iterates over each two letter code.
	 * It runs the letter through a switch case, the letter is a code for what instance of square to create on the board
	 * @param lineToCheck
	 */
	public void initiateBoard(String lineToCheck){

		int subStringStart = 0;
		int subStringEnd = 2;

		for(colIndex = 0; colIndex < 24; colIndex++){

			String letterToCheck = lineToCheck.substring(subStringStart,subStringEnd);
			switch(letterToCheck){
			case "N ":
				board[rowIndex][colIndex] = new NullSquare();
				break;
			case "SC": //Colonel Mustard starting square
				board[rowIndex][colIndex] = new StartSquare("Colonel Mustard");
				break;
			case "SP": //Mrs Peacock starting square
				board[rowIndex][colIndex] = new StartSquare("Mrs Peacock");
				break;
			case "SS": //Miss Scarlett starting square
				board[rowIndex][colIndex] = new StartSquare("Miss Scarlett");
				break;
			case "SR": //The Reverend Green starting square
				board[rowIndex][colIndex] = new StartSquare("The Reverend Green");
				break;
			case "SM": //Professor Plum starting square
				board[rowIndex][colIndex] = new StartSquare("Professor Plum");
				break;
			case "SW": //Mrs White starting square
				board[rowIndex][colIndex] = new StartSquare("Mrs White");
				break;
			case "BR":
				board[rowIndex][colIndex] = new RoomSquare("BR");
				break;
			case "C ":
				board[rowIndex][colIndex] = new RoomSquare("C");
				break;
			case "K ":
				board[rowIndex][colIndex] = new RoomSquare("K");
				break;
			case ". ":
				board[rowIndex][colIndex] = new BlankSquare();
				break;
			case "D ":
				board[rowIndex][colIndex] = new DoorSquare();
				break;
			case "DR":
				board[rowIndex][colIndex] = new RoomSquare("DR");
				break;
			case "B ":
				board[rowIndex][colIndex] = new RoomSquare("B");
				break;
			case "Li":
				board[rowIndex][colIndex] = new RoomSquare("Li");
				break;
			case "M ":
				board[rowIndex][colIndex] = new MiddleSquare();
				break;
			case "Lo":
				board[rowIndex][colIndex] = new RoomSquare("Lo");
				break;
			case "H ":
				board[rowIndex][colIndex] = new RoomSquare("H");
				break;
			case "S ":
				board[rowIndex][colIndex] = new RoomSquare("S");
				break;
			}
			subStringStart = subStringStart + 2;
			subStringEnd = subStringEnd + 2;
		}
		rowIndex++;
		System.out.println();
	}

	/**
	 * Reads the CluedoBoard.txt file line by line. It passes each line as an argument to initiateBoard(String lineToCheck)
	 */
	public void readBoard(){

		File file = new File("CluedoBoard.txt");

		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				initiateBoard(line);
			}
			scan.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
