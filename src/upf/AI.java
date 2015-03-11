package upf;

import javax.swing.JLabel;


public class AI {
	static PlayerMovement p2 = new PlayerMovement();
	static int yCoord = BallMovement.yC; // Determines future y position of the ball
	int row, tempDiff, diff; // For the AI, to determine which direction to move

	/**
	 * Generates a random number between 1 and 13, and then generates another
	 * number between 0 and 1. If the second number is less than 0.5, the
	 * program moves player 2 left by the number generated originally. If it is
	 * not, then it moves right by that same number.
	 * 
	 * @param player2
	 *            The JLabel array that needs to be passed as a parameter to
	 *            prevent the players from moving out of bounds
	 */
	public void moveRandom(JLabel[] player2) {

		// makes the "AI" move in a random direction

		try {
			Thread.sleep(200);
		} catch (InterruptedException ie) {
		}
		int numOfMoves = (int) (Math.random() * 13 + 1); // Can move up to 13
															// places
		if (Math.random() < 0.5)
			for (int x = 0; x < numOfMoves; x++) { // Repeats for the number of
													// intended moves
				if (!(player2[3].getX() + 3 < 20)) // Prevents going off the
													// screen
					p2.p2Left();
				try {
					Thread.sleep(20); // Pause so that the AI does not teleport
				} catch (InterruptedException ie) {
				}
			}
		else
			for (int x = 0; x < numOfMoves; x++) {
				if (!(player2[6].getX() + 3 > 252))
					p2.p2Right();
				try {
					Thread.sleep(20);
				} catch (InterruptedException ie) {
				}
			}

	}

	/**
	 * Moves the AI to block the ball. Designed so that it does not block all
	 * the balls, and is thus beatable
	 * 
	 * @param player2
	 *            The JLabel array that is required for determining which
	 *            direction to move the AI
	 */
	public void moveSmart(JLabel[] player2) {
		yCoord = BallMovement.yC; // Resets yCoord
		outerloop: // Label to exit from while loop
		while (true) {
			if (yCoord <= 0 || yCoord >= 490) // If yCoord has exceeded the
												// screen, it will reset
				yCoord = BallMovement.yC;
			yCoord += BallMovement.yV; // Adds the velocity repeatedly
			for (int x = 0; x < 4; x++)
				if (Math.abs(yCoord - p2.y2[x]) <= 10) { // If yCoord becomes
															// equal to any of
															// the player rows,
															// then it registers
															// the row value
					row = x;
					break outerloop;
				}
		}
		// Depending on the row, the program will do different things
		if (row == 0)
			// Goalie
			determineDirection(BallMovement.xC - player2[0].getX());
		else if (row == 1) {
			// Defender
			tempDiff = BallMovement.xC - player2[1].getX();
			diff = BallMovement.xC - player2[2].getX();
			if (Math.abs(tempDiff) < Math.abs(diff))
				// player is closer and
														// moves accordingly
				determineDirection(tempDiff);
			else
				determineDirection(diff);
		} else if (row == 2) {
			// Midfield
			diff = BallMovement.xC - player2[3].getX();
			for (int i = 0; i < 3; i++)
				if (Math.abs(diff) > Math.abs(BallMovement.xC
						- player2[i + 4].getX()))
					diff = BallMovement.xC - player2[i + 4].getX();
			determineDirection(diff);
		} else if (row == 3) {
			// Attacker
			diff = BallMovement.xC - player2[7].getX();
			for (int i = 0; i < 2; i++)
				if (Math.abs(diff) > Math.abs(BallMovement.xC
						- player2[i + 8].getX()))
					diff = BallMovement.xC - player2[i + 8].getX();
			determineDirection(diff);
		}
	}

	/**
	 * Determines whether to move left or right
	 * 
	 * @param difference
	 *            if this is negative, the player is moved to the left, if it
	 *            positive, the player is moved to the right
	 */
	public void determineDirection(int difference) {
		if (difference < 0 && !(Game.player2[3].getX() - 1 < 20))
			// difference
																	// is less
																	// than
																	// zero,
																	// then it
																	// means the
																	// ball is
																	// further
																	// to the
																	// right of
																	// the
																	// player,
																	// so the AI
																	// moves the
																	// player
																	// left,
			p2.p2Left();
		else if (difference > 0 && !(Game.player2[6].getX() + 1 > 252))
			// it
																			// is
																			// positive,
																			// the
																			// AI
																			// moves
																			// the
																			// player
																			// right
			p2.p2Right();
	}
}
