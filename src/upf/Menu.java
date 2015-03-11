package upf;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import upf.Game.MovePlayers;
import upf.Game.RunGame;


public class Menu implements ActionListener
{
	static AI bob = new AI(); // Creates a new AI object

	static JLabel sBackground; // Background of menu
	static JLabel title1, title2, title3, title1Shade, title2Shade,
			title3Shade; //Titles
	static JButton play1, play2, easy, hard, back, fullAd; //Buttons
	static JInternalFrame advertisement; // Full screen ad

	Font title = new Font("Berlin Sans FB Demi", Font.PLAIN, 42); //Title fonts
	Font button = new Font("Eras Demi ITC", Font.PLAIN, 25); //Button fonts

	static boolean runAI = true; // Determines if the AI is running or not
	static boolean gameMode; // Single player or multiplayer. True = 2 player,  False = single player
	static boolean difficulty; // Game difficulty. True = easy, False = hard 

	/**
	 * Creates the menu
	 */
	public Menu() {// Constructor (Output)

		// Background
		sBackground = new JLabel(new ImageIcon("resources//menuBKG.jpg"));
		sBackground.setBounds(14, 46, 253, 445);

		// Ultimate
		title1 = new JLabel("Ultimate", SwingConstants.CENTER);
		title1.setBounds(14, 75, 253, 42);
		title1.setFont(title);
		title1.setForeground(Color.WHITE);

		title1Shade = new JLabel("Ultimate", SwingConstants.CENTER);
		title1Shade.setBounds(4, 85, 253, 42);
		title1Shade.setFont(title);
		title1Shade.setForeground(new Color(0, 0, 0, 150));

		// Pocket
		title2 = new JLabel("Pocket", SwingConstants.CENTER);
		title2.setBounds(14, 125, 253, 42);
		title2.setFont(title);
		title2.setForeground(Color.WHITE);

		title2Shade = new JLabel("Pocket", SwingConstants.CENTER);
		title2Shade.setBounds(4, 135, 253, 42);
		title2Shade.setFont(title);
		title2Shade.setForeground(new Color(0, 0, 0, 150));

		// Foosball
		title3 = new JLabel("Foosball", SwingConstants.CENTER);
		title3.setBounds(14, 175, 253, 42);
		title3.setFont(title);
		title3.setForeground(Color.WHITE);

		title3Shade = new JLabel("Foosball", SwingConstants.CENTER);
		title3Shade.setBounds(4, 185, 253, 42);
		title3Shade.setFont(title);
		title3Shade.setForeground(new Color(0, 0, 0, 150));

		// Single Player button
		play1 = new JButton("SINGLE PLAYER");
		play1.setFont(button);
		play1.setBounds(24, 250, 233, 40);
		play1.addActionListener(this);

		// 2 Player Button
		play2 = new JButton("2 PLAYER");
		play2.setFont(button);
		play2.setBounds(24, 310, 233, 40);
		play2.addActionListener(this);

		// Difficulty
		// Easy
		easy = new JButton("EASY");
		easy.setFont(button);
		easy.setBounds(24, 250, 233, 40);
		easy.addActionListener(this);
		easy.setVisible(false);

		// Normal
		hard = new JButton("HARD");
		hard.setFont(button);
		hard.setBounds(24, 310, 233, 40);
		hard.addActionListener(this);
		hard.setVisible(false);

		// Back button
		back = new JButton("BACK");
		back.setFont(button);
		back.setBounds(24, 370, 233, 40);
		back.addActionListener(this);
		back.setVisible(false);

		// Ad button
		UPF.ad.addActionListener(this);

		// Adding
		UPF.lp.add(sBackground, new Integer(0));
		UPF.lp.add(title1, new Integer(2));
		UPF.lp.add(title2, new Integer(2));
		UPF.lp.add(title3, new Integer(2));
		UPF.lp.add(title1Shade, new Integer(1));
		UPF.lp.add(title2Shade, new Integer(1));
		UPF.lp.add(title3Shade, new Integer(1));
		UPF.lp.add(play1, new Integer(2));
		UPF.lp.add(play2, new Integer(2));
		UPF.lp.add(easy, new Integer(2));
		UPF.lp.add(hard, new Integer(2));
		UPF.lp.add(back, new Integer(2));

		UPF.f.repaint();
	}

	/**
	 * Removes the JButtons and JLabels from the JLayeredPane
	 */
	public void remove()
	{
		UPF.lp.remove(sBackground);
		UPF.lp.remove(title1);
		UPF.lp.remove(title2);
		UPF.lp.remove(title3);
		UPF.lp.remove(title1Shade);
		UPF.lp.remove(title2Shade);
		UPF.lp.remove(title3Shade);
		UPF.lp.remove(play1);
		UPF.lp.remove(play2);
		UPF.lp.remove(easy);
		UPF.lp.remove(hard);
		UPF.lp.remove(back);
		UPF.f.repaint();
		UPF.ad.removeActionListener(this);
	}

	/**
	 * Registers button presses
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == play1) // Changes button visibility to ask the user for AI difficulty
		{
			gameMode = false;
			play1.setVisible(false);
			play2.setVisible(false);
			easy.setVisible(true);
			hard.setVisible(true);
			back.setVisible(true);
		}
		else if (e.getSource() == play2) // Starts 2 player
		{
			gameMode = true;
			play();
		}
		if (e.getSource() == easy) //Starts easy mode
		{
			difficulty = true;
			play();
		}
		else if (e.getSource() == hard) //Starts hard mode
		{
			difficulty = false;
			play();
		}
		else if (e.getSource() == back) // Goes back to number of players selection
		{
			play1.setVisible(true);
			play2.setVisible(true);
			easy.setVisible(false);
			hard.setVisible(false);
			back.setVisible(false);
		}
		if (e.getSource() == UPF.ad) // Goes to Dragons' Den website
			UPF.link();
	}

	/**
	 * Starts the game
	 */
	public void play()
	{
		remove();
		new Game();
		RunGame.runGame = true;
		MovePlayers.movePlayers = true;
		// BallMovement.resetBall();
		if (!gameMode) // If 2 players, don't start the AI
		{
			runAI = true;
			new Thread(new RunAI()).start();
		}
	}

	/**
	 * Creates full screen advertisement
	 */
	public static void fullAd()
	{
		advertisement = new JInternalFrame("", false, true, false, false);
		fullAd = new JButton("advertisement");

		advertisement.setBounds(14, 46, 253, 445);
		fullAd.setBounds(0, 0, 243, 412);

		advertisement.setVisible(true);

		advertisement.add(fullAd);
		fullAd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent aActionEvent)
			{
				UPF.link();
			}
		});
		UPF.lp.add(advertisement, new Integer(7));
	}

	/**
	 * Responsible for the thread that moves the AI player
	 * 
	 * @author Aditya Matam
	 * 
	 */
	public static class RunAI implements Runnable
	{
		/**
		 * Repeatedly moves either the smart AI or the random AI depending on
		 * user choices
		 */
		@Override
		public void run()
		{
			while (runAI) // Stops if variable becomes false
			{
				UPF.pause(10);
				if (difficulty == true)
					bob.moveRandom(Game.player2);
				else
					bob.moveSmart(Game.player2);
			}
		}

		/**
		 * Suspends the execution of the AI
		 */
		public static void killAI() // (Kills Skynet)
		{
			runAI = false;
		}
	}
}
