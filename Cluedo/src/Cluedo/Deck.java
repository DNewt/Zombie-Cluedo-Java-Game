package Cluedo;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

/**
 * The Deck is the 'Card Manager'. First of all it creates all the cards in the game.
 * It then creates the Envelope, shuffles the deck then deals the cards to each individual players hand.
 * @author newtondavi2
 *
 */
public class Deck {

	private Card[] deck = new Card[21];

	/**
	 * The Deck is the 'Card Manager'.
	 * It creates the 21 cards required for a game of Cluedo.
	 * It creates the Envelope for the game of Cluedo which holds the Solution to win the game
	 * It shuffles the deck and then allocates the cards to each individual players Hand.
	 */
	public Deck(){
		createDeck();
		createEnvelope();
		shuffleDeck();
	}

	/**
	 * Returns the deck
	 */
	public Card[] getDeck(){
		return deck;
	}


	/**
	 * Shuffles the deck to randomize it every game
	 */
	private void shuffleDeck() {
		Random rnd = new Random();
	    for (int i = deck.length - 1; i > 0; i--){
	      int index = rnd.nextInt(i + 1);
	      Card a = deck[index];
	      deck[index] = deck[i];
	      deck[i] = a;
	    }
	    System.out.println();
	}

	/**
	 * Removes the card at the index in the deck.
	 * Then creates an updatedDeck of size minus 1 and puts each remaining card into this deck.
	 * Assigns deck to the updatedDeck.
	 * @param index
	 * @return
	 */
	private Card[] removeCard(int index){

		 deck[index] = null;
		 int cardsRemaining = 0;
		 Card[] updatedDeck = new Card[deck.length - 1];
		 for(int i = 0; i < deck.length; i++){
			 if(deck[i] instanceof Card){
				 updatedDeck[cardsRemaining] = deck[i];
				 cardsRemaining++;
			 }
		 }
		return updatedDeck;
	 }

	/**
	 * Selects the Weapon, Character and Room card from the unshuffled deck.
	 * Then creates an Envelope with this cards and then remove them from the deck.
	 */
	private void createEnvelope() {

		Random rand = new Random();
		int Wep = rand.nextInt(5 - 0) + 0;
		int Char = rand.nextInt((11 - 6) + 1) + 6;
		int Room = rand.nextInt((20 - 12) + 1) + 12;

		Envelope.setRoomSolution(deck[Room]);
		Envelope.setWeaponSolution(deck[Wep]);
		Envelope.setCharacterSolution(deck[Char]);

		deck = removeCard(Room);
		deck = removeCard(Char);
		deck = removeCard(Wep);
	}

	/**
	 * Creates each individual card and puts it in the deck
	 */
	public void createDeck(){

		deck[0] = new Card("Weapon", "Candlestick");
		deck[1] = new Card("Weapon", "Dagger");
		deck[2] = new Card("Weapon", "LeadPipe");
		deck[3] = new Card("Weapon", "Revolver");
		deck[4] = new Card("Weapon", "Rope");
		deck[5] = new Card("Weapon", "Spanner");
		deck[6] = new Card("Character", "Colonel Mustard");
		deck[7] = new Card("Character", "Miss Scarlett");
		deck[8] = new Card("Character", "Mrs Peacock");
		deck[9] = new Card("Character", "Mrs White");
		deck[10] = new Card("Character", "Professor Plum");
		deck[11] = new Card("Character", "The Reverend Green");
		deck[12] = new Card("Room", "Ballroom");
		deck[13] = new Card("Room", "Billiard");
		deck[14] = new Card("Room", "Conservatory");
		deck[15] = new Card("Room", "Dining");
		deck[16] = new Card("Room", "Hall");
		deck[17] = new Card("Room", "Kitchen");
		deck[18] = new Card("Room", "Library");
		deck[19] = new Card("Room", "Lounge");
		deck[20] = new Card("Room", "Study");

	}

	/**
	 * Creates each individual players hand.
	 * Selects a card at random from the deck and allocates it to a players hand.
	 * @param players
	 */
	public void createHand() {

				Random rand = new Random();
				int playerNumber = 1;
				while(deck.length > 0){

					for(int i = 0; i < Setup.players.size(); i++){
						if(deck.length == 0){
							break;
						}
						int cardIndex = rand.nextInt(deck.length);
						(Setup.players.get(playerNumber-1)).getHand().addCard(deck[cardIndex]);
						deck = removeCard(cardIndex);
						if(playerNumber == Setup.players.size()){
							playerNumber = 0;
						}
						playerNumber++;
					}
				}

	}
}






