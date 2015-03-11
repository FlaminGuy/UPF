package upf;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import upf.Menu.RunAI;


public class Game implements ActionListener, KeyListener
{

	static PlayerMovement move = new PlayerMovement(); // Creates an object of the PlayerMovement class.
	final static BallMovement bMove = new BallMovement(); // Creates an object of the BallMovement class.

	static JLabel sBackground, ball, goal, win, countdown, scoreRed, scoreBlue,
			insRightTop, insLeftTop, insRightBot, insLeftBot, // Background, ball, messages, score, instructions
			scoreDash = new JLabel("-");;
	static JLabel[] player1 = new JLabel[10], player2 = new JLabel[10],
			bar = new JLabel[8]; // Players
	final static JLabel net1 = new JLabel(new ImageIcon(
			"resources/goalnett.png")), net2 = new JLabel(new ImageIcon(
			"resources/goalnetb.png")); //Nets
	static JButton toMenu; // Returns to menu

	Font menuFont = new Font("Arial Narrow", Font.PLAIN, 20); // Font for return to menu button
	Font score = new Font("Arial Black", Font.PLAIN, 20); // Score font
	Font goalFont = new Font("Forte", Font.PLAIN, 90); // Goal message font
	Font winFont = new Font("Forte", Font.PLAIN, 80); // Winner message font
	static Font countdownFont1 = new Font("Hobo STD", Font.PLAIN, 72); // Start countdown font 1
	static Font countdownFont2 = new Font("Hobo STD", Font.PLAIN, 50); // Start countdown font 1

	static int scoreCounter1 = 0, scoreCounter2 = 0; // Game score 1 = blue, 2 = red
	static boolean[] keys = new boolean[4]; // Keyboard keys

	/**
	 * Creates the game
	 */
	public Game() {// Constructor

		UPF.f.addKeyListener(this);
		UPF.f.requestFocusInWindow();

		// Background
		sBackground = new JLabel(new ImageIcon("resources/gameBKG.jpg"));
		sBackground.setBounds(14, 46, 253, 445);

		// Return to menu button
		toMenu = new JButton("Menu");
		toMenu.setFont(menuFont);
		toMenu.setBounds(183, 54, 80, 30);
		toMenu.addActionListener(this);

		// Instructions
		insRightTop = new JLabel(new ImageIcon("resources/insTopRight.png"));
		insLeftTop = new JLabel(new ImageIcon("resources/insTopLeft.png"));
		insRightBot = new JLabel(new ImageIcon("resources/insBotRight.png"));
		insLeftBot = new JLabel(new ImageIcon("resources/insBotLeft.png"));

		insRightTop.setBounds(143, 92, 120, 176);
		insLeftTop.setBounds(21, 92, 120, 176);
		insRightBot.setBounds(143, 271, 120, 176);
		insLeftBot.setBounds(21, 271, 120, 176);

		insRightTop.setVisible(false);
		insLeftTop.setVisible(false);
		insRightBot.setVisible(false);
		insLeftBot.setVisible(false);

		// Nets
		net1.setBounds(100, 78, 82, 36);
		net2.setBounds(100, 424, 82, 36);

		// Bars
		ImageIcon barI = new ImageIcon("resources/bar.png");
		for (int i = 0; i < 4; i++)
		{
			bar[i * 2] = new JLabel(barI);
			bar[i * 2 + 1] = new JLabel(barI);
			bar[i * 2].setBounds(14, move.y1[i] + 2, 254, 5); //y1
			bar[i * 2 + 1].setBounds(14, move.y2[i] + 2, 254, 5); //y2
		}

		// Goal message
		goal = new JLabel("GOAL!", SwingConstants.CENTER);
		goal.setBounds(14, 230, 253, 90);
		goal.setFont(goalFont);
		goal.setVisible(false);

		// Win message
		win = new JLabel("", SwingConstants.CENTER);
		win.setBounds(14, 150, 253, 200);
		win.setFont(winFont);
		win.setVisible(false);

		// Start Countdown
		countdown = new JLabel("", SwingConstants.CENTER);
		countdown.setBounds(14, 230, 253, 72);
		countdown.setForeground(Color.yellow);
		countdown.setVisible(false);

		// Player1
		ImageIcon player;
		player = new ImageIcon("resources/player1.png");
		for (int i = 0; i != 10; i++)
		{
			player1[i] = new JLabel(player);
			player1[i].setSize(9, 9);// 12
		}

		// Player2
		player = new ImageIcon("resources/player2.png");
		for (int i = 0; i != 10; i++)
		{
			player2[i] = new JLabel(player);
			player2[i].setSize(9, 9);// 12
		}

		// Ball
		ball = new JLabel(new ImageIcon("resources/ball.png"));
		ball.setBounds(136, 265, 7, 7);
		ball.setVisible(false);

		// Scores
		scoreDash.setForeground(Color.yellow);
		scoreDash.setFont(score);
		scoreBlue = new JLabel("0");
		scoreBlue.setForeground(Color.blue);
		scoreBlue.setFont(score);
		scoreRed = new JLabel("0");
		scoreRed.setForeground(Color.red);
		scoreRed.setFont(score);
		scoreBlue.setBounds(52, 51, 15, 24);
		scoreDash.setBounds(67, 51, 8, 24);
		scoreRed.setBounds(79, 51, 15, 24);

		// Ad button
		UPF.ad.addActionListener(this);

		// Adding
		UPF.lp.add(sBackground, new Integer(1));
		UPF.lp.add(net1, new Integer(2));
		UPF.lp.add(net2, new Integer(2));
		for (int i = 0; i != 8; i++)
			UPF.lp.add(bar[i], new Integer(4));
		for (int i = 0; i != 10; i++)
		{
			UPF.lp.add(player1[i], new Integer(5));
			UPF.lp.add(player2[i], new Integer(5));
		}
		UPF.lp.add(ball, new Integer(3));
		UPF.lp.add(goal, new Integer(6));
		UPF.lp.add(win, new Integer(6));
		UPF.lp.add(insRightTop, new Integer(6));
		UPF.lp.add(insLeftTop, new Integer(6));
		UPF.lp.add(insRightBot, new Integer(6));
		UPF.lp.add(insLeftBot, new Integer(6));
		UPF.lp.add(countdown, new Integer(6));
		UPF.lp.add(scoreDash, new Integer(6));
		UPF.lp.add(scoreBlue, new Integer(6));
		UPF.lp.add(scoreRed, new Integer(6));
		UPF.lp.add(toMenu, new Integer(6));

		UPF.f.repaint();

		resetAll(); //Resets all variables to their initial values 
		new Thread(new RunGame()).start(); //Starts the thread that moves the ball
		new Thread(new MovePlayers()).start(); //Starts the thread that allows user to move players
	}

