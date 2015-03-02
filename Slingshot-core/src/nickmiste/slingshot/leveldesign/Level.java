package nickmiste.slingshot.leveldesign;

import nickmiste.slingshot.Main;
import nickmiste.slingshot.Player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;

public abstract class Level 
{
	public Vector3 finishPos;
	private ShapeRenderer shapes;
	
	public int gravityFields;
	public int[] obstaclesWithGravity;
	
	private Obstacle[] obstacles;
	
	public Level()
	{
		shapes = new ShapeRenderer();
		
		this.obstacles = obstacles();
		
		this.finishPos = getFinish();
		
		for (int i = 0; i < this.obstacles.length; i++)
			if (this.obstacles[i].gravityField() != -1)
				gravityFields++;
		obstaclesWithGravity = new int[gravityFields];
		for (int i = 0; i < this.obstacles.length; i++)
			if (this.obstacles[i].gravityField() != -1)
			{
				for (int j = 0; j < obstaclesWithGravity.length; j++)
					obstaclesWithGravity[j] = -1;
				for (int j = 0; j < obstaclesWithGravity.length; j++)
				{
					if (obstaclesWithGravity[j] == -1)
					{
						obstaclesWithGravity[j] = i;
						continue; //Not sure if needed
					}
				}
			}
	}
	
	protected abstract Vector3 getFinish();
	
	protected abstract Obstacle[] obstacles();
	
	public Obstacle[] getObstacles()
	{
		return this.obstacles;
	}
	
	public void playLevel()
	{
		for (Obstacle i : this.obstacles)
		{
			i.effect();
			i.move();
		}
		shapes.setProjectionMatrix(Main.camera.combined);
		shapes.begin(ShapeType.Filled);
		shapes.setColor(Color.BLACK);
		shapes.circle(this.finishPos.x, this.finishPos.y, 50);
		shapes.end();
		
		int xDist = Math.abs((int) (Main.player.pos.x - this.finishPos.x));
		int yDist = Math.abs((int) (Main.player.pos.y - this.finishPos.y));
		
		if (xDist <= 50 && yDist <= 50)
		{
			System.out.println("You win!");
			Main.player = new Player();
		}
	}
}