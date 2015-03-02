package nickmiste.slingshot;

import com.badlogic.gdx.math.Vector3;

public class Movement 
{
	public static double[] calculateMovexy(Vector3 objectPos, Moveable moveable, int mass, int gm1m2, Vector3 gravityPos, boolean gravityOn)
	{
		//Using gravity field as origin
		double force = (gm1m2 / gravityPos.dst2(objectPos));
		
		double accelx = 0;
		double accely = 0;
		
		double adj = (objectPos.x - gravityPos.x);
		double opp = (objectPos.y - gravityPos.y);
		
		double angle = 0;

		float forcex = 0;
		float forcey = 0;
		
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
		
		accelx = forcex / mass; // (F = m * a) OR (a = F / m)
		accely = -forcey / mass;
		
		double velocityx = moveable.velocityx + accelx; // V[n] = V[n-1] + at
		double velocityy = moveable.velocityy + accely;
		
		moveable.velocityx = velocityx;
		moveable.velocityy = velocityy;
		
		double movex = 0.5 * accelx + velocityx; // delta S = 0.5 at^2 + vt
		double movey = 0.5 * accely + velocityy;
		
		return new double[] {movex, movey};
	}
}
