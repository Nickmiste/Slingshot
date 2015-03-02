package nickmiste.slingshot.leveldesign;

import nickmiste.slingshot.Moveable;

public abstract class Obstacle extends Moveable
{
	public int x;
	public int y;
	
	public Obstacle(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public abstract void effect();
	 
	public int gravityField() //Returns gm1m2 or -1 in the case of no gravity field
	{
		return -1;
	}
	
	public void move() {}
}