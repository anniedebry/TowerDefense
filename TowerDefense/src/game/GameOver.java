/**
 * Creates a game object that displays a game over screen when a certain
 * condition is met. 
 * 
 * @author Annie de Bry
 * @version November 2022
 */
package game;

import java.awt.Graphics;

public class GameOver extends GameObject
{

	State state;
	Control control;
	
	public GameOver(Control control, State state)
	{
		this.state = state;
		this.control = control;
		isVisible = false;
		isExpired = false;
	}
	
	/**
	 * checks the status of the game.
	 * checks a condition, if it's met, shows the game over screen
	 * @param elapsedTime
	 */
	@Override
	public void update(double elapsedTime) 
	{
		//checks if the user has lives left or not 
		if(state.getLife() == 0)
		{
			//shows the game over screen
			isVisible = true;
			//sets the status to false so that the game can't update anymore
			state.setGameStatus(false);
		}
	}

	/**
	 * loads the game over screen
	 */
	@Override
	public void draw(Graphics g) 
	{
		g.drawImage(control.getImage("gameoverbackground.png"), 0, 0, null);
	}

}
