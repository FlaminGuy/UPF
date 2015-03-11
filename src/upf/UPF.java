package upf;

import java.awt.Desktop;
import java.awt.Font;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;


public class UPF
{

	static JFrame f = new JFrame("Ultimate Pocket Foosball"); // Main JFrame
	static JLayeredPane lp = new JLayeredPane(); // Layered Pane that contains all components
	static JButton ad = new JButton("advertisement"); // Banner advertisement

	static Font adFont = new Font("Arial", Font.PLAIN, 20); // Font for advertisement text

	/**
	 * Sets up constant JComponents, and starts the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{

		// Sets up JFrame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setFocusable(true);
		f.setVisible(true);
		f.setBounds(200, 100, 280 + 6, 550 + 25);
		f.setLayout(null);

		// Phone background image
		JLabel pBackground = new JLabel(new ImageIcon("resources/phone.png"));
		pBackground.setSize(280, 550);

		// Banner ad Button
		ad = new JButton("advertisement");
		ad.setFont(adFont);
		ad.setBounds(14, 452, 253, 40);

		// Layered pane
		lp.setBounds(0, 0, 280, 550);
		lp.add(pBackground, new Integer(4));
		lp.setFocusable(true);

		// Adding to layered pane
		lp.add(ad, new Integer(2));

		// Adding to frame
		f.add(UPF.lp);

		new Menu(); // Starting Menu class
	}

	/**
	 * Pauses the thread
	 * 
	 * @param time
	 *            Amount of time to pause in milliseconds
	 */
	public static void pause(int time)
	{
		try
		{
			Thread.sleep(time);
		}
		catch (InterruptedException e)
		{
		}
	}

	/**
	 * Goes to Dragons' Den website
	 */
	public static void link()
	{
		try
		{
			Desktop.getDesktop().browse(
					new URL("http://www.cbc.ca/dragonsden/").toURI());
		}
		catch (Exception e)
		{
		}
	}

}
