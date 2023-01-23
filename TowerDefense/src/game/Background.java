/**
 * Loads the background of the program
 * 
 * @author Annie de Bry
 * @version November 2022
 */

package game;

import java.awt.Graphics;

public class Background extends GameObject
{
	Control control;
	State state;
	
	public Background(Control control, State state)
	{
		 isVisible = true;
	     isExpired = false;
	     this.control = control;
	     this.state = state;
	}

	/**
	 * required method, not being used currently
	 */
	@Override
	public void update(double elapsedTime) 
	{
		
	}

	/**
	 * Draws the background
	 */
	@Override
	public void draw(Graphics g) {
		g.drawImage(control.getImage("garf_background.jpg"), 0, 0, null);
	}
	
}
