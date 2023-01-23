/**
 * This class holds the game object for the bullets that the towers release,
 * it makes enemies expire when a bullet object and an enemy object come into contact
 * with each other. 
 * 
 * @author Annie de Bry
 * @Version december 2022
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class FoulWords extends GameObject
{
	Control control;
	State state;
	Point towerPos;
	
	double deltaX;
	double deltaY;
	double angle;
	double towerX;
	double towerY;
	double speed;
	Enemy myEnemy;
	
	public FoulWords(Control control, State state, Point towerPos)
	{
		this.state = state;
		this.control = control;
		this.towerPos = towerPos;
		isVisible = true;
		isExpired = false;
		Point closeEnemyPos = state.findNearestEnemy(towerPos).getPosition();
		
		myEnemy = state.findNearestEnemy(towerPos);
		closeEnemyPos = myEnemy.getPosition();
		
		deltaX = closeEnemyPos.getX() - towerPos.getX();
		deltaY = closeEnemyPos.getY() - towerPos.getY();
		angle = Math.atan2(deltaY, deltaX);
		
		towerX = towerPos.getX();
		towerY = towerPos.getY();
		
		speed = 10;
		
	}

	/**
	 * checks if an enemy and a bullet made contact with each other.
	 * also gets the position of the bullet
	 * 
	 * @param elapsedTime
	 */
	@Override
	public void update(double elapsedTime) 
	{
		
		towerX += speed * Math.cos(angle);
		towerY += speed * Math.sin(angle);
		
		if((int)myEnemy.getPosition().getX() >= (int)towerX && (int)myEnemy.getPosition().getY() >= (int)towerY)
		{
			isExpired = true; 
			isVisible = false;
			state.setMoney(state.getMoney() + 25);
			state.setScore(state.getScore() + 100);
			myEnemy.isExpired = true;
			myEnemy.isVisible = false;
		}
	
		
	}

	/**
	 * draws in the bullet(in this case its a word)
	 * @param g
	 */
	@Override
	public void draw(Graphics g) 
	{
		g.drawImage(control.getImage("john_word.png"), (int)towerX, (int)towerY, 58, 26, null);
		
	}

}
