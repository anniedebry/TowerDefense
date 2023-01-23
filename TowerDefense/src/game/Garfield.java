/**
 * Creates the enemy sprite, keeps the percentage of how much of the path it has completed.
 * 
 * @author Annie de Bry
 * @version November 2022
 */

package game;

import java.awt.Graphics;
import java.awt.Point;

public class Garfield extends Enemy
{
	Control control;
	State state;
	private double percentage;
	private double velocity;
	
    public Garfield(Control control, State state)
    {
        percentage = 0;
        velocity = 0.0001;
        isVisible = true;
        isExpired = false;
        this.control = control;
        this.state = state;
    }

    /**
     * While the sprite moves, the percentage increases as it moves closer to the end point
     */
	@Override
	public void update(double elapsedTime) 
	{
        percentage = percentage + (velocity * elapsedTime);
        
        if(state.getGameStatus() == true)
        {
        	 if(percentage > 1)
             {
             	isExpired = true;
             	//changes the Life counter when the enemy reaches the end of the path
             	state.setLife(state.getLife() - 1);
             }
        }
        else if(state.getGameStatus() == false)
        {
        	isVisible = false; 
        }
	}

	/**
	 * Draws the enemy sprite on the current Path
	 */
	@Override
	public void draw(Graphics g) 
	{
		//gets the coordinates of the sprite based on the current percentage
		Point loc = control.getPath().convertToCoordinates(percentage);
		//draws in the enemy image
	    g.drawImage(control.getImage("garf.png"), loc.x - 32, loc.y - 32, null);
		
	}

	@Override
	Point getPosition() 
	{
		return control.getPath().convertToCoordinates(percentage);
	}

}
