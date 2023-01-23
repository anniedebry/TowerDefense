/**
 * Where the majority of the loading comes from, if you want something loaded into the program,
 * you load it in through the run method. also implements mouseListeners, makes it able for the user
 * to interact with certain objects.
 * 
 * @author Annie de Bry
 * @version November 2022
 */

package game;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import path.Path;
import java.awt.image.BufferedImage;
import java.awt.event.*;

public class Control implements Runnable, ActionListener, MouseListener, MouseMotionListener
{
	State state;
    View  view;
    private Path path;
    private int mouseX;
    private int mouseY;
    Map<String, BufferedImage> countMap;

	
	public Control()
	{
		SwingUtilities.invokeLater(this);
	}

	/**
	 * Creates the frame, loads in the Path currently being used, loads in the images
	 * currently being used (enemy sprite, background), creates a timer used for the enemy
	 * sprite's movement
	 */
	public void run() 
	{
		countMap = new TreeMap<String, BufferedImage>();
		try
		{
			ClassLoader myLoader = this.getClass().getClassLoader();
			//loads in a previously saved path
			InputStream pathStream = myLoader.getResourceAsStream("resources/garf_path.txt");
			Scanner pathScanner = new Scanner(pathStream);
			//creates the new path based on the loaded path
			path = new Path(pathScanner);	
		}
		catch(Exception e)
		{
			System.out.println("Error!");
			return;
		}
		
		
		state = new State();
        view = new View(this, state); 
        
        state.startFrame();  // Prepares the creation of the 'next' frame
        state.addGameObject(new Background(this, state));  // Add one background object to our list
        state.addGameObject(new Menu(this, state));
        state.addGameObject(new MenuButton(this, state));
        state.addGameObject(new GameOver(this, state));
        state.finishFrame();// Mark the next frame as ready

        view.repaint();// Draw it.
        
        Timer t = new Timer(1, this);// Triggers every 16 milliseconds, reports actions to 'this' object.
        
        view.addMouseListener(this);
        view.addMouseMotionListener(this);
        
        t.start();
        
	}
	
	/**
	 * returns the current path
	 * @return this.path
	 */
	public Path getPath()
	{
		return this.path;
	}
	
	/**
	 * returns the current x coordinate of the mouse
	 * @return this.mouseX
	 */
	public int getMouseX()
	{
		return this.mouseX;
	}
	
	/**
	 * returns the current y coordinate of the mouse
	 * @return this.mouseY
	 */
	public int getMouseY()
	{
		return this.mouseY;
	}
	/**
	 * Main method behind loading images 
	 * 
	 * @param filename
	 * @return null(only if an Exception is caught)
	 */
	public BufferedImage getImage (String filename)
    {
		if(countMap.containsKey(filename))
		{
			BufferedImage newImage = countMap.get(filename);
			return newImage;
		}
        try
        {
            ClassLoader myLoader = this.getClass().getClassLoader();
            InputStream imageStream = myLoader.getResourceAsStream("resources/" + filename);
            BufferedImage image = javax.imageio.ImageIO.read(imageStream);
            return image;
        }
        catch (IOException e)
        {
            System.out.println("Could not find or load resources/" + filename);
            System.exit(0);  // Close the frame, bail out.
            return null;  // Does not happen, the application has exited.
        }
    }

	/**
	 * updates the games display by repainting the screen every time a new update occurs
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		state.startFrame();
		//gets the current frame for the game objects and updates the display
        for (GameObject go : state.getFrameObjects())
        {
            go.update(state.getElapsedTime());
            //checks if the game is still running(if the game over screen is still showing) 
            if(state.getGameStatus() == true)
            {
            	//checks if the total time is larger than the next gen time
    	        if(state.getTotalTime() > state.getNextGenTime())
    	        {
    	        	//generates the second enemy sprite
    	        	state.addGameObject(new SecondGarfield(this, state));
    	        	//adds 8 seconds onto the next generations time
    	        	state.setNextGenTime(10);
    	        }
    	        
    	        if(state.getTotalTime() > state.getFirstGenTime())
    	        {
    	        	state.addGameObject(new Garfield(this, state));
    	        	state.setFirstGenTime(5);
    	        }
            }
        }
        
        
        state.finishFrame();
        //repaints once for loop is complete
        view.repaint();
		
	}

	/**
	 * unused method
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * gets the x and y coordinates of the current location of the mouse
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

	}

	/**
	 * unused method
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * checks if the GameObjects are clickable by iterating through an ArrayList of them
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		List<GameObject> list = state.getFrameObjects();
		
		for(GameObject go : list) //iterates over the list of GameObjects
		{
			if(go instanceof Clickable) //checks if they're clickable
			{
				Clickable c = (Clickable) go;
				if(c.consumeClick()) //if they consume the click, the loop breaks
				{
					break;
				}
			}
		}
	}

	/**
	 * unused method
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * unused method
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * unsused method
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
