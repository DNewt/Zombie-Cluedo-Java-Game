package tests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import Squares.BlankSquare;
import Cluedo.Accusation;
import Cluedo.Board;
import Cluedo.Card;
import Cluedo.Deck;
import Cluedo.Envelope;
import Cluedo.InvalidMove;
import Cluedo.Piece;
import Cluedo.Player;
import Cluedo.Setup;
import Cluedo.Suggestion;
import Cluedo.Turn;

public class Tests {

	// ===========================================================================
	// Test card allocation
	// ===========================================================================

	/**
	 * Checks to see if the Envelope contains one room,character and weapon card on creation.
	 */
	@Test public void checkEnvelope() {


		Setup s = new Setup(true);

		int counter = 0;
		while(counter < 500){
			if(Envelope.wepGetType() != "Weapon"){
				fail("Weapon card should be of type Weapon");
			}
			if(Envelope.roomGetType() != "Room"){
				fail("Weapon card should be of type Weapon");
			}
			if(Envelope.charGetType() != "Character"){
				fail("Weapon card should be of type Weapon");
			}
			counter++;
		}
	}

	/**
	 * Checks to see that hand sizes are correct for 3 players
	 */
	@Test public void checkHandPlayerSize3() {

		Setup s = new Setup(true);

		boolean correct = false;

		Player A = new Player(0, "Colonel Mustard");
		Player B = new Player(1, "Miss Scarlett");
		Player C = new Player(2, "Mrs Peacock");
		Setup.players.add(A);
		Setup.players.add(B);
		Setup.players.add(C);
		Setup.deck.createHand();

		if(Setup.players.get(0).getHand().size() == 6){
			if(Setup.players.get(1).getHand().size() == 6){
				if(Setup.players.get(2).getHand().size() == 6){
					correct = true;
				}
			}
		}
		if(!correct){
			fail("HandSize not correct for 3 players");
		}
	}

	/**
	 * Checks to see that hand sizes are correct for 4 players
	 */
	@Test public void checkHandPlayerSize4() {

		Setup s = new Setup(true);

		boolean correct = false;

		Player A = new Player(0, "Colonel Mustard");
		Player B = new Player(1, "Miss Scarlett");
		Player C = new Player(2, "Mrs Peacock");
		Player D = new Player(3, "Mrs White");
		Setup.players.add(A);
		Setup.players.add(B);
		Setup.players.add(C);
		Setup.players.add(D);
		Setup.deck.createHand();

		if((Setup.players.get(0).getHand().size() == 5) &&
				(Setup.players.get(1).getHand().size() == 5) &&
				(Setup.players.get(2).getHand().size() == 4) &&
				(Setup.players.get(3).getHand().size() == 4)){
			correct = true;
		}

		if(correct){
			fail("HandSize not correct for 4 players");
		}
	}

	/**
	 * Checks to see that hand sizes are correct for four five players
	 */
	@Test public void checkHandPlayerSize5() {
		Setup s = new Setup(true);

		boolean correct = false;

		Player A = new Player(0, "Colonel Mustard");
		Player B = new Player(1, "Miss Scarlett");
		Player C = new Player(2, "Mrs Peacock");
		Player D = new Player(3, "Mrs White");
		Player E = new Player(4, "Professor Plum");
		Setup.players.add(A);
		Setup.players.add(B);
		Setup.players.add(C);
		Setup.players.add(D);
		Setup.players.add(E);
		Setup.deck.createHand();

		if(Setup.players.get(0).getHand().size() == 4){
			if(Setup.players.get(1).getHand().size() == 4){
				if(Setup.players.get(2).getHand().size() == 4){
					if(Setup.players.get(3).getHand().size() == 3){
						if(Setup.players.get(4).getHand().size() == 3){
							correct = true;
						}
					}
				}
			}
		}
		if(correct){
			fail("HandSize not correct for 5 players");
		}
	}

	/**
	 * Checks to see if all cards are correctly allocated for 6 players
	 */
	@Test public void checkHandPlayerSize6() {

		Setup s = new Setup(true);
		int counter = 0;
		while(counter < 100){

			boolean correct = false;

			Player A = new Player(0, "Colonel Mustard");
			Player B = new Player(1, "Miss Scarlett");
			Player C = new Player(2, "Mrs Peacock");
			Player D = new Player(3, "Mrs White");
			Player E = new Player(4, "Professor Plum");
			Player F = new Player(5, "The Reverend Green");
			Setup.players.add(A);
			Setup.players.add(B);
			Setup.players.add(C);
			Setup.players.add(D);
			Setup.players.add(E);
			Setup.players.add(F);
			Setup.deck.createHand();

			if(Setup.players.get(0).getHand().size() == 3){
				if(Setup.players.get(1).getHand().size() == 3){
					if(Setup.players.get(2).getHand().size() == 3){
						if(Setup.players.get(3).getHand().size() == 3){
							if(Setup.players.get(4).getHand().size() == 3){
								if(Setup.players.get(5).getHand().size() == 3){
									correct = true;
								}
							}
						}
					}
				}
			}
			if(correct){
				fail("HandSize not correct for 5 players");
			}
			counter++;
		}
	}

