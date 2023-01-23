/**
 * Iterates over the pre-made path that the enemy sprite follows, involves all internal aspects
 * of the path. 
 * 
 * @author Annie de Bry
 * @version November 2022
 */

package path;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Scanner;

/**
 * This class holds all the methods used to create a path.
 * 
 * @author Annie de Bry
 * @version November 2022
 */

public class Path 
{
	//creates an array list of points
	private ArrayList<Point> pointArray;
	
	/**
	 * Creates an empty point ArrayList in the constructor
	 */
	public Path()
	{
		pointArray = new ArrayList<Point>(); 
	}
	
	/**
	 * Adds new points into a pre-existing ArrayList, takes user input in order to 
	 * create the x and y variables of the new points.
	 * 
	 * @param sc
	 */
	public Path(Scanner sc)
	{
		pointArray = new ArrayList<Point>(); 
		
		int size = sc.nextInt();
		
		for(int index = 0; index < size; index++)
		{
			pointArray.add(new Point (sc.nextInt(), sc.nextInt()));
		}
		
	}
	
	/**
	 * iterates over the pointArray and counts how many points are inside the ArrayList
	 * 
	 * @return count
	 */
	public int getPointCount()
	{
		int count = 0;
		
		for(int index = 0; index < pointArray.size(); index++)
		{
			count++;
		}
		
		return count;
	}
	
	/**
	 * Grabs the x value from a specified point in pointArray
	 * 
	 * @param n
	 * @return xValue
	 */
	
	public int getX(int n)
	{
		int xValue;
		
		xValue = (int) pointArray.get(n).getX();
		
		return xValue;
	}
	
	/**
	 * Grabs the y value from a specified point in pointArray
	 * 
	 * @param n
	 * @return yValue
	 */
	public int getY(int n)
	{
		int yValue;
		
		yValue = (int) pointArray.get(n).getY();
		
		return yValue;
	}
	
	/**
	 * Adds new points into a pre-existing ArrayList
	 * 
	 * @param x
	 * @param y
	 */
	public void add(int x, int y)
	{
		pointArray.add(new Point (x, y));
	}
	
	/**
	 * groups all elements in pointArray into a String variable, making sure that
	 * index 0 contains the size of the ArrayList.
	 * 
	 * @return output
	 */
	public String toString()
	{
		String output = pointArray.size() + "\n";
		
		for(int index = 0; index < pointArray.size(); index++)
		{
			output = output + getX(index) + " " + getY(index) + "\n";
		}
		
		return output;
	}
	
	/**
	 * draws a line between the two points that the user placed(draws it in pink)
	 * 
	 * @param g
	 */
	 public void draw (Graphics g)
	 {
		 g.setColor(Color.PINK);
		 
		 for(int index = 0; index < pointArray.size() - 1; index++)
			{
				g.drawLine(getX(index), getY(index), getX(index + 1), getY(index + 1));
			}
	 }
	 
	 /** 
	  * Given a percentage between 0% and 100%, this method calculates
	  * the location along the path that is exactly this percentage
	  * along the path. The location is returned in a Point object
	  * (integer x and y), and the location is a screen coordinate.
	  * 
	  * If the percentage is less than 0%, the starting position is
	  * returned. If the percentage is greater than 100%, the final
	  * position is returned.
	  * 
	  * Callers must not change the x or y coordinates of any returned
	  * point object (or the caller could be changing the path).
	  * 
	  * @param percentTraveled a distance along the path
	  * @return the screen coordinate of this position along the path
	  */
	public Point convertToCoordinates(double percentage) 
	{
		//checks if the current percentage is 100%
		if(percentage >= 1)
		{
			return new Point(getX(getPointCount() - 1), getY(getPointCount() - 1));
		}
		//checks if the current percent is less than or equal to 0
		else if(percentage <= 0)
		{
			return new Point(getX(0), getY(0));
		}
		double totalLengthPath = 0.0;
		for(int index = 1; index < getPointCount(); index++)
		{
			//gets the total length of the entire path by retrieving individual segment lengths and adding them up
			totalLengthPath += getLength(index);
		}
		
		//the calculation to get the total amount of pixels the sprite has traveled
		double totalAmountPixels = totalLengthPath * percentage;
		//temporary variable to check the current amount of pixels traveled
		double tempTotalSegPix = totalAmountPixels;
		int currentSeg = 0;
		for(int index = 1; index < getPointCount(); index++)
		{
			tempTotalSegPix = tempTotalSegPix - getLength(index);
			
			//checks if the current total pixels is less than 0
			if(tempTotalSegPix < 0)
			{
				//adds back the removed length(re-approximating where the sprite currently is located)
				tempTotalSegPix = tempTotalSegPix + getLength(index);
				//saves the current index to which segment the sprite is currently in
				currentSeg = index;
				break;
			}
		}
		
		//calculates the current percentage of the sprite. 
		double tempPercent = tempTotalSegPix / getLength(currentSeg);
		
		//calculates the exact coordinates of where the sprite is located on the path (x, y)
		int x = (int) ((1 - tempPercent) * getX(currentSeg - 1) + (tempPercent) * getX(currentSeg));
		int y = (int) ((1 - tempPercent) * getY(currentSeg - 1) + (tempPercent) * getY(currentSeg));
		
		//returns the current coordinates of the sprite
		return new Point(x, y);
	}
	
	/**
	 * Gets the length of one segment by calculating the distance between two points
	 * 
	 * @param n
	 * @return length
	 */
	
	public double getLength(int n)
	{
		double length = 0.0;
		
		double sideOne = (getX(n) - getX(n - 1));
		double sideTwo = (getY(n) - getY(n - 1));
		//distance formula
		length = Math.sqrt((sideOne * sideOne)+(sideTwo * sideTwo));
		
		return length;
	}
}
