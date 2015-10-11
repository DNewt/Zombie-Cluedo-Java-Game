package Cluedo;

import java.util.Random;

/**
 * The Dice is the random number generator which controls how many steps a player can take per turn
 * @author newtondavi2
 *
 */
public class Dice {

	/**
	 * The Dice is the random number generator for the amount of steps a player can take in a given turn.
	 */
	public Dice(){
		rollDice();
	}

	/**
	 * This generates a random number between and including 1 and 6.
	 * This number is returned and is used as the number of steps a player can move in a given turn.
	 * @return
	 */
	public int rollDice() {
		Random rand = new Random();
		int roll = rand.nextInt(6) + 1;
		return roll;

	}
}
