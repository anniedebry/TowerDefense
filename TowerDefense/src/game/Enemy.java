/**
 * A superclass for all enemies, extends GameObject.
 */

package game;

import java.awt.Graphics;
import java.awt.Point;

abstract class Enemy extends GameObject
{
	/**
	 * Enemy superclass constructor
	 */
	public Enemy()
	{
		
	}

	/**
	 * unused method, update
	 */
	@Override
	public void update(double elapsedTime) 
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * unused method, draw
	 */
	@Override
	public void draw(Graphics g) 
	{
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * method that applies to all that implements Enemy, returns the position of an enemy
	 * 
	 */
	abstract Point getPosition();

}
