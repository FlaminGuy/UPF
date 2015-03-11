package upf;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;


public class BallMovement
{

	static int xC = 140, yC = 266, yV = 0, xV = 0; // Values for the x and y coordinates and velocities
	Ellipse2D.Double b;
	Ellipse2D.Double[] p1 = new Ellipse2D.Double[10]; // An Ellipse2D.Double for players
	Ellipse2D.Double[] p2 = new Ellipse2D.Double[10];

	/**
	 * Adds the x and y velocities to the x and y coordinates of the ball, and
	 * calls the method to check for ball collisions.
	 * <p>
	 * Also calls the method that resets the ball using new coordinates.
	 * 
	 * @param ball
	 *            JLabel that contains the image for the ball
	 * @param player1
	 *            JLabel array that contains the players for team 1
	 * @param player2
	 *            JLabel array that contains the players for team 2
	 * @param net1
	 *            JLabel that contains the image for team 1's net
	 * @param net2
	 *            JLabel that contains the image for the team 2's net
	 */
	public void updateBallPosition(JLabel ball, JLabel[] player1,
			JLabel[] player2, JLabel net1, JLabel net2)
	{
		xC += xV; // Moves ball position by the value of the x and y velocities
		yC += yV;
		b = new Ellipse2D.Double(xC, yC, 7, 7); // Creates an Ellipse2D.Double so that collisions can be detected easier
		Rectangle2D.Double[] n1 = createNet(net1); // Creates the array of net rectangles
		Rectangle2D.Double[] n2 = createNet(net2);
		for (int x = 0; x < 10; x++)
		{ // Updates the Ellipse2D.Double array so that collisions between players can be more easily detected
			p1[x] = new Ellipse2D.Double(player1[x].getX(), player1[x].getY(),
					player1[x].getWidth(), player1[x].getHeight());
			p2[x] = new Ellipse2D.Double(player2[x].getX(), player2[x].getY(),
					player2[x].getWidth(), player2[x].getHeight());
		}
		detectCollision(n1, n2);
		ball.setLocation(xC, yC);
	}

	/**
	 * Creates the three Rectangle2D.Double objects that are used for collision
	 * detection with the net.
	 * 
	 * @param net
	 *            JLabel that influences where the net rectangles are created
	 * @return the array of Rectangle 2D objects that are used for collision
	 *         detection
	 */
	public Rectangle2D.Double[] createNet(JLabel net)
	{ // Necessary, as the net is divided into 3 parts: two side panels that reflect the ball, and the middle one that registers a goal
		Rectangle2D.Double[] output = {
				new Rectangle2D.Double(net.getX() - 1, net.getY(), 1,
						net.getHeight()),
				new Rectangle2D.Double((net.getX() + (net.getWidth())),
						net.getY(), 1, net.getHeight()),
				new Rectangle2D.Double((net.getX()), net.getY(),
						(net.getWidth()), net.getHeight()) };
		return output;
	}

