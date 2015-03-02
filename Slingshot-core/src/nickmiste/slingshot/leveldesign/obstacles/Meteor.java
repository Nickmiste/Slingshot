package nickmiste.slingshot.leveldesign.obstacles;

import nickmiste.slingshot.Main;
import nickmiste.slingshot.Movement;
import nickmiste.slingshot.Player;
import nickmiste.slingshot.leveldesign.Obstacle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;

public class Meteor extends Obstacle
{
	public Meteor(int x, int y)
	{
		super(x, y);
	}

	@Override
	public void effect() 
	{
		Main.shapes.setProjectionMatrix(Main.camera.combined);
		Main.shapes.begin(ShapeType.Filled);
		Main.shapes.setColor(Color.RED);
		Main.shapes.circle(this.x, this.y, 10);
		Main.shapes.end();
	}
	
	@Override
	public void move()
	{
		double[] movement = Movement.calculateMovexy(new Vector3(this.x, this.y, 0), this, 100, 100000, Player.gravityPos, Player.gravityOn);
		
		Vector3 move = new Vector3((float) movement[0], (float) movement[1], 0);
		
		for (int i = 0; i < Main.level.gravityFields; i++)
		{
			Obstacle obstacle = Main.level.getObstacles()[Main.level.obstaclesWithGravity[i]];
			movement = Movement.calculateMovexy(new Vector3(this.x, this.y, 0), this, 100, obstacle.gravityField(), new Vector3(obstacle.x, obstacle.y, 0), true);
			move.add(new Vector3((float) movement[0], (float) movement[1], 0));
		}
		
		this.x += move.x;
		this.y += move.y;
	}
}