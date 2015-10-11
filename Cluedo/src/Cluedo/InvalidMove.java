package Cluedo;

import Squares.BlankSquare;
import Squares.RoomSquare;

/**
 * InvalidMove holds all the methods to check if a player can move in a certain direction.
 * It is created by Setup and used throughout the game when a player makes a move.
 * @author newtondavi2
 *
 */
public class InvalidMove {

	/**
	 * Checks if the players movement up is valid
	 * @param player
	 * @return
	 */
	public boolean checkValidMoveUp(Player player) {
		//Checks for a invalid movement from a room square directly to a blank square.
		if(Setup.board.getBoard()[player.getRow()-1][player.getCol()] instanceof BlankSquare){
			if(player.getRoom() != null){
				return true;
			}
		}
		//Checks for a invalid movement from a blank square directly to a room square
		if(Setup.board.getBoard()[player.getRow()-1][player.getCol()] instanceof RoomSquare){
			if(!player.getDoorStatus()){
				return true;
			}
		}
		//Checks for a invalid move to an already preoccupied square
		if(!Setup.board.getBoard()[player.getRow()-1][player.getCol()].isEmpty()){
				return true;
		}
		return false;
	}

	
	/**
	 * Checks if the players movement down is valid
	 * @param player
	 * @return
	 */
	public boolean checkValidMoveDown(Player player) {
		//Checks for a invalid movement from a room square directly to a blank square.
		if(Setup.board.getBoard()[player.getRow()+1][player.getCol()] instanceof BlankSquare){
			if(player.getRoom() != null){
				return true;
			}
		}
		//Checks for a invalid movement from a blank square directly to a room square
		if(Setup.board.getBoard()[player.getRow()+1][player.getCol()] instanceof RoomSquare){
			if(!player.getDoorStatus()){
				return true;
			}
		}
		//Checks for a invalid move to an already preoccupied square
		if(!Setup.board.getBoard()[player.getRow()+1][player.getCol()].isEmpty()){
				return true;
		}
		return false;
	}

	/**
	 * Checks if the players movement to the right is valid
	 * @param player
	 * @return
	 */
	public boolean checkValidMoveRight(Player player) {
		//Checks for a invalid movement from a room square directly to a blank square.
		if(Setup.board.getBoard()[player.getRow()][player.getCol()+1] instanceof BlankSquare){
			if(player.getRoom() != null){
				return true;
			}
		}
		//Checks for a invalid movement from a blank square directly to a room square
		if(Setup.board.getBoard()[player.getRow()][player.getCol()+1] instanceof RoomSquare){
			if(!player.getDoorStatus()){
				return true;
			}

		}
		//Checks for a invalid move to an already preoccupied square
		if(!Setup.board.getBoard()[player.getRow()][player.getCol()+1].isEmpty()){
				return true;
		}
		return false;
	}

	/**
	 * Checks if the players movement left is valid
	 * @param player
	 * @return
	 */
	public boolean checkValidMoveLeft(Player player) {
		//Checks for a invalid movement from a room square directly to a blank square.
		if(Setup.board.getBoard()[player.getRow()][player.getCol()-1] instanceof BlankSquare){
			if(player.getRoom() != null){
				return true;
			}
		}
		//Checks for a invalid movement from a blank square directly to a room square
		if(Setup.board.getBoard()[player.getRow()][player.getCol()-1] instanceof RoomSquare){
			if(!player.getDoorStatus()){
				return true;
			}

		}
		//Checks for a invalid move to an already preoccupied square
		if(!Setup.board.getBoard()[player.getRow()][player.getCol()-1].isEmpty()){
				return true;
		}
		return false;
	}
}