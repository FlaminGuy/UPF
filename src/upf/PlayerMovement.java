package upf;


{
	final int[] xStart = { 80, 136, 188, 57, 110, 159, 210, 108, 163, 136 }; // Initial player position
	int[] x1 = { 80, 136, 188, 57, 110, 159, 210, 108, 163, 136 }; // Blue players x coordinates
	int[] x2 = { 80, 136, 188, 57, 110, 159, 210, 108, 163, 136 };// Red players x coordinates
	int[] y1 = { 195, 291, 370, 402 };// Blue players y coordinates
	int[] y2 = { 125, 155, 235, 330 };// Red players y coordinates
	
	/**
	 * Blue player position
	 * @param i
	 * Determines which player
	 */
	public void player1Position(int i)
	{
		if (i < 3)
			Game.player1[i].setLocation(x1[i], y1[0]);
		else if (i < 7)
			Game.player1[i].setLocation(x1[i], y1[1]);
		else if (i < 9)
			Game.player1[i].setLocation(x1[i], y1[2]);
		else
			Game.player1[i].setLocation(x1[i], y1[3]);
	}
	
	/**
	 * Red player position
	 * @param i
	 * Determines which player
	 */
	public void player2Position(int i)
	{
		if (i < 3)
			Game.player2[i].setLocation(x2[i], y2[3]);
		else if (i < 7)
			Game.player2[i].setLocation(x2[i], y2[2]);
		else if (i < 9)
			Game.player2[i].setLocation(x2[i], y2[1]);
		else
			Game.player2[i].setLocation(x2[i], y2[0]);
	}
	/**
	 * Moves blue player left
	 */
	public void p1Left()
	{
		for (int i = 0; i != 10; i++)
		{
			x1[i] -= 3;
			player1Position(i);
		}
	}
	/**
	 * Moves blue player right
	 */
	public void p1Right()
	{
		for (int i = 0; i != 10; i++)
		{
			x1[i] += 3;
			player1Position(i);
		}
	}
	/**
	 * Moves blue player left
	 */
	public void p2Left()
	{
		for (int i = 0; i != 10; i++)
		{
			x2[i] -= 3;
			player2Position(i);
		}
	}
	/**
	 * Moves blue player left
	 */
	public void p2Right()
	{
		for (int i = 0; i != 10; i++)
		{
			x2[i] += 3;
			player2Position(i);
		}
	}
	public void move()
	{
		if (Game.keys[0] && x1[3] > 22)
			p1Left();
		if (Game.keys[1] && x1[6] < 252)
			p1Right();
		if (Game.keys[2] && x2[3] > 22)
			p2Left();
		if (Game.keys[3] && x2[6] < 252)
			p2Right();
	}
	public void resetPlayers()
    {
            PlayerMovement move = new PlayerMovement();
            move.x1 = move.xStart;
            move.x2 = move.xStart;
            for (int i = 0; i != 10; i++)
            {
                    move.player1Position(i);
                    move.player2Position(i);
            }       
    }
}
