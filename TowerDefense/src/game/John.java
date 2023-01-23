/**
 * A class that creates the John graphics(which is the tower that defends the lasagna), implements
 * Clickable which makes the user able to interact with the object
 * 
 * @author Annie de Bry
 * @version November 2022
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class John extends GameObject implements Clickable
{
	Control control;
	State state;
	private int x,y;
	private boolean isMoving;
	private double cooldown;
	Enemy myEnemy;
	Point towerPos;

	public John(Control control, State state)
	{
		this.control = control;
		this.state = state;
		isVisible = true;
		isExpired = false;
		isMoving = true;
		cooldown = 5000.0;
	
		towerPos = new Point(x, y);
		myEnemy = state.findNearestEnemy(towerPos);
	}
	
	/**
	 * Makes sure John follows the mouse until the user clicks to place it down
	 */
	@Override
	public void update(double elapsedTime) {
		if(state.getGameStatus() == false)
		{
			isVisible = false;
			isExpired = true; 
		}
		
		if(isMoving)
		{
			x = control.getMouseX();
			y = control.getMouseY();
		}
		else
		{
			//gets the distance of the nearest enemy and the tower
			double distance = Math.sqrt((((myEnemy.getPosition().getY()) - towerPos.getY())
					   * ((myEnemy.getPosition().getY()) - towerPos.getY()))
					   + ((((myEnemy.getPosition().getX()) - towerPos.getX())
					   * ((myEnemy.getPosition().getX()) - towerPos.getX()))));
			//if the distance is less than 50, remove the tower
			if(distance < 50)
			{
				isVisible = false;
				isExpired = true;
			}
			
			
			//calculates cool down
			cooldown -= elapsedTime;
			if(cooldown <= 0)
			{	//resets cool down if certain condition is met
				if(state.findNearestEnemy(new Point(x,y)) == null)
				{
					cooldown = 5000.0;
				}
				else
				{
					Enemy closestEnemy = state.findNearestEnemy(new Point(x,y));
					state.addGameObject(new FoulWords(control, state, new Point(x,y)));
					cooldown = 5000.0;
				}
			}
		}
		
	}

	/**
	 * Draws John (the tower of the tower defense game)
	 */
	@Override
	public void draw(Graphics g) {
		g.drawImage(control.getImage("john.png"), x, y, 64, 140, null);
		
	}

	/**
	 * Allows the object to be able to be clicked and interacted with
	 */
	@Override
	public boolean consumeClick() 
	{

		if(isMoving == true)
		{
			if(control.getMouseX() <= 600 && control.getMouseY() <= 600)
			{
				isMoving = false;
			}
			
		
			for(int index = 0; index < control.getPath().getPointCount(); index++)
			{
				double distance = Math.sqrt((((control.getPath().getY(index)) - control.getMouseY())
						   * ((control.getPath().getY(index)) - control.getMouseY()))
						   + ((((control.getPath().getX(index)) - control.getMouseX())
						   * ((control.getPath().getX(index)) - control.getMouseY()))));
				if(distance < 115)
				{
					isMoving = true;
				}
			}
			
		}
		return false;
	}

}

