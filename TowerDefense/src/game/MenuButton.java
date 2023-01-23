/**
 * Creates the MenuButton, when pressed, allows you to create a tower(John) that can be placed down
 * 
 * @author Annie de Bry
 * @version November 2022
 */

package game;

import java.awt.Color;
import java.awt.Graphics;

public class MenuButton extends GameObject implements Clickable
{
	State state;
	Control control;
	
	
	public MenuButton(Control control, State state)
	{
		this.state = state;
		this.control = control;
		isVisible = true;
		isExpired = false;
	}
	
	/**
	 * unused method
	 */
	@Override
	public void update(double elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * draws the menu button
	 */
	@Override
	public void draw(Graphics g) 
	{
		//custom colors for the button
		Color darker = new Color(66, 49, 1);
		Color lighter = new Color(125, 108, 76);
		
		//shadow of the button
		g.setColor(darker);
		g.fillRoundRect(605, 400, 111, 42, 10, 10);
		//lighter part of the button
		g.setColor(lighter);
		g.fillRoundRect(615, 410, 100, 30, 8, 8);
		//words of the button
		g.setColor(darker);
		g.drawString("John", 625, 430);
//		g.drawImage(control.getImage("garf.png"), 715, 415, null); saved for when I create the sprite
	}

	/**
	 * checks if the user pressed the coordinates where the buttons are located, when pressed,
	 * creates a new john object.
	 */
	@Override
	public boolean consumeClick() {
		if(state.getMoney() >= 100)
		{
			if(control.getMouseX() >= 605 && control.getMouseX() <= 716 && control.getMouseY() >= 400 && control.getMouseY() <= 442)
			{
				state.addGameObject(new John(control, state));
				state.setMoney(state.getMoney() - 100);
				return true;
			}
		}
		return false;
	}


}

