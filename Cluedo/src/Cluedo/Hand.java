package Cluedo;
import java.util.ArrayList;

/**
 * A hand holds the players cards in a ArrayList<Card>
 * @author newtondavi2
 *
 */
public class Hand {

	ArrayList<Card> hand = new ArrayList<Card>();

	/**
	 * Adds card to the hand ArrayList
	 * @param card
	 */
	public void addCard(Card card) {
		hand.add(card);
	}

	/**
	 * Returns the size of the hand
	 * @return
	 */
	public int size() {
		return hand.size();
	}

	/**
	 * Prints all the cards in the hand
	 */
	public void getCards() {
		for(int i = 0; i < hand.size(); i++){
			System.out.println(hand.get(i).getName());
		}
	}

	/**
	 * Returns ArrayList hand
	 * @return
	 */
	public ArrayList<Card> returnHand(){
		return hand;
	}

}
