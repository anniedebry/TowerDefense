/**
 * The internal aspects of each GameObject, ex: if it's visible or not
 * 
 * @author Annie de Bry
 * @version November 2022
 */

package game;

import java.awt.Graphics;

//GameObject is an abstract class
abstract public class GameObject 
{
	protected boolean isVisible; 
    protected boolean isExpired;

    public boolean isVisible() { return isVisible; }
    public boolean isExpired() { return isExpired; }

    abstract public void update (double elapsedTime);
    abstract public void draw (Graphics g);
}