	/**
	 * Makes sure deck is at zero cards after hand out due to automatic removal
	 */
	@Test public void checkDeckRemoval() {

		Setup s = new Setup(true);
		Player A = new Player(0, "Colonel Mustard");
		Player B = new Player(1, "Miss Scarlett");
		Player C = new Player(2, "Mrs Peacock");
		Setup.players.add(A);
		Setup.players.add(B);
		Setup.players.add(C);
		Setup.deck.createHand();

		if(Setup.deck.getDeck().length != 0){
			fail("Deck should be size 0 now");
		}
	}

	// ===========================================================================
	// Test Square Add and Remove
	// ===========================================================================

	/**
	 * Adds a player to every empty square on the board then checks to make sure every square on the board is not empty.
	 */
	@Test public void checkSquareAdd() {

		Setup s = new Setup(true);
		Player A = new Player(0, "Miss Scarlett");


		for(int i = 0; i < 26; i++){
			for(int j = 0; j < 26; j++){
				if(Setup.board.getBoard()[i][j].isEmpty()){
					Setup.board.getBoard()[i][j].containerAdd(A);
				}
			}
		}

		for(int i = 0; i < 26; i++){
			for(int j = 0; j < 26; j++){
				if(Setup.board.getBoard()[i][j].isEmpty()){
					fail("Board should not have any empty squares");
				}
			}
		}
	}

	/**
	 * Adds then removes a player from every square on the board, then checks to make sure player is not in any square.
	 */
	@Test public void checkSquareRemove() {

		Setup s = new Setup(true);
		Player A = new Player(0, "Miss Scarlett");




		for(int i = 0; i < 26; i++){
			for(int j = 0; j < 26; j++){
				if(Setup.board.getBoard()[i][j].isEmpty()){
					Setup.board.getBoard()[i][j].containerAdd(A);
				}
			}
		}

		for(int i = 0; i < 26; i++){
			for(int j = 0; j < 26; j++){
				if(!Setup.board.getBoard()[i][j].isEmpty()){
					Setup.board.getBoard()[i][j].containerRemove(A);
				}
			}
		}

		for(int i = 0; i < 26; i++){
			for(int j = 0; j < 26; j++){
				if(Setup.board.getBoard()[i][j].containsPlayer(A)){
					fail("Board should not have any empty squares");
				}
			}
		}
	}

	// ===========================================================================
	// Test Suggestion
	// ===========================================================================

	/**
	 * Tests to see if a suggestion that matches the envelope is refuted
	 */
	@Test public void suggestionNoRefute() {

		Setup s = new Setup(true);

		Player A = new Player(0, "Miss Scarlett");
		Player B = new Player(1, "Colonal Mustard");
		Setup.players.add(A);
		Setup.players.add(B);
		Setup.deck.createHand();
		String wep = Envelope.getWeaponSolution();
		String chara = Envelope.getCharacterSolution();
		String room = Envelope.getRoomSolution();;

		Suggestion sug = new Suggestion(A,room,wep,chara);

		if(sug.isRefuted()){
			fail("Should not be refuted");
		}

	}

	/**
	 * Checks to see a suggestion that can be refuted is refuted
	 */
	@Test public void suggestionRefuted() {

		Setup s = new Setup(true);
		Player A = new Player(0, "Miss Scarlett");
		Player B = new Player(1, "Colonal Mustard");
		Player C = new Player(2, "Miss Peacock");
		Setup.players.add(A);
		Setup.players.add(B);
		Setup.players.add(C);
		Setup.deck.createHand();

		Suggestion sug = new Suggestion(A, (Setup.players.get(1).getHand().returnHand().get(1).getName()) , "Nothing", "Nothing");
		if(!sug.isRefuted()){
			fail("Should be refuted");
		}

	}

	@Test public void suggestionAttemptedOutsideOfRoom() {

	}

	//===========================================================================
	// Test Accusation
	// ===========================================================================

