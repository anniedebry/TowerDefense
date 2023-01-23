/**
 * controls the graphic frames and hold the variables involved in keeping the money, lives,
 * and score values. 
 * 
 * @author Annie de Bry
 * @version November 2022
 */

package game;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class State 
{
	//instance variables to keep score, money, and life in the game
	private int money = 1000;
	private int life = 10;
	private int score = 0;
	
	//instance variable to check if its game over or not
	private boolean game = true;
	
	//time instance variables   
	private double elapsedTime;
	private double totalTime;
	private double nextGenTime;
	private double firstGenTime;
	private long timeStart;
	private double elapsedSeconds;
	
	//Frame lists (Graphics)
	List<GameObject> currentFrameGameObjects;
    List<GameObject> nextFrameGameObjects;
    
    public State()
    {
    	nextGenTime = 13.0;
    	firstGenTime = 0.0;
    	totalTime = 0.0;
    	elapsedTime = 0.0;
    	
    	
      	timeStart = System.currentTimeMillis();
    	
        currentFrameGameObjects = new ArrayList<GameObject>();
  
    }
    
    /**
     * retrieves an array list made up of GameObjects that'll appear on the screen and
     * returns it.
     * 
     * @return currentFrameGameObjects
     */
    public List<GameObject> getFrameObjects ()
    {
        return currentFrameGameObjects;
    }
    
    /**
     * Creates an empty ArrayList made up of game objects then adds all the current 
     * game objects to the list.
     * 
     */
    public void startFrame ()
    {
    	
        nextFrameGameObjects = new ArrayList<GameObject>();   
        nextFrameGameObjects.addAll(currentFrameGameObjects);
        
        //calculates elapsed time using the starting time and ending time
        elapsedTime = (System.currentTimeMillis() - timeStart);
        
        //converts the elapsed time from milliseconds to seconds
        elapsedSeconds = elapsedTime / 1000.0;
        
        //adds the elapsed time to the total time
        totalTime = totalTime + elapsedSeconds;
        
        timeStart = System.currentTimeMillis();
        
        
        
        
    }
    
    /**
     * Makes the currentFrameGameObjects transfer to the nextFrameGameObjects(end of the frame)
     */
    public void finishFrame ()
    {
    	if(game == true)
    	{
    		for(int index = 0; index < currentFrameGameObjects.size(); index++)
        	{
        		if(currentFrameGameObjects.get(index).isExpired() == true)
        		{
        			nextFrameGameObjects.remove(currentFrameGameObjects.get(index));
        		}
        	}
        	
            currentFrameGameObjects = nextFrameGameObjects;
    	}
//        nextFrameGameObjects = null;  // I added this -- it makes it clear there is only a current list now.
    }
    
    /**
     * Finds the nearest enemy to the defense tower
     * @param point
     * @return closeEnemy
     */
    public Enemy findNearestEnemy(Point point)
    {
    	//variable for the seconds Arrays size
    	int count = 0;
    	for(int index = 0; index < getFrameObjects().size(); index++)
    	{
    		//checks which index has the instance of Enemy
    		if(getFrameObjects().get(index) instanceof Enemy)
    		{
    			count++;
    		}
    	}
    	
    	//returns null if the count is 0 because the ArrayList would be 0
    	if(count == 0)
    	{
    		return null;
    	}
    	
    	//creates new list for only Enemy objects and sets the size to the count variable
    	List<Enemy> enemyObjects = new ArrayList<Enemy>(count);
    	
    	for(int index = 0; index < getFrameObjects().size(); index++)
    	{
    		//checks if the current index is an Enemy
    		if(getFrameObjects().get(index) instanceof Enemy)
    		{
    			//if it is an enemy, adds the index to the new array list
    			enemyObjects.add((Enemy)getFrameObjects().get(index));
    		}
    	}
    	
    	//gets the distance of the closest enemy(this one is a place holder, its index 0 for now)
    	double closeDistance = Math.sqrt((((enemyObjects.get(0).getPosition().getY()) - point.getY())
    						   * ((enemyObjects.get(0).getPosition().getY()) - point.getY()))
    						   + ((((enemyObjects.get(0).getPosition().getX()) - point.getX())
    						   * ((enemyObjects.get(0).getPosition().getX()) - point.getX()))));
    	Enemy closeEnemy = enemyObjects.get(0);
    	for(int index = 0; index < enemyObjects.size(); index++)
    	{
    		//gets the distance between the enemy and the tower 
    		double tempDistance = Math.sqrt((((enemyObjects.get(index).getPosition().getY()) - point.getY())
    							  * ((enemyObjects.get(index).getPosition().getY()) - point.getY()))
    							  + ((((enemyObjects.get(index).getPosition().getX()) - point.getX())
    							  * ((enemyObjects.get(index).getPosition().getX()) - point.getX()))));
    		//checks if the current distance is less than or equal to the closest distance saved
    		if(tempDistance <= closeDistance)
    		{
    			closeEnemy = enemyObjects.get(index);
    		}
    	}
    	
		return closeEnemy;
    	
    }
    
    /**
     * Adds a game object to the GUI
     * 
     * @param go
     */
    public void addGameObject (GameObject go)
    {
        nextFrameGameObjects.add(go);
    }
    
    //getters and setters
    
    
    /**
     * getter for money
     * @return money
     */
    public int getMoney()
    {
    	return money;
    }
    
    /**
     * setter for money
     * @param newMoney
     * @return money
     */
    public int setMoney(int newMoney)
    {
    	money = newMoney;
    	return money;
    }
    
    /**
     * getter for life
     * @return life
     */
    public int getLife()
    {
    	return life;
    }
    
    /**
     * setter for life
     * @param newLife
     * @return life
     */
    public int setLife(int newLife)
    {
    	life = newLife;
    	return life;
    }
    
    /**
     * getter for score
     * @return score
     */
    public int getScore() 
    {
    	return score;
    }
    
    /**
     * setter for score
     * @param newScore
     * @return score
     */
    public int setScore(int newScore)
    {
    	score = newScore;
    	return score;
    }
    
    /**
     * setter for game results
     * @param gameResult
     * @return game
     */
    public boolean setGameStatus(boolean gameResult)
    {
    	game = gameResult;
    	return game;
    }
    
    public boolean getGameStatus()
    {
    	return game;
    }
    
    /**
     * getter for elapsedTime
     * @param newTime
     * @return elapsedTime
     */
	public double setElapsedTime(double newTime)
	{
		elapsedTime = newTime;
		return elapsedTime;
	}
	
	/**
	 * getter for elapsed time
	 * @return elapsedTime
	 */
	public double getElapsedTime()
	{
		return elapsedTime;
	}
	
	/**
	 * getter for totalTime
	 * @param newTotalTime
	 * @return totalTime
	 */
	public double setTotalTime(double newTotalTime)
	{
		totalTime = newTotalTime;
		return totalTime;
	}
	
	/**
	 * getting the total time
	 * @return totalTime
	 */
	public double getTotalTime()
	{
		return totalTime;
	}
	
	/**
	 * getter for the nextGenTime
	 * @return nextGenTime
	 */
	public double getNextGenTime()
	{
		return nextGenTime;
	}
	
	/**
	 * setter for nextGenTime
	 * @param additionToTime
	 * @return nextGenTime
	 */
	public double setNextGenTime(double additionToTime)
	{
		nextGenTime = nextGenTime + additionToTime;
		return nextGenTime;
	}
	
	/**
	 * adds to the first generations time 
	 * @param additionTime
	 * @return firstGenTime
	 */
	public double setFirstGenTime(double additionTime)
	{
		firstGenTime = firstGenTime + additionTime;
		return firstGenTime;
	}
	
	/**
	 * getter for the first generation time
	 * @return firstGenTime
	 */
	public double getFirstGenTime()
	{
		return firstGenTime;
	}
	
	
}
