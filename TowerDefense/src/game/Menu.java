/**
 * Creates the graphics of the menu part of the game (such as the button and scores being kept)
 * 
 * @author Annie de Bry
 * @version November 2022
 */

package game;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class Menu extends GameObject
{
	Control control;
	State state;
	
	public Menu(Control control, State state)
	{
		this.control = control;
		this.state = state;
		isVisible = true;
		isExpired = false;
	}
	
	/**
	 * not used
	 */
	@Override
	public void update(double elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * draws the entire menu (including the background color)
	 */
	@Override
	public void draw(Graphics g) 
	{
		int menuX = 600;
		int menuY = 0;
		
		//custom colors
		Color menuColor = new Color(230, 202, 154);
		Color fontColor = new Color(66, 49, 1);
		Color numberColor = new Color(125, 108, 76);
		
		//creates the background color of the menu
		g.setColor(menuColor);
		g.fillRect(600, 0, 200, 600);
		
		//sets the font for every word drawn
		g.setColor(fontColor);
		Font myFont = new Font("Goudy Stout", Font.BOLD, 16);
		g.setFont(myFont);
		
		//drawing in the score, life, and money words
		g.drawString("Score:", menuX + 2, menuY + 30);
		
		g.drawString("Life:", menuX + 2, menuY + 60);
		
		g.drawString("Money:", menuX + 2, menuY + 90);
	
		//drawing in the score, life, and money numbers
		g.setColor(numberColor);
		
		g.drawString("" + state.getScore(), menuX + 105, menuY + 30);
		
		g.drawString("" + state.getLife(), menuX + 90, menuY + 60);
		
		g.drawString("" + state.getMoney(), menuX + 120, menuY + 90);
		
	}

}
