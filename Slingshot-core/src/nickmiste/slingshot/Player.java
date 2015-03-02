package nickmiste.slingshot;

import nickmiste.slingshot.leveldesign.Obstacle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;

public class Player extends Moveable
{
	public int radius;
	
	public static Vector3 gravityPos;
	public Vector3 pos;
	
	public int gm1m2;
	private int mass;
	
	public Vector3 oldPos;
	
	public static boolean gravityOn;
	public boolean isVisible;
	
	public Player()
	{
		radius = 15;
		gravityPos = new Vector3();
		gravityPos.set(100, 1, 0);
		pos = new Vector3();
		pos.set(100, 150, 0);
		
		gm1m2 = 100000;
		mass = 100;
		
		gravityOn = false;
		isVisible = true;
	}
	
	public void doMovement()
	{
		
		if (Gdx.input.isTouched())
		{
			gravityPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			gravityOn = true;
		}
		else
		{
			gravityOn = false;
		}
		
		double[] movement = Movement.calculateMovexy(this.pos, this, this.mass, this.gm1m2, gravityPos, gravityOn);

		Vector3 move = new Vector3((float) movement[0], (float) movement[1], 0);
		
		for (int i = 0; i < Main.level.gravityFields; i++)
		{
			Obstacle obstacle = Main.level.getObstacles()[Main.level.obstaclesWithGravity[i]];
			movement = Movement.calculateMovexy(this.pos, this, this.mass, obstacle.gravityField(), new Vector3(obstacle.x, obstacle.y, 0), true);
			move.add(new Vector3((float) movement[0], (float) movement[1], 0));
		}
				
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
			quads(move.x, move.y);
		
		pos.add((float) move.x, (float) -move.y, 0);
		oldPos = new Vector3((float) (pos.x - move.x), (float) (pos.y + move.y), 0);
		
		//Draw shapes to screen
		Main.shapes.setProjectionMatrix(Main.camera.combined);
		Main.shapes.begin(ShapeType.Filled);
		if (gravityOn)
		{
			Main.shapes.setColor(Color.PURPLE);
			Main.shapes.circle(gravityPos.x, gravityPos.y, 25);
		}
		if (this.isVisible)
		{
			Main.shapes.setColor(Color.WHITE);
			Main.shapes.circle(pos.x, pos.y, radius);
		}
		Main.shapes.end();
	}
	
	public void setVelocity(double x, double y)
	{
		this.velocityx = x;
		this.velocityy = y;
	}
	
	//Debug Method
	private void quads(double x, double y)
	{
		System.out.println((x > 0) + " " + (y > 0));
	}
}