package nickmiste.slingshot;

import nickmiste.slingshot.leveldesign.Obstacle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;

public class Player 
{
	public int radius;
	
	public Vector3 gravityPos;
	public Vector3 pos;
	
	private int mass;
	public double velocityx;
	public double velocityy;
	private double oldVelocityx;
	private double oldVelocityy;
	public int gm1m2;
	private float forcex;
	private float forcey;
	
	public Vector3 oldPos;
	
	public boolean gravityOn;
	public boolean isVisible;
	
	public Player()
	{
		radius = 15;
		gravityPos = new Vector3();
		gravityPos.set(100, 1, 0);
		pos = new Vector3();
		pos.set(100, 150, 0);
		
		forcex = 0;
		forcey = 0;
		mass = 100;
		velocityx = 0;
		velocityy = 0;
		oldVelocityx = 0;
		oldVelocityy = 0;
		gm1m2 = 100000;
		
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
		
		double[] movement = calculateMovexy(gm1m2, gravityPos, gravityOn);

		Vector3 move = new Vector3((float) movement[0], (float) movement[1], 0);
		
		for (int i = 0; i < Main.level.gravityFields; i++)
		{
			Obstacle obstacle = Main.level.getObstacles()[Main.level.obstaclesWithGravity[i]];
			movement = calculateMovexy(obstacle.gravityField(), new Vector3(obstacle.x, obstacle.y, 0), true);
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
	
	private double[] calculateMovexy(int gm1m2, Vector3 gravityPos, boolean gravityOn)
	{
		//Using gravity field as origin
		double force = (gm1m2 / gravityPos.dst2(pos));
		
		double accelx = 0;
		double accely = 0;
		
		double adj = (pos.x - gravityPos.x);
		double opp = (pos.y - gravityPos.y);
		
		double angle = 0;
		
		if (gravityOn)
		{
			if (adj <= 25 && adj >= -25 && opp <= 25 && opp >= -25)
			{
				forcex = 0;
				forcey = 0;
			}
			else
			{
				angle = Math.atan(opp / adj);
				
				
				forcex = (float) (Math.cos(angle) * force) * 5;
				forcey = (float) (Math.sin(angle) * force) * 5;		
				
				if (adj > 0)
				{
					forcex = -forcex;
					forcey = -forcey;
				}
			}
		}
		else
		{
			forcex = 0;
			forcey = 0;
		}
		accelx = forcex / mass; // (F = m * a) OR (a = F / m)
		accely = -forcey / mass;
		
		velocityx = oldVelocityx + accelx; // V[n] = V[n-1] + at
		velocityy = oldVelocityy + accely;
		
		double movex = 0.5 * accelx + velocityx; // delta S = 0.5 at^2 + vt
		double movey = 0.5 * accely + velocityy;
		
		oldVelocityx = velocityx;
		oldVelocityy = velocityy;
		
		return new double[] {movex, movey};
	}
	
	public void setVelocity(double x, double y)
	{
		this.oldVelocityx = x;
		this.oldVelocityy = y;
	}
	
	//Debug Method
	private void quads(double x, double y)
	{
		System.out.println((x > 0) + " " + (y > 0));
	}
}