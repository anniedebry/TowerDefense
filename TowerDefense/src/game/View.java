/**
 * sets up the JPanel and JFrame of the program, paints in current graphics
 * 
 * @author Annie de Bry
 * @version November 2022
 */

package game;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class View extends JPanel {
	 State state;
	 Control control;
	 
	 public View(Control control, State state)
	 {
		 this.control = control;
		 this.state = state;
	     
		 //sets the size of the frame
	     Dimension d = new Dimension (800, 600);
		 this.setMinimumSize(d);
		 this.setPreferredSize(d);
		 this.setMaximumSize(d);
	     
//		 JButton tower = new JButton("Tower");
//		 tower.setLocation(600, 25);
		 
		 JFrame f = new JFrame("Tower Defense Game 2022");
	     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     f.setContentPane(this); 
	     f.setLocationRelativeTo(null);
	     f.setVisible(true);
	     f.pack();
	     f.setVisible(true);
	 }
	 
	 /**
	  * paints the current frames of the game objects onto the view
	  */
	 public void paint(Graphics g)
	 {
		 //retrieves the current game objects that will be displayed on the frame
		 for (GameObject go : state.getFrameObjects())
		 {
	        if (go.isVisible() && !go.isExpired())
	        {
	        	go.draw(g); //draws objects
	        }
		 }
	 }
	 
}