	/**
	 * Registers button presses
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == UPF.ad) // Goes to Dragons' Den website
			UPF.link();
		if (e.getSource() == toMenu) // Returns to menu
		{
			RunAI.killAI();
			returnToMenu();
		}
	}

	/**
	 * Registers keyboard key presses
	 */
	@Override
	public void keyPressed(KeyEvent e)
	{
		int k = e.getKeyCode();
		if (k == KeyEvent.VK_LEFT)
			keys[0] = true;
		else if (k == KeyEvent.VK_RIGHT)
			keys[1] = true;
		if (k == KeyEvent.VK_A & Menu.gameMode) // Only effective during 2 player
			keys[2] = true;
		else if (k == KeyEvent.VK_D & Menu.gameMode) // Only effective during 2 player
			keys[3] = true;
	}

	/**
	 * Registers keyboard key releases
	 */
	@Override
	public void keyReleased(KeyEvent e)
	{
		int k = e.getKeyCode();
		if (k == KeyEvent.VK_LEFT)
			keys[0] = false;
		else if (k == KeyEvent.VK_RIGHT)
			keys[1] = false;
		if (k == KeyEvent.VK_A & Menu.gameMode) // Only effective during 2 player
			keys[2] = false;
		else if (k == KeyEvent.VK_D & Menu.gameMode) // Only effective during 2 player
			keys[3] = false;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	/**
	 * Resets all variables to their initial values
	 */
	public static void resetAll()
	{
		BallMovement.resetBall(true);
		move.resetPlayers();
	}

	/**
	 * Returns to the main menu
	 */
	public static void returnToMenu()
	{
		remove();
		RunGame.killGame();
		MovePlayers.stopPlayers();
		scoreCounter1 = 0;
		scoreCounter2 = 0;
		Menu.fullAd();
		new Menu();
	}

	/**
	 * Removes the JButton and JLabels from the JLayeredPane
	 */
	public static void remove()
	{
		UPF.lp.remove(sBackground);
		UPF.lp.remove(net1);
		UPF.lp.remove(net2);
		for (int i = 0; i != 8; i++)
			UPF.lp.remove(bar[i]);
		for (int i = 0; i != 10; i++)
		{
			UPF.lp.remove(player1[i]);
			UPF.lp.remove(player2[i]);
		}
		UPF.lp.remove(ball);
		UPF.lp.remove(goal);
		UPF.lp.remove(win);
		UPF.lp.remove(insRightTop);
		UPF.lp.remove(insLeftTop);
		UPF.lp.remove(insRightBot);
		UPF.lp.remove(insLeftBot);
		UPF.lp.remove(countdown);
		UPF.lp.remove(scoreDash);
		UPF.lp.remove(scoreBlue);
		UPF.lp.remove(scoreRed);
		UPF.lp.remove(toMenu);
		UPF.f.repaint();
	}

	/**
	 * Shows game instructions
	 * 
	 * @param playersNum
	 *            Determines if single player or 2 player instructions should
	 *            show
	 */
	public static void instructions(boolean playersNum)
	{
		toMenu.setEnabled(false);
		if (playersNum)
		{
			insRightTop.setVisible(true);
			insLeftTop.setVisible(true);
		}
		insRightBot.setVisible(true);
		insLeftBot.setVisible(true);
		UPF.pause(1000);
		if (playersNum)
		{
			insRightTop.setVisible(false);
			insLeftTop.setVisible(false);
		}
		insRightBot.setVisible(false);
		insLeftBot.setVisible(false);
	}

	/**
	 * Shows "Goal!" message
	 * 
	 * @param colour
	 *            Determines which colour to show
	 * @param score
	 *            If the score is greater than 5, "Win" message shows
	 * 
	 */
	public static void goal(boolean colour, int score) // true = blue, false =
														// red
	{
		ball.setVisible(false);
		if (colour)
		{
			scoreBlue.setText("" + score);
			goal.setForeground(Color.blue);
		}
		else
		{
			scoreRed.setText("" + score);
			goal.setForeground(Color.red);
		}
		toMenu.setEnabled(false);
		goal.setVisible(true);
		UPF.pause(2000);
		goal.setVisible(false);
		if (score == 5)
			win(colour);
		else
			BallMovement.resetBall(false);
	}

	/**
	 * Show "Win" message, and returns to the main menu
	 * 
	 * @param colour
	 *            Determines which colour to show of the message
	 */
	public static void win(boolean colour)
	{
		RunAI.killAI();
		if (colour)
		{
			win.setForeground(Color.blue);
			win.setText("<html>BLUE<br>WINS</html>");
		}
		else
		{
			win.setForeground(Color.red);
			win.setText("<html>RED<br>WINS</html>");
		}
		toMenu.setEnabled(false);
		win.setVisible(true);
		UPF.pause(2000);
		returnToMenu();
	}

	/**
	 * Responsible for the thread that moves the players
	 * 
	 * @author Aditya Matam
	 * 
	 */
	public static class MovePlayers implements Runnable
	{
		static boolean movePlayers = true;

		/**
		 * Repeatedly runs the method that registers the user's key presses
		 */
		@Override
		public void run()
		{
			while (movePlayers)
			{ // Can stop if variable becomes false
				UPF.pause(10);
				move.move();
			}
		}

		/**
		 * Pauses the execution of the thread that moves the players
		 */
		public static void stopPlayers()
		{
			movePlayers = false;
		}
	}

	/**
	 * Responsible for the thread that constantly updates the ball position
	 * 
	 * @author Aditya Matam
	 * 
	 */
	public static class RunGame implements Runnable
	{
		static boolean runGame = true;

		/**
		 * Repeatedly updates ball position
		 */
		@Override
		public void run()
		{
			instructions(Menu.gameMode); // Instructions
			BallMovement.resetBall(false);
			while (runGame) // Stops if variable becomes false
			{ 
				UPF.pause(43);
				bMove.updateBallPosition(ball, player1, player2, net1, net2);
			}
		}

		/**
		 * pauses game execution
		 */
		public static void killGame()
		{
			runGame = false;
		}
	}
}