	/**
	 * Determines whether or not the ball intersects any of the players or the
	 * net. Depending on what it hits, it readjusts the x and y velocities.
	 * 
	 * @param n1
	 *            The array of net rectangles for player 1
	 * @param n2
	 *            The array of net rectangles for player 2
	 */
	public void detectCollision(Rectangle2D.Double[] n1, Rectangle2D.Double[] n2)
	{

		if (xC < 20 && xV < 0)
			xV = -xV;
		else if (xC > 252 && xV > 0)
			xV = -xV;
		else if (yC < 89 && yV < 0)
			yV = -yV;
		else if (yC > 440 && yV > 0)
			yV = -yV;
		if (b.intersects(n1[0]))
			// left net rectangle
			xV = -xV;
		else if (b.intersects(n1[1]))
			// right net rectangle
			xV = -xV;
		else if (b.intersects(n1[2]))
		{
			// goal p1
			Game.scoreCounter1 += 1;
			// red team
			Game.goal(true, Game.scoreCounter1); // Shows goal message
		}
		if (b.intersects(n2[0])) // left net rectangle
			xV = -xV;
		else if (b.intersects(n2[1])) // right net rectangle
			xV = -xV;
		else if (b.intersects(n2[2]))// goal p2
		{
			Game.scoreCounter2 += 1;
			Game.goal(false, Game.scoreCounter2); // Shows goal message
		}
		for (int x = 0; x < 10; x++)
		{
			Rectangle2D.Double[] boundingRectp1 = new Rectangle2D.Double[5];
			boundingRectp1 = createBoundingRect(p1[x]);
			Rectangle2D.Double[] boundingRectp2 = new Rectangle2D.Double[5];
			boundingRectp2 = createBoundingRect(p2[x]);
			int increaseAmount = 1; // Amount to increase/decrease x and y velocities if it hits the corner of the player
			if (b.intersects(boundingRectp1[2])) // Center-most rectangle for player 1
			{
				if (yV >= 0)
					yV = -yV;
			}
			else if (b.intersects(boundingRectp1[0]))
			{
				// Outer-left rectangle for player 1
				if (xV > 1 && (yV >= 1 && yV < 8))
				{ // Determines which direction the ball was moving when it hit the rectangle, as the reaction will be different based on whether the x value was positive or negative
					xV -= increaseAmount;
					yV += increaseAmount;
				}
				else if (xV <= -1 && (yV > 1 && yV <= 8))
				{
					xV -= increaseAmount;
					yV -= increaseAmount;
				}
				else if (yV <= 0)// Ensures that the total velocity is always 8
				{
					if (xV < -1)
					{
						xV += increaseAmount;
						yV -= increaseAmount;
					}
					if (xV > 1)
					{
						xV -= increaseAmount;
						yV -= increaseAmount;
					}
				}
				if (yV >= 0) // the right direction
					yV = -yV;
			}
			else if (b.intersects(boundingRectp1[1])) // Follows same logic as the commented code above
			{
				// Outer-right rectangle for player 1
				if (xV < -1 && (yV >= 1 && yV < 8))
				{
					xV += increaseAmount;
					yV += increaseAmount;
				}
				else if (xV >= 1 && (yV > 1 && yV <= 8))
				{
					xV += increaseAmount;
					yV -= increaseAmount;
				}
				else if (yV <= 0)
				{
					if (xV < -1)
					{
						xV += increaseAmount;
						yV -= increaseAmount;
					}
					if (xV > 1)
					{
						xV -= increaseAmount;
						yV -= increaseAmount;
					}
				}
				if (yV >= 0)
					yV = -yV;
			}
			else if (b.intersects(boundingRectp2[2]))// Center-most rectangle for player 2
			{
				if (yV <= 0)
					yV = -yV;
			}
			else if (b.intersects(boundingRectp2[0]))
			{// Follows same logic as the commented code above Outer-left rectangle for player 2
				if (xV > 1 && (yV < -1 && yV >= -8))
				{
					xV -= increaseAmount;
					yV -= increaseAmount;
				}
				else if (xV <= -1 && (yV <= -1 && yV > -8))
				{
					xV -= increaseAmount;
					yV += increaseAmount;
				}
				else if (yV >= 0)
				{
					if (xV < -1)
					{
						xV += increaseAmount;
						yV += increaseAmount;
					}
					if (xV > 1)
					{
						xV -= increaseAmount;
						yV += increaseAmount;
					}
				}
				if (yV <= 0)
					yV = -yV;
			}
			else if (b.intersects(boundingRectp2[1]))
			{// Follows same logic as the commented code above Outer-right rectangle for player 2
				if (xV < -1 && (yV <= -1 && yV > -8))
				{
					xV += increaseAmount;
					yV -= increaseAmount;
				}
				else if (xV > 1 && (yV < -1 && yV >= -8))
				{
					xV += increaseAmount;
					yV += increaseAmount;
				}
				else if (yV >= 0)
				{
					if (xV < -1)
					{
						xV += increaseAmount;
						yV += increaseAmount;
					}
					if (xV > 1)
					{
						xV -= increaseAmount;
						yV += increaseAmount;
					}
				}
				if (yV <= 0)
					yV = -yV;
			}
		}
	}

	/**
	 * Creates the 3 rectangles for each player so that collisions can be
	 * handled.
	 * 
	 * @param player
	 *            The Ellipse2D.Double that covers the player objects
	 * @return an array of rectangles that cover the players
	 */
	public Rectangle2D.Double[] createBoundingRect(Ellipse2D.Double player)
	{ // Required so that collisions can be different based on the side of the player the ball hits
		Rectangle2D.Double[] rect = new Rectangle2D.Double[3]; // Array of the rectangles for the players
		rect[0] = new Rectangle2D.Double(player.getX(), player.getY(), 3,
				player.height);
		rect[1] = new Rectangle2D.Double((player.getX() + 4), player.getY(), 3,
				player.height);
		rect[2] = new Rectangle2D.Double((player.getX() + 3), player.getY(), 1,
				player.height);
		return rect;
	}

	/**
	 * Resets the location and the velocities of the ball
	 * 
	 * @param global
	 *            True if the method has been called from outside of the class.
	 *            This makes it so that the count down will not start until the
	 *            tutorial has been shown.
	 */
	public static void resetBall(boolean global)
	{
		if (!global)
		{
			Game.countdown.setVisible(true);
			for (int i = 3; i != 0; i--)
			{ // Counts down from 3
				Game.countdown.setText("" + i);
				Game.countdown.setFont(Game.countdownFont1); // Big font
				UPF.pause(750);
				Game.countdown.setFont(Game.countdownFont2); // Small font
				UPF.pause(250);
			}
			Game.countdown.setVisible(false);
			Game.toMenu.setEnabled(true);
			Game.ball.setVisible(true);
		}
		xV = 0; // Resets velocity so that the ball can randomly be given a new
				// velocity
		yV = 0;
		while (xV == 0 || yV == 0) // So that x or y will never be 0
		{
			xV = (int) (Math.random() * 13 - 6); // Assigns x a random number from -6 to 6
			if (Math.random() < 0.5)
				// negative
				yV = 8 - Math.abs(xV);
			else
				yV = -(8 - Math.abs(xV));
		}
		xC = 140; // Resets initial x and y coordinates
		yC = 266;
	}
}