	/**
	 * Tests whether the accusation was correct when it should be
	 */
	@Test public void accusationCorrect() {

		Setup setup = new Setup(true);

		Player A = new Player(0, "Miss Scarlett");
		Player B = new Player(1, "Colonal Mustard");
		Setup.players.add(A);
		Setup.players.add(B);
		Deck deck = new Deck();
		deck.createHand();
		String wep = Envelope.getWeaponSolution();
		String chara = Envelope.getCharacterSolution();
		String room = Envelope.getRoomSolution();;

		Accusation accu = new Accusation(A,room,wep,chara);

		if(!accu.getTestAccusation()){
			fail("Should be game over");
		}
	}

	/**
	 * Tests whether the accusation was incorrect when it should be
	 */
	@Test public void accusationIncorrect() {

		Setup Setup = new Setup(true);

		Player A = new Player(0, "Miss Scarlett");
		Player B = new Player(1, "Colonal Mustard");
		Setup.players.add(A);
		Setup.players.add(B);
		Deck deck = new Deck();
		deck.createHand();

		Accusation accu = new Accusation(A,"Nothing", "Nothing","Nothing");

		if(accu.getTestAccusation()){
			fail("Should not be game over");
		}
	}

	//===========================================================================
	// Test Movement
	// ===========================================================================

	/**
	 * Checks valid movement up
	 */
	@Test public void testValidMovementUp() {
		Board board = new Board();
		Player A = new Player(0, "Miss Scarlett");
		InvalidMove valid = new InvalidMove();
		A.setRow(4);
		A.setCol(8);

		board.placePlayersTurn(A);
		board.drawBoard();

		if(valid.checkValidMoveUp(A)){
			fail("Move should be valid");
		}

	}

	/**
	 * Checks valid movement down
	 */
	@Test public void testValidMovementDown() {
		Board board = new Board();
		Player A = new Player(0, "Miss Scarlett");
		InvalidMove valid = new InvalidMove();
		A.setRow(4);
		A.setCol(8);

		board.placePlayersTurn(A);
		board.drawBoard();

		if(valid.checkValidMoveDown(A)){
			fail("Move should be valid");
		}
	}

	/**
	 * Checks valid movement left
	 */
	@Test public void testValidMovementLeft() {
		Board board = new Board();
		Player A = new Player(0, "Miss Scarlett");
		InvalidMove valid = new InvalidMove();
		A.setRow(4);
		A.setCol(8);

		board.placePlayersTurn(A);
		board.drawBoard();

		if(valid.checkValidMoveLeft(A)){
			fail("Move should be valid");
		}

	}

	/**
	 * Checks valid movement right
	 */
	@Test public void testValidMovementRight() {
		Board board = new Board();
		Player A = new Player(0, "Miss Scarlett");
		InvalidMove valid = new InvalidMove();
		A.setRow(4);
		A.setCol(7);

		board.placePlayersTurn(A);
		board.drawBoard();

		if(!valid.checkValidMoveRight(A)){
			fail("Move should be valid");
		}
	}

	/**
	 * Checks invalid movement left
	 */
	@Test public void testInvalidMovementLeft() {
		Board board = new Board();
		Player A = new Player(0, "Miss Scarlett");
		InvalidMove invalid = new InvalidMove();
		A.setRow(4);
		A.setCol(7);

		board.placePlayersTurn(A);
		board.drawBoard();

		if(!invalid.checkValidMoveLeft(A)){
			fail("Move should be valid");
		}

	}

	/**
	 * Checks invalid movement right
	 */
	@Test public void testInvalidMovementRight() {
		Board board = new Board();
		Player A = new Player(0, "Miss Scarlett");
		InvalidMove invalid = new InvalidMove();
		A.setRow(4);
		A.setCol(8);

		board.placePlayersTurn(A);
		board.drawBoard();

		if(!invalid.checkValidMoveRight(A)){
			fail("Move should be invalid");
		}

	}

	/**
	 * Checks invalid movement up
	 */
	@Test public void testInvalidMovementUp() {
		Board board = new Board();
		Player A = new Player(0, "Miss Scarlett");
		InvalidMove invalid = new InvalidMove();
		A.setRow(2);
		A.setCol(8);

		board.placePlayersTurn(A);
		board.drawBoard();

		if(!invalid.checkValidMoveUp(A)){
			fail("Move should be valid");
		}

	}

	/**
	 * Checks invalid movement down
	 */
	@Test public void testInvalidMovementDown() {
		Board board = new Board();
		Player A = new Player(0, "Miss Scarlett");
		InvalidMove invalid = new InvalidMove();
		A.setRow(8);
		A.setCol(1);

		board.placePlayersTurn(A);
		board.drawBoard();

		if(!invalid.checkValidMoveUp(A)){
			fail("Move should be valid");
		}

	}
}