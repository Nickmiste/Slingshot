package nickmiste.slingshot.leveldesign.obstacles;

import nickmiste.slingshot.Main;
import nickmiste.slingshot.Player;
import nickmiste.slingshot.leveldesign.Obstacle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Void extends Obstacle
{
	private int radius;
	
	public Void(int x, int y, int radius)
	{
		super(x, y);
		this.radius = radius;
	}
	
	@Override
	public void effect() 
	{
		int xDist = Math.abs((int) (Main.player.pos.x - this.x));
		int yDist = Math.abs((int) (Main.player.pos.y - this.y));
		
		if (xDist <= this.radius && yDist <= this.radius)
		{
			System.out.println("You Loose!");
			Main.player = new Player();
		}
		
		Main.shapes.setProjectionMatrix(Main.camera.combined);
		Main.shapes.begin(ShapeType.Filled);
		Main.shapes.setColor(Color.BLACK);
		Main.shapes.circle(this.x, this.y, this.radius);
		Main.shapes.end();
	}

	@Override
	public int gravityField()
	{
		return 100000;
	}
}